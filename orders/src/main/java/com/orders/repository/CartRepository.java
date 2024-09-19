package com.orders.repository;

import com.orders.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for accessing {@link Cart} entities.
 * <p>
 * This interface extends {@link JpaRepository} to provide CRUD operations and query methods
 * for {@link Cart} entities. It includes methods for finding carts based on user ID, restaurant
 * ID, and food item ID.
 * </p>
 */
public interface CartRepository extends JpaRepository<Cart, Integer> {
  /**
   * Finds a {@link Cart} by user ID, restaurant ID, and food item ID.
   *
   * @param userId the ID of the user
   * @param restaurantId the ID of the restaurant
   * @param foodItemId the ID of the food item
   * @return an {@link Optional} containing the matching {@link Cart} if found, or empty if not
   */
  Optional<Cart> findByUserIdAndRestaurantIdAndFoodItemId(Integer userId, Integer restaurantId, Integer foodItemId);

  /**
   * Finds a list of {@link Cart} entities by user ID and restaurant ID.
   *
   * @param userId the ID of the user
   * @param restaurantId the ID of the restaurant
   * @return a list of {@link Cart} entities matching the given user ID and restaurant ID
   */
  List<Cart> findByUserIdAndRestaurantId(Integer userId, Integer restaurantId);
}
