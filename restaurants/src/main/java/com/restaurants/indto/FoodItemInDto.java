package com.restaurants.indto;

import lombok.Data;

@Data
public class FoodItemInDto {
  private String foodName;
  private Integer restaurantId;
  private String description;
  private Integer categoryId;
  private Boolean isAvailable;
  private Double price;
  private String imageUrl;
}
