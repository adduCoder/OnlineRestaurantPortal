package com.restaurants.repository;

import com.restaurants.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
  /**
   * Finds all categories for a given restaurant.
   *
   * @param restaurantId the ID of the restaurant
   * @return a list of categories for the specified restaurant
   */
  List<Category> findAllByRestaurantId(Integer restaurantId);

  Optional<Category> findByNameAndRestaurantId(String categoryName, Integer restaurantId);
}
