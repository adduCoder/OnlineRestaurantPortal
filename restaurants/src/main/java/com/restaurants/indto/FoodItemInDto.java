package com.restaurants.indto;

import lombok.Data;
/**
 * DTO for creating or updating a food item.
 */
@Data
public class FoodItemInDto {
  /**
   * Name of the food item.
   */
  private String foodName;
  /**
   * ID of the restaurant where the food item is available.
   */
  private Integer restaurantId;
  /**
   * Description of the food item.
   */
  private String description;
  /**
   * ID of the category to which the food item belongs.
   */
  private Integer categoryId;
  /**
   * Availability status of the food item.
   */
  private Boolean isAvailable;
  /**
   * Price of the food item.
   */
  private Double price;
  /**
   * URL of the food item's image.
   */
  private String imageUrl;
}
