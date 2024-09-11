package com.orders.dto;

import com.sun.istack.NotNull;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for capturing input related to a cart entry.
 * This includes details about the user, restaurant, food item, quantity, and price.
 */
@Data
public class CartInDto {
  /**
   * The ID of the user associated with the cart.
   * This field is required and cannot be null.
   */
  @NotNull
  private Integer userId;

  /**
   * The ID of the restaurant where the food item is being ordered.
   * This field is required and cannot be null.
   */
  @NotNull
  private Integer restaurantId;

  /**
   * The ID of the food item being added to the cart.
   * This field is required and cannot be null.
   */
  @NotNull
  private Integer foodItemId;

  /**
   * The quantity of the food item being added to the cart.
   * This field is required and cannot be null.
   */
  @NotNull
  private Integer quantity;

  /**
   * The price of the food item.
   * This field is required and cannot be null.
   */
  @NotNull
  private Double price;

  /**
   * Default constructor for CartInDto.
   */
  public CartInDto() {
  }
}
