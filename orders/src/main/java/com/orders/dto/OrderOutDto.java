package com.orders.dto;

import com.orders.entities.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object (DTO) for representing the details of an order in the response.
 */
@Data
public class OrderOutDto {
  /**
   * The unique identifier of the order.
   */
  private Integer id;
  /**
   * The ID of the user who placed the order.
   */
  private Integer userId;
  //we will send the address in string
  /**
   * The ID of the address where the order is to be delivered.
   * Note: The address is sent as a string.
   */
  private Integer addressId;

  /**
   * The ID of the restaurant where the order is placed.
   */
  private Integer restaurantId;

  /**
   * The current status of the order.
   */
  private OrderStatus orderStatus;
  // private String orderDetails;
  // Use a Map for order details
  /**
   * The details of the items in the order.
   * Each item includes information about the food item and its quantity.
   */
  private List<OrderItemDetailOutDto> orderDetails;  // Key: foodItem, Value: quantity


  /**
   * The date and time when the order was created.
   */
  private LocalDateTime createdAt;
  private String restaurantName;
  private String addressName;
  private String userName;
}
