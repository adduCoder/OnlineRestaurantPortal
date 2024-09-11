package com.orders.dto;

import lombok.Data;


/**
 * Data Transfer Object (DTO) for capturing input related to retrieving all carts for a specific restaurant and user.
 */
@Data
public class AllCartInDto {
  /**
   * The ID of the restaurant.
   */
  private Integer restaurantId;
  /**
   * The ID of the user.
   */
  private Integer userId;
}
