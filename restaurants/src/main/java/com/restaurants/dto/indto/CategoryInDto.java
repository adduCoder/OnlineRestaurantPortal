package com.restaurants.dto.indto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for category input.
 * <p>
 * This class is used to encapsulate the data needed to create or update a
 * category in the system. It includes the ID of the restaurant to which the
 * category belongs and the name of the category.
 * </p>
 */
@Data
public class CategoryInDto {
  /**
   * The ID of the restaurant to which this category belongs.
   * <p>
   * This field is used to associate the category with a specific restaurant.
   * </p>
   */
  @NotNull(message = "Category ID cannot be null")
  private Integer restaurantId;
  /**
   * The name of the category.
   * <p>
   * This field represents the name of the category that will be created or updated.
   * </p>
   */
  @NotBlank(message = "Food name cannot be blank")
  @Size(min = 3, message = "Food name must be at least 3 characters long")
  @Pattern(regexp = "^[a-zA-Z ]+$", message = "Food name must contain only alphabets and spaces")
  private String name;
}
