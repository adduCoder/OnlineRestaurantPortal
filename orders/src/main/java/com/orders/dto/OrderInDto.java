package com.orders.dto;

import com.orders.entities.OrderStatus;
import com.sun.istack.NotNull;
import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object (DTO) for capturing input related to an order.
 * This includes details about the user, restaurant, address, cart items, total amount, and order status.
 */
@Data
public class OrderInDto {

  /**
   * The ID of the user placing the order.
   * This field is required and cannot be null.
   */
  @NotNull
  private Integer userId;

  /**
   * The ID of the restaurant where the order is placed.
   * This field is required and cannot be null.
   */
  @NotNull
  private Integer restaurantId;

  /**
   * The ID of the address where the order will be delivered.
   * This field is required and cannot be null.
   */
  @NotNull
  private Integer addressId;

  /**
   * The list of cart IDs associated with the order.
   * This field is required and cannot be null.
   */
  @NotNull
  private List<Integer> cartIds;


  /**
   * The total amount for the order.
   * This field is required and cannot be null.
   */
  @NotNull
  private Double totalAmount;

  /**
   * The status of the order.
   * This field is required and cannot be null.
   */
  @NotNull
  private OrderStatus orderStatus;

  /**
   * Default constructor for OrderInDto.
   */
  public OrderInDto() {
  }
}
