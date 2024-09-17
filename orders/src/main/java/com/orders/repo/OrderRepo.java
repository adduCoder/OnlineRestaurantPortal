package com.orders.repo;

import com.orders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for accessing {@link Order} entities.
 * <p>
 * This interface extends {@link JpaRepository} to provide CRUD operations and query methods
 * for {@link Order} entities. It includes methods for finding orders based on user ID and restaurant ID.
 * </p>
 */
public interface OrderRepo extends JpaRepository<Order, Integer> {

  /**
   * Finds a list of {@link Order} entities by user ID.
   *
   * @param userId the ID of the user
   * @return a list of {@link Order} entities associated with the given user ID
   */
  List<Order> findByUserId(Integer userId);

  /**
   * Finds a list of {@link Order} entities by restaurant ID.
   *
   * @param restaurantId the ID of the restaurant
   * @return a list of {@link Order} entities associated with the given restaurant ID
   */
  List<Order> findByRestaurantId(Integer restaurantId);
}
