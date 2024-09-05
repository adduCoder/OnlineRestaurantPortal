package com.restaurants.dto.indto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;

/**
 * DTO for creating or updating a food item.
 */
@Data
public class FoodItemInDto {

  /**
   * Name of the food item.
   * <p>
   * This field must not be blank, must be at least 3 characters long, and can only contain alphabets and spaces.
   * </p>
   */
  @NotBlank(message = "Food name cannot be blank")
  @Size(min = 3, message = "Food name must be at least 3 characters long")
  @Pattern(regexp = "^[a-zA-Z ]+$", message = "Food name must contain only alphabets and spaces")
  private String foodName;

  /**
   * ID of the restaurant where the food item is available.
   * <p>
   * This field must not be null.
   * </p>
   */
  @NotNull(message = "Restaurant ID cannot be null")
  private Integer restaurantId;

  /**
   * Description of the food item.
   * <p>
   * This field must not be blank.
   * </p>
   */
  @NotBlank(message = "Description cannot be blank")
  private String description;

  /**
   * ID of the category to which the food item belongs.
   * <p>
   * This field must not be null.
   * </p>
   */
  @NotNull(message = "Category ID cannot be null")
  private Integer categoryId;

  /**
   * Availability status of the food item.
   * <p>
   * This field must not be null.
   * </p>
   */
  @NotNull(message = "Availability status cannot be null")
  private Boolean isAvailable;

  /**
   * Price of the food item.
   * <p>
   * This field must not be null and must be a positive value.
   * </p>
   */
  @NotNull(message = "Price cannot be null")
  @Min(value = 0, message = "Price cannot be negative")
  private Double price;
}
