package com.orders.exceptionhandler;

import com.orders.util.Constant;

/**
 * Exception thrown when an order is not found.
 * This runtime exception is used to signal that a requested order could not be found
 * in the system.
 */
public class OrderNotFound extends RuntimeException {
  /**
   * Constructs a new {@code OrderNotFound} exception with a predefined message.
   * The message is obtained from the {@link Constant#ORDER_NOT_FOUND} constant.
   */
  public OrderNotFound() {
    super(Constant.ORDER_NOT_FOUND);
  }
}
