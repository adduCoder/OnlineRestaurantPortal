package com.orders.dto.outdto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for representing the details of an item within an order.
 */
@Data
public class OrderItemDetailOutDto {
  /**
   * The ID of the food item in the order.
   */
  private Integer foodItemId;

  /**
   * The name of the food item in the order.
   */
  private String foodItemName;

  /**
   * The quantity of the food item ordered.
   */
  private Integer quantity;

  /**
   * The price of the food item.
   */
  private Double price;
}
