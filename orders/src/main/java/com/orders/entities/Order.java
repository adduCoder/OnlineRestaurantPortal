package com.orders.entities;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Entity class representing an Order in the system.
 * Contains details about the order including user, restaurant, address, order details, status, and timestamps.
 */
@Entity
@Data
@Table(name = "orders")
public class Order {

  /**
   * Unique identifier for the order.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  /**
   * ID of the user who placed the order.
   */
  private Integer userId;

  /**
   * ID of the restaurant associated with the order.
   */
  private Integer restaurantId;

  /**
   * ID of the address where the order should be delivered.
   */
  private Integer addressId;

  /**
   * Details of the order, such as food items and their quantities.
   */
  private String orderDetails;

  /**
   * Timestamp of when the order was last updated.
   */
  private LocalDateTime lastUpdatedAt;

  /**
   * Total amount for the order.
   */
  private Double totalAmount;

  /**
   * Status of the order, represented as an enumeration.
   */
  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;
}
