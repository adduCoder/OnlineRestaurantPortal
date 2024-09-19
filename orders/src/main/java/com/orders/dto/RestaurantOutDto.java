package com.orders.dto;

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

  /**
   * Default constructor for creating a new {@code RestaurantOutDto}.
   * <p>
   * This constructor initializes a new instance of the DTO with all fields set to their default values
   * (e.g., {@code null} for objects, {@code 0} for numeric types).
   * </p>
   */
  public RestaurantOutDto() { }


  /**
   * Constructs a new {@code RestaurantOutDto} with the specified restaurant name.
   * <p>
   * This constructor is used to create a DTO with the given restaurant name. The other fields
   * (such as ID, user ID, address, contact number, description, and image data) will be initialized to their default values.
   * </p>
   *
   * @param restaurantName the name of the restaurant to be set.
   */
  public RestaurantOutDto(final String restaurantName) {
    this.restaurantName = restaurantName;
  }
}
