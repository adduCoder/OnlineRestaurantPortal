package com.orders.service;

import com.orders.dto.CartInDto;
import com.orders.dto.CartOutDto;
import com.orders.dto.FoodItemNameOutDto;
import com.orders.dto.RestaurantOutDto;
import com.orders.dtoconversion.DtoConversion;
import com.orders.entities.Cart;
import com.orders.repo.CartRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing cart operations.
 * <p>
 * This service provides methods to add or update cart items, and retrieve cart details
 * including information about the restaurant and food items.
 * </p>
 */
@Slf4j
@Service
public class CartService {

  /**
   * Repository for performing CRUD operations on Cart entities.
   */
  @Autowired
  private CartRepo cartRepo;

  /**
   * Feign client for interacting with the restaurant microservice.
   */
  @Autowired
  private RestaurantFClient restaurantFClient;

  /**
   * Retrieves the name of a restaurant by its ID using a Feign client.
   * <p>
   * This method communicates with the restaurant microservice to fetch the restaurant's name.
   * </p>
   *
   * @param restaurantId the ID of the restaurant
   * @return the name of the restaurant, or "No Name Available" if the name cannot be retrieved
   */
  public String restaurantName(final Integer restaurantId) {
    String response = "No Name Available";
    RestaurantOutDto restaurantOutDto = null;
    try {
      restaurantOutDto = restaurantFClient.getRestaurantById(restaurantId).getBody();
      response = restaurantOutDto.getRestaurantName();
    } catch (Exception e) {
      log.info("exception in fetching data from restor microservice using fclient");
    }
    return response;
  }

  /**
   * Retrieves the name of a food item by its ID using a Feign client.
   * <p>
   * This method communicates with the restaurant microservice to fetch the food item's name.
   * </p>
   *
   * @param foodItemId the ID of the food item
   * @return the name of the food item, or "No Name Available" if the name cannot be retrieved
   */
  public String foodItemName(final Integer foodItemId) {
    String response = "No Name Available";
    FoodItemNameOutDto foodItemNameOutDto = null;
    try {
      foodItemNameOutDto = restaurantFClient.getFoodItemName(foodItemId).getBody();
      response = foodItemNameOutDto.getFoodItemName();
    } catch (Exception e) {
      log.info("exception in fetching data from restro microservice using fclient");
    }
    return response;
  }



  /**
   * Adds a new cart item or updates an existing cart item with the provided details.
   * <p>
   * If a cart item already exists for the given user, restaurant, and food item,
   * the quantity is updated. Otherwise, a new cart item is created.
   * </p>
   *
   * @param cartInDto the DTO containing cart item details to be added or updated
   * @return the ID of the cart item
   */
  public Integer addOrUpdateCart(final CartInDto cartInDto) {
    log.info("Attempting to add or update cart item: {}", cartInDto);
    Optional<Cart> existingCartOpt = cartRepo.findByUserIdAndRestaurantIdAndFoodItemId(
      cartInDto.getUserId(), cartInDto.getRestaurantId(), cartInDto.getFoodItemId());

    Integer cartId = null;
    if (existingCartOpt.isPresent()) {
      // If cart item exists, update with the exact quantity from the DTO (instead of incrementing)
      Cart existingCart = existingCartOpt.get();
      existingCart.setQuantity(cartInDto.getQuantity());
      cartRepo.save(existingCart);
      cartId = existingCart.getUserId();
      log.info("Updated existing cart item with ID: {}", cartId);
    } else {
      // Otherwise, add new cart item
      Cart savedCart = cartRepo.save(DtoConversion.mapToCart(cartInDto));
      cartId = savedCart.getId();
      log.info("Added new cart item with ID: {}", cartId);
    }
    return cartId;
  }

  /**
   * Retrieves all cart items for the specified user and restaurant.
   * <p>
   * This method fetches cart items from the repository and maps them to DTOs.
   * It also enriches the DTOs with restaurant and food item names.
   * </p>
   *
   * @param userId the ID of the user
   * @param restaurantId the ID of the restaurant
   * @return a list of DTOs representing cart items
   */
  public List<CartOutDto> getAllCart(final Integer userId, final Integer restaurantId) {
    log.info("Retrieving all cart items for user ID: {} and restaurant ID: {}", userId, restaurantId);
    List<Cart> cartList = cartRepo.findByUserIdAndRestaurantId(userId, restaurantId);
    List<CartOutDto> cartOutDtoList = new ArrayList<>();
    for (Cart cart : cartList) {
      CartOutDto cartOutDto = DtoConversion.mapToCartOutDto(cart);
      System.out.println(restaurantName(cartOutDto.getRestaurantId()));
      cartOutDto.setRestaurantName(restaurantName(cartOutDto.getRestaurantId()));
      cartOutDto.setFoodItemName(foodItemName(cartOutDto.getFoodItemId()));
      cartOutDtoList.add(cartOutDto);
    }
    log.info("Retrieved {} cart items", cartOutDtoList.size());
    return cartOutDtoList;
  }

}
