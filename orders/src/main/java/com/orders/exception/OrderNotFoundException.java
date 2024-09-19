package com.orders.exception;

import com.orders.util.Constant;

/**
 * Exception thrown when an order is not found in the system.
 * <p>
 * This runtime exception is used to indicate that a requested order could not be
 * found within the system, possibly due to an invalid order ID or the order
 * being deleted. This exception is typically used in scenarios where the system
 * needs to signal that the order does not exist or cannot be retrieved.
 * </p>
 */
public class OrderNotFoundException extends RuntimeException {

  /**
   * Constructs a new {@code OrderNotFound} exception with a predefined message.
   * <p>
   * The default message for this exception is retrieved from the
   * {@link Constant#ORDER_NOT_FOUND} constant, which provides a standard error
   * message indicating that the order could not be found.
   * </p>
   */
  public OrderNotFoundException() {
    super(Constant.ORDER_NOT_FOUND);
  }

  /**
   * Constructs a new {@code OrderNotFound} exception with the specified message.
   * <p>
   * This constructor allows for a custom message to be provided, which can be
   * used to give more specific details about why the order is considered not found.
   * </p>
   *
   * @param message the detail message to be included in the exception
   */
  public OrderNotFoundException(final String message) {
    super(message);
  }
}
