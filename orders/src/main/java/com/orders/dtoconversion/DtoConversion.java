package com.orders.dtoconversion;

import com.orders.dto.CartInDto;
import com.orders.dto.OrderInDto;
import com.orders.dto.CartOutDto;
import com.orders.dto.OrderOutDto;
import com.orders.entities.Cart;
import com.orders.entities.Order;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class DtoConversion {

  /**
   * Converts a {@link CartInDto} to a {@link Cart} entity.
   *
   * @param cartInDto The input DTO containing cart details.
   * @return The corresponding {@link Cart} entity.
   */
  public static Cart mapToCart(final CartInDto cartInDto) {
    log.debug("Converting CartInDto to Cart: {}", cartInDto);
    Cart cart = new Cart();
    cart.setUserId(cartInDto.getUserId());
    cart.setPrice(cartInDto.getPrice());
    cart.setQuantity(cartInDto.getQuantity());
    cart.setRestaurantId(cartInDto.getRestaurantId());
    cart.setFoodItemId(cartInDto.getFoodItemId());
    log.debug("Converted Cart: {}", cart);
    return cart;
  }

  /**
   * Converts a {@link Cart} entity to a {@link CartOutDto}.
   *
   * @param cart The input entity containing cart details.
   * @return The corresponding {@link CartOutDto}.
   */
  public static CartOutDto mapToCartOutDto(final Cart cart) {
    log.info("Converting Cart to CartOutDto: {}", cart);
    CartOutDto cartOutDto = new CartOutDto();
    cartOutDto.setId(cart.getId());
    cartOutDto.setPrice(cart.getPrice());
    cartOutDto.setQuantity(cart.getQuantity());
    cartOutDto.setUserId(cart.getUserId());
    cartOutDto.setRestaurantId(cart.getRestaurantId());
    cartOutDto.setFoodItemId(cart.getFoodItemId());
    log.info("Converted CartOutDto: {}", cartOutDto);
    return cartOutDto;
  }

  /**
   * Converts an {@link Order} entity to an {@link OrderOutDto}.
   *
   * @param order The input entity containing order details.
   * @return The corresponding {@link OrderOutDto}.
   */
  public static OrderOutDto mapToOrderOutDto(final Order order) {
    log.debug("Converting Order to OrderOutDto: {}", order);
    OrderOutDto orderOutDto = new OrderOutDto();
    orderOutDto.setId(order.getId());
    orderOutDto.setUserId(order.getUserId());
    orderOutDto.setRestaurantId(order.getRestaurantId());
    orderOutDto.setAddressId(order.getAddressId());
    orderOutDto.setOrderStatus(order.getOrderStatus());
    //orderOutDto.setOrderDetails(order.getOrderDetails());
    //we will handle in service
    orderOutDto.setCreatedAt(order.getLastUpdatedAt());
    log.debug("Converted OrderOutDto: {}", orderOutDto);
    return orderOutDto;
  }

  /**
   * Converts an {@link OrderInDto} to an {@link Order} entity.
   *
   * @param orderInDto The input DTO containing order details.
   * @return The corresponding {@link Order} entity.
   */
  public static Order mapToOrder(final OrderInDto orderInDto) {
    log.debug("Converting OrderInDto to Order: {}", orderInDto);
    Order order = new Order();
    order.setUserId(orderInDto.getUserId());
    order.setRestaurantId(orderInDto.getRestaurantId());
    order.setAddressId(orderInDto.getAddressId());
    //we handle the follwing in service
    //order.setOrderDetails(toOrderDetails(orderInDto.getCartIds()));
    order.setTotalAmount(orderInDto.getTotalAmount());
    order.setOrderStatus(orderInDto.getOrderStatus());
    order.setLastUpdatedAt(LocalDateTime.now());
    log.debug("Converted Order: {}", order);
    return order;
  }

}
