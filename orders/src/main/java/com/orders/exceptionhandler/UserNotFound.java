package com.orders.exceptionhandler;

import com.orders.util.Constant;

/**
 * Exception thrown when a user is not found in the system.
 * This runtime exception is used to indicate that a requested user could not be located.
 */
public class UserNotFound extends RuntimeException {
  /**
   * Constructs a new {@code UserNotFound} exception with a predefined message.
   * The message is obtained from the {@link Constant#NOT_FOUND} constant.
   */
  public UserNotFound() {
    super(Constant.NOT_FOUND);
  }
  public UserNotFound(String message) {
    super(message);
  }
}
