package com.restaurants.repository;

import com.restaurants.entities.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodItemRepo extends JpaRepository<FoodItem, Integer> {
  /**
   * Finds all food items for a given restaurant.
   *
   * @param restaurantId the ID of the restaurant
   * @return a list of food items for the specified restaurant
   */
  List<FoodItem> findAllByRestaurantId(Integer restaurantId);
}
