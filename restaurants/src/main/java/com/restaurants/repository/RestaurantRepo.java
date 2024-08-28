package com.restaurants.repository;

import com.restaurants.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepo extends JpaRepository<Restaurant,Integer> {
  List<Restaurant> findByUserId(Integer userId);
}
