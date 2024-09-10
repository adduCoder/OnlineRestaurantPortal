package com.orders.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity class representing a Cart in the system.
 * Contains details about the cart including the user, restaurant, food item, quantity, and price.
 */
@Entity
@Data
public class Cart {
  /**
   * Unique identifier for the cart.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * ID of the user who owns the cart.
   */
  private Integer userId;

  /**
   * ID of the restaurant associated with the cart.
   */
  private Integer restaurantId;

  /**
   * ID of the food item in the cart.
   */
  private Integer foodItemId;

  /**
   * Quantity of the food item in the cart.
   */
  private Integer quantity;

  /**
   * Price of the food item in the cart.
   */
  private Double price;

}
