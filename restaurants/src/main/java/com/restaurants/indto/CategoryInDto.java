package com.restaurants.indto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
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
  private Integer restaurantId;
  /**
   * The name of the category.
   * <p>
   * This field represents the name of the category that will be created or updated.
   * </p>
   */
  @NotBlank(message = "Name cannot be blank")
  @Size(min = 3, message = "Name must be at least 3 characters long")
  @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only alphabets")
  private String name;
}
