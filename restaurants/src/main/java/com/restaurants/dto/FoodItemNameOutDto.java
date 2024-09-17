package com.restaurants.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for providing the basic details of a food item.
 * This DTO is used to transfer the food item name, ID, and price.
 */
@Data
public class FoodItemNameOutDto {
  /**
   * The unique identifier of the food item.
   */
  private Integer id;

  /**
   * The name of the food item.
   */
  private String foodItemName;

  /**
   * The price of the food item.
   */
  private Double price;
}
