package com.orders.repo;

import com.orders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {
  List<Order> findByUserId(Integer userId);

  List<Order> findByRestaurantId(Integer restaurantId);
}
