package com.orders.service;

import com.orders.dto.AddressOutDto;
import com.orders.dto.AmountInDto;
import com.orders.dto.FoodItemNameOutDto;
import com.orders.dto.OrderInDto;
import com.orders.dto.OrderItemDetailOutDto;
import com.orders.dto.OrderOutDto;
import com.orders.dto.RestaurantOutDto;
import com.orders.dto.UpdateStatusInDto;
import com.orders.dto.UserOutDto;
import com.orders.dtoconversion.DtoConversion;
import com.orders.entities.Cart;
import com.orders.entities.Order;
import com.orders.entities.OrderStatus;
import com.orders.exceptionhandler.InsufficientBalance;
import com.orders.exceptionhandler.InvalidOperation;
import com.orders.exceptionhandler.OrderNotFound;
import com.orders.exceptionhandler.SessionExpiredException;
import com.orders.exceptionhandler.UserNotFound;
import com.orders.repo.CartRepo;
import com.orders.repo.OrderRepo;
import com.orders.util.Constant;
import com.orders.util.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for managing orders in the system.
 */
@Service
@Slf4j
public class OrderService {
  /**
   * Repository for managing order entities.
   */
  @Autowired
  private OrderRepo orderRepo;

  /**
   * Repository for managing cart entities.
   */
  @Autowired
  private CartRepo cartRepo;

  /**
   * Feign client for interacting with user-related services.
   */
  @Autowired
  private UserFClient userFClient;


  /**
   * Feign client for interacting with restaurant-related services.
   */
  @Autowired
  private RestaurantFClient restaurantFClient;


  /**
   * Retrieves the restaurant name by its ID.
   *
   * @param restaurantId the ID of the restaurant
   * @return the name of the restaurant
   */
  public String getRestaurantName(final Integer restaurantId) {
    String response = "NA";
    RestaurantOutDto restaurantOutDto = null;
    try {
      restaurantOutDto = restaurantFClient.getRestaurantById(restaurantId).getBody();
      response = restaurantOutDto.getRestaurantName();
    } catch (Exception e) {
      log.info("exception in fetching data from restro microservice using fclient");
    }
    return response;
  }


  /**
   * Retrieves the address details by address ID.
   *
   * @param addressId the ID of the address
   * @return a formatted address string
   */
  public String getAddress(final Integer addressId) {
    String response = "NA";
    AddressOutDto addressOutDto = null;
    try  {
      addressOutDto = userFClient.getAddressByAddressId(addressId).getBody();
      response = addressOutDto.getStreet() + "  " + addressOutDto.getCity() + " " + addressOutDto.getState();
    } catch (Exception e) {
      System.out.println(e);
    }
    return response;
  }


  /**
   * Retrieves the user name by user ID.
   *
   * @param userId the ID of the user
   * @return the name of the user
   */
  public String getUserName(final Integer userId) {
    String response = "NA";
    UserOutDto userOutDto = null;
    try {
      userOutDto = userFClient.getUserById(userId);
      response = userOutDto.getName();
    } catch (Exception e) {
      System.out.println(e);
    }
    return response;
  }

  /**
   * Converts a list of cart IDs into a string of order details.
   * Each cart item is represented as 'foodItemId:quantity'.
   *
   * @param cartIds the list of cart IDs
   * @return a string representing the order details
   */
  public String toOrderDetails(final List<Integer> cartIds) {
    StringBuilder result = new StringBuilder();
    for (Integer cartId : cartIds) {
      Optional<Cart> optionalCart = cartRepo.findById(cartId);
      if (!optionalCart.isPresent()) {
        log.warn("Cart with ID {} not found", cartId);
        continue;
      }
      Cart cart = optionalCart.get();
      result.append(cart.getFoodItemId()).append(":").append(cart.getQuantity()).append(",");
    }
    if (result.length() > 0) {
      result.setLength(result.length() - 1);  // Remove the last comma
    }
    System.out.println(result);
    log.info("Order details converted: {}", result.toString());
    return result.toString();
  }

  /**
   * Converts a string of order details into a list of OrderItemDetailOutDto.
   * Each entry in the string is parsed and mapped to a DTO.
   *
   * @param orderDetails the string representing the order details
   * @return a list of OrderItemDetailOutDto
   */
  public List<OrderItemDetailOutDto> orderDetailsForOrderOutDto(final String orderDetails) {
    List<OrderItemDetailOutDto> itemDetails = new ArrayList<>();
    String[] items = orderDetails.split(",");

    for (String item : items) {
      log.warn("Skipping empty or malformed order detail: {}", item);
      if (item.trim().isEmpty()) {
        continue; // Skip empty or malformed entries
      }
      String[] parts = item.split(":");
      if (parts.length != 2) {
        log.error("Invalid order details format for item: {}", item);
        continue; // Skip invalid format
      }

      Integer foodItemId;
      Integer quantity;

      try {
        foodItemId = Integer.parseInt(parts[0].trim());
        quantity = Integer.parseInt(parts[1].trim());
      } catch (NumberFormatException e) {
        log.error("Error parsing order detail for item: {} - {}", item, e.getMessage());
        continue;
      }

      try {
        ResponseEntity<FoodItemNameOutDto> responseEntity = restaurantFClient.getFoodItemName(foodItemId);
        FoodItemNameOutDto foodItemNameOutDto = responseEntity.getBody();

        // Log response details

        log.info("Response from Feign client for foodItemId {}: {}", foodItemId, responseEntity);

        if (foodItemNameOutDto != null && foodItemNameOutDto.getFoodItemName() != null) {
          OrderItemDetailOutDto detailOutDto = new OrderItemDetailOutDto();
          detailOutDto.setFoodItemId(foodItemId);
          detailOutDto.setFoodItemName(foodItemNameOutDto.getFoodItemName());
          detailOutDto.setQuantity(quantity);
          detailOutDto.setPrice(foodItemNameOutDto.getPrice());
          itemDetails.add(detailOutDto);
        } else {
          log.warn("Food item name is null for ID: {}", foodItemId);
        }
      } catch (Exception e) {
        log.error("Error fetching food item name for ID: {} - {}", foodItemId, e.getMessage());
      }
    }
    return itemDetails;
  }

  /**
   * Creates a new order based on the provided OrderInDto.
   * Validates user balance, creates the order, deducts money from the user, and deletes cart entries.
   *
   * @param orderInDto the DTO containing order details
   */
  public void createOrder(final OrderInDto orderInDto) {
    UserOutDto userOutDto = null;
    try {
      userOutDto = userFClient.getUserById(orderInDto.getUserId());
    } catch (Exception e) {
      log.error("User not found: {}", e.getMessage());
      throw new UserNotFound();
    }

    if (userOutDto.getRole() == Role.OWNER) {
      throw new InvalidOperation(Constant.OWNER_NOT_ALLOWED);
    }

    if (userOutDto != null && userOutDto.getWalletBalance() < orderInDto.getTotalAmount()) {
      log.error("Insufficient balance for user ID: {}. Order total: {}", orderInDto.getUserId(), orderInDto.getTotalAmount());
      throw new InsufficientBalance();
    }

    Order order = DtoConversion.mapToOrder(orderInDto);
    order.setOrderDetails(toOrderDetails(orderInDto.getCartIds()));

    AmountInDto amountInDto = new AmountInDto();
    System.out.println(order.getTotalAmount());
    amountInDto.setMoney(order.getTotalAmount());
    // Call Feign client to refund the money
    userFClient.deductMoneyFromUser(order.getUserId(), amountInDto);
    orderRepo.save(order);
    //once the order is placed we delete the corresponding cart entries
    log.info("Deducting money from user ID: {}. Amount: {}", order.getUserId(), amountInDto.getMoney());
    for (Integer cartId : orderInDto.getCartIds()) {
      if (cartRepo.existsById(cartId)) {
        log.info("Deleted cart entry with ID: {}", cartId);
        cartRepo.deleteById(cartId);  // Delete if cart exists
      } else {
        log.warn("Cart with ID {} does not exist, skipping deletion", cartId);
      }
    }
  }
  /**
   * Retrieves the details of an order by its ID.
   *
   * @param orderId the ID of the order
   * @return the OrderOutDto containing order details
   */
  public OrderOutDto getOrder(final Integer orderId) {
    Optional<Order> optionalOrder = orderRepo.findById(orderId);
    if (!optionalOrder.isPresent()) {
      log.error("Order not found with ID: {}", orderId);
      throw new OrderNotFound();
    }
    Order order = optionalOrder.get();
    OrderOutDto orderOutDto = DtoConversion.mapToOrderOutDto(order);
    orderOutDto.setOrderDetails(orderDetailsForOrderOutDto(order.getOrderDetails()));
    orderOutDto.setAddressName(getAddress(orderOutDto.getAddressId()));
    orderOutDto.setRestaurantName(getRestaurantName(orderOutDto.getRestaurantId()));
    orderOutDto.setUserName(getUserName(orderOutDto.getUserId()));
    log.info("Retrieved order with ID: {}", orderId);
    return orderOutDto;
  }

  /**
   * Updates the status of an order based on the provided order ID and status update details.
   * If the status is set to CANCELLED and the time difference between the current time and the
   * order's last update exceeds 30 seconds, a session expiration exception is thrown. Otherwise,
   * the refund amount is processed and added back to the user's account.
   *
   * @param orderId the ID of the order to be updated
   * @param updateStatusInDto the DTO containing the new status for the order
   * @throws OrderNotFound if the order with the specified ID does not exist
   * @throws UserNotFound if the user associated with the order cannot be found
   * @throws SessionExpiredException if the order is attempted to be cancelled after the allowed time
   */
  public void updateStatus(final Integer orderId, final UpdateStatusInDto updateStatusInDto) {
    log.info("Updating status for order ID: {}", orderId);
    Optional<Order> optionalOrder = orderRepo.findById(orderId);
    if (!optionalOrder.isPresent()) {
      log.error("Order not found with ID: {}", orderId);
      throw new OrderNotFound();
    }
    System.out.println("2 service");
    Order order = optionalOrder.get();
    Integer userId = order.getUserId();
    UserOutDto userOutDto = null;
    try {
      userOutDto = userFClient.getUserById(userId);
    } catch (Exception e) {
      log.error("User not found: {}", e.getMessage());
      throw new UserNotFound();
    }
    if (updateStatusInDto.getOrderStatus() == OrderStatus.CANCELLED) {
      long timeDifferenceInSeconds = Duration.between(order.getLastUpdatedAt(), LocalDateTime.now()).getSeconds();
      // Check if the difference is greater than 30 seconds
      if (timeDifferenceInSeconds > 30) {
        log.error("Session expired for order ID: {}", orderId);
        throw new SessionExpiredException();
      }
      AmountInDto amountInDto = new AmountInDto();
      amountInDto.setMoney(order.getTotalAmount());
      log.info("Refunding amount to user ID: {}", userId);
      userFClient.addMoneyToUser(order.getUserId(), amountInDto);
    }
    order.setOrderStatus(updateStatusInDto.getOrderStatus());
    orderRepo.save(order);
    log.info("Order status updated successfully for ID: {}", orderId);
  }

  /**
   * Retrieves a list of orders placed by a specific user.
   *
   * @param userId the ID of the user
   * @return a list of OrderOutDto for the user
   */
  public List<OrderOutDto> getOrdersByUser(final Integer userId) {
    List<Order> orderList = orderRepo.findByUserId(userId);
    List<OrderOutDto> orderOutDtoList = new ArrayList<>();
    for (Order order : orderList) {
      OrderOutDto orderOutDto = DtoConversion.mapToOrderOutDto(order);
      orderOutDto.setOrderDetails(orderDetailsForOrderOutDto(order.getOrderDetails()));
      orderOutDtoList.add(orderOutDto);
    }
    log.info("Retrieved {} orders for user ID: {}", orderOutDtoList.size(), userId);
    return orderOutDtoList;
  }



  /**
   * Retrieves a list of orders associated with a specific restaurant.
   *
   * @param restaurantId the ID of the restaurant
   * @return a list of OrderOutDto for the restaurant
   */
  public List<OrderOutDto> getOrdersByRestaurant(final Integer restaurantId) {
    List<Order> orderList = orderRepo.findByRestaurantId(restaurantId);
    List<OrderOutDto> orderOutDtoList = new ArrayList<>();
    for (Order order : orderList) {
      OrderOutDto orderOutDto = DtoConversion.mapToOrderOutDto(order);
      orderOutDto.setOrderDetails(orderDetailsForOrderOutDto(order.getOrderDetails()));
      orderOutDtoList.add(orderOutDto);
    }
    log.info("Retrieved {} orders for restaurant ID: {}", orderOutDtoList.size(), restaurantId);
    return orderOutDtoList;
  }

}
