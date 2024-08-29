package com.restaurants.repository;

import com.restaurants.entities.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodItemRepo extends JpaRepository<FoodItem, Integer> {
  List<FoodItem> findAllByRestaurantId(Integer restaurantId);
}
