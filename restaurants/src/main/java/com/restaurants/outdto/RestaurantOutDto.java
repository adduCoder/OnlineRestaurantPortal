package com.restaurants.outdto;

import lombok.Data;

@Data
public class RestaurantOutDto {
  private Integer id;
  private Integer userId;
  private String restaurantName;
  private String address;
  private String contactNumber;
  private String description;
  private String imageUrl;
}
