package com.restaurants.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for updating category information.
 * This DTO contains fields necessary for updating a category and includes validation constraints.
 */
@Data
public class CategoryUpdateInDto {
  /**
   * The name of the category to be updated.
   * Must not be blank, must be at least 3 characters long, and can only contain alphabets and spaces.
   */
  @NotBlank(message = "Food name cannot be blank")
  @Size(min = 3, message = "Food name must be at least 3 characters long")
  @Pattern(regexp = "^[a-zA-Z ]+$", message = "Food name must contain only alphabets and spaces")
  private String name;
}
