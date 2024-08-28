package com.restaurants.repository;

import com.restaurants.entities.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepo extends JpaRepository<FoodItem,Integer> {
}
