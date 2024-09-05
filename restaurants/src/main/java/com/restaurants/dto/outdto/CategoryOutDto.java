package com.restaurants.dto.outdto;

import lombok.Data;

@Data
public class CategoryOutDto {
  /**
   * Unique identifier for the category.
   */
  private Integer id;
  /**
   * ID of the restaurant to which the category belongs.
   */
  private Integer resturantId;
  /**
   * Name of the category.
   */
  private String name;
}
