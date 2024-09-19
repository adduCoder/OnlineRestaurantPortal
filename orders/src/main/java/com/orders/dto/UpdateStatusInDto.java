package com.orders.dto;

import com.orders.entities.OrderStatus;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for capturing input related to updating the status of an order.
 * <p>
 * This class encapsulates the information needed to update the status of an existing order.
 * It includes the new status that should be set for the order.
 * </p>
 */
@Data
public class UpdateStatusInDto {
  /**
   * The new status to set for the order.
   * <p>
   * This field denotes the updated status that will be applied to the order.
   * </p>
   */

  private OrderStatus orderStatus;

  /**
   * Default constructor for UpdateStatusInDto.
   * <p>
   * Initializes a new instance of the UpdateStatusInDto class with default values.
   * </p>
   */
  public UpdateStatusInDto() {

  }

  /**
   * Constructor for UpdateStatusInDto with specified order status.
   * <p>
   * Initializes a new instance of the UpdateStatusInDto class with the provided order status.
   * </p>
   *
   * @param orderStatus The new status to set for the order.
   */
  public UpdateStatusInDto(final OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }
}
