package com.restaurants.dto;

import lombok.Data;

@Data
public class RestaurantOutDto {
  /**
   * Unique identifier for the restaurant.
   */
  private Integer id;
  /**
   * Identifier of the user who owns the restaurant.
   */
  private Integer userId;
  /**
   * Name of the restaurant.
   */
  private String restaurantName;
  /**
   * Address of the restaurant.
   */
  private String address;
  /**
   * Contact number of the restaurant.
   */
  private String contactNumber;

  /**
   * Description of the restaurant.
   */
  private String description;
  /**
   * URL of the restaurant's image.
   */
  private byte[] imageData;
}
