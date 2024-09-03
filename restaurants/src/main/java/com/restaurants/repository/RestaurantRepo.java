package com.restaurants.repository;

import com.restaurants.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {
  /**
   * Finds all restaurants for a given user.
   *
   * @param userId the ID of the user
   * @return a list of restaurants for the specified user
   */
  List<Restaurant> findByUserId(Integer userId);
}
