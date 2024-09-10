package com.restaurants.dto.indto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
  @NotBlank(message = "Food name cannot be blank")
  @Size(min = 3, message = "Food name must be at least 3 characters long")
  @Pattern(regexp = "^[a-zA-Z ]+$", message = "Food name must contain only alphabets and spaces")
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
  private String description;

}
