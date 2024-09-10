package com.orders.dto.indto;

import com.orders.entities.OrderStatus;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for capturing input related to updating the status of an order.
 */
@Data
public class UpdateStatusInDto {
  /**
   * The new status to set for the order.
   */
  private OrderStatus orderStatus;
}
