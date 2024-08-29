package com.restaurants.outdto;

import lombok.Data;

@Data
public class FoodItemOutDto {
  private Integer id;
  private String foodName;
  private String restaurantName;
  private String description;
  private String categoryName;
  private Boolean isAvailable;
  private Double price;
  private String imageUrl;
}
