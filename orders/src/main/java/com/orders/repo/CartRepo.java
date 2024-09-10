package com.orders.repo;

import com.orders.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart, Integer> {
  Optional<Cart> findByUserIdAndRestaurantIdAndFoodItemId(Integer userId, Integer restaurantId, Integer foodItemId);

  List<Cart> findByUserIdAndRestaurantId(Integer userId, Integer restaurantId);
}
