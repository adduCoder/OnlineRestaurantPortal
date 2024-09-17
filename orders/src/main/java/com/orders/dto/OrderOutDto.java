package com.orders.dto;

import com.orders.entities.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Transfer Object (DTO) for representing the details of an order in the response.
 * <p>
 * This class encapsulates all the details related to an order that will be included in the response.
 * It includes information about the order's ID, user, address, restaurant, status, details of items in the order,
 * and additional metadata like creation time and names.
 * </p>
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
  /**
   * The details of the items in the order.
   * Each item includes information about the food item and its quantity.
   */
  private List<OrderItemDetailOutDto> orderDetails;  // Key: foodItem, Value: quantity


  /**
   * The date and time when the order was created.
   */
  private LocalDateTime createdAt;

  /**
   * The name of the restaurant where the order was placed.
   * <p>
   * This field provides the name of the restaurant associated with the order.
   * </p>
   */
  private String restaurantName;

  /**
   * The name of the address where the order is to be delivered.
   * <p>
   * This field provides the name or description of the delivery address.
   * </p>
   */
  private String addressName;

  /**
   * The name of the user who placed the order.
   * <p>
   * This field provides the name of the user associated with the order.
   * </p>
   */
  private String userName;
}
