package com.orders.entities;

/**
 * Enumeration representing the status of an order.
 * <p>
 * The order status indicates the current state of the order in the processing workflow.
 * It helps in tracking the order's progress and determining the next steps in the order processing.
 * </p>
 */
public enum OrderStatus {
  /**
   * Indicates that the order has been confirmed and is being processed.
   */
  CONFIRMED,
  /**
   * Indicates that the order has been cancelled and will not be processed further.
   */
  CANCELLED,
  /**
   * Indicates that the order is pending and has not yet been confirmed or processed.
   */
  PENDING
}
