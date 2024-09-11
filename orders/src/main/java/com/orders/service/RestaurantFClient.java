package com.orders.service;

import com.orders.dto.AddressOutDto;
import com.orders.dto.FoodItemNameOutDto;
import com.orders.dto.RestaurantOutDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "restaurant-service", url = "http://localhost:8081")
public interface RestaurantFClient {

  @GetMapping("/foodItem/getName/{foodItemId}")
  ResponseEntity<FoodItemNameOutDto> getFoodItemName(@PathVariable("foodItemId") Integer foodItemId);

  @GetMapping("/restaurant/getRestaurant/{restaurantId}")
  ResponseEntity<RestaurantOutDto> getRestaurantById(@PathVariable Integer restaurantId);



}
