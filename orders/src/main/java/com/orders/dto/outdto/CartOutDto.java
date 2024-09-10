package com.orders.dto.outdto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for representing the details of a cart item in the response.
 */
@Data
public class CartOutDto {
  /**
   * The unique identifier of the cart item.
   */
  private Integer id;

  /**
   * The ID of the user associated with the cart item.
   */
  private Integer userId;

  /**
   * The ID of the restaurant where the food item is ordered from.
   */
  private Integer restaurantId;

  /**
   * The ID of the food item in the cart.
   */
  private Integer foodItemId;

  /**
   * The quantity of the food item in the cart.
   */
  private Integer quantity;

  /**
   * The price of the food item.
   */
  private Double price;
}
