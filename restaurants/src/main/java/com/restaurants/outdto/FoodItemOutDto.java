package com.restaurants.outdto;

import lombok.Data;

@Data
public class FoodItemOutDto {
   private Integer id;
   private String foodName;
   private Integer restaurantId;
   private String description;
   private Integer categoryId;
   private Boolean isAvailable;
   private Double price;
}
