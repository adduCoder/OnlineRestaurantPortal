package com.orders.service;

import com.orders.dto.FoodItemNameOutDto;
import com.orders.dto.RestaurantOutDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client for interacting with the restaurant service.
 * This client communicates with the restaurant service to fetch details
 * about food items and restaurants.
 */
@FeignClient(name = "restaurant-service", url = "http://localhost:8081")
public interface RestaurantFClient {


  /**
   * Retrieves the name and details of a food item based on the provided food item ID.
   *
   * @param foodItemId the ID of the food item to retrieve
   * @return a ResponseEntity containing the details of the food item, or an error response
   */
  @GetMapping("/foodItem/getName/{foodItemId}")
  ResponseEntity<FoodItemNameOutDto> getFoodItemName(@PathVariable("foodItemId") Integer foodItemId);

  /**
   * Retrieves the details of a restaurant based on the provided restaurant ID.
   *
   * @param restaurantId the ID of the restaurant to retrieve
   * @return a ResponseEntity containing the details of the restaurant, or an error response
   */
  @GetMapping("/restaurant/getRestaurant/{restaurantId}")
  ResponseEntity<RestaurantOutDto> getRestaurantById(@PathVariable Integer restaurantId);
}
