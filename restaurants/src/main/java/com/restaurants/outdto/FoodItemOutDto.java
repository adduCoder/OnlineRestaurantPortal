package com.restaurants.outdto;

import lombok.Data;


@Data
public class FoodItemOutDto {

  /**
   * Unique identifier for the food item.
   */
  private Integer id;
  /**
   * Name of the food item.
   */
  private String foodName;
  /**
   * Name of the restaurant offering the food item.
   */
  private String restaurantName;

  /**
   * Description of the food item.
   */
  private String description;
  /**
   * Name of the category to which the food item belongs.
   */
  private String categoryName;
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
  private byte[] imageData;
}
