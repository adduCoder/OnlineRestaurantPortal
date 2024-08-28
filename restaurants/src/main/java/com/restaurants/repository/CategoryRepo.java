package com.restaurants.repository;

import com.restaurants.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
  List<Category> findAllByRestaurantId(Integer restaurantId);
}
