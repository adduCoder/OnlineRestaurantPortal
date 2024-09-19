package com.restaurants.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class RestaurantInDto {
  /**
   * User ID associated with the restaurant.
   */
  @NotNull(message = "User id cannot be blank")
  private Integer userId;

  /**
   * Name of the restaurant.
   */
  @NotBlank(message = "Name cannot be blank")
  @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only alphabets and spaces")
  private String restaurantName;

  /**
   * Address of the restaurant.
   */
  @NotNull(message = "Address cannot be blank")
  private String address;

  /**
   * Contact number of the restaurant.
   */
  @NotBlank(message = "Phone number cannot be blank")
  @Pattern(regexp = "^[7896]\\d{9}$", message = "Phone number must be exactly 10 digits and start with 7, 8, 9, or 6")
  private String contactNumber;

  /**
   * Description of the restaurant.
   */
  @NotBlank(message = "Description cannot be blank")
  @Pattern(regexp = "^[a-zA-Z ]+$", message = "Description must contain only alphabets and spaces")
  private String description;

}
