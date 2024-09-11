package com.restaurants.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CategoryUpdateInDto {
  @NotBlank(message = "Food name cannot be blank")
  @Size(min = 3, message = "Food name must be at least 3 characters long")
  @Pattern(regexp = "^[a-zA-Z ]+$", message = "Food name must contain only alphabets and spaces")
  private String name;
}
