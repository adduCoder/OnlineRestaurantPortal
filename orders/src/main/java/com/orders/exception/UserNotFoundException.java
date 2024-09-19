package com.orders.exception;

import com.orders.util.Constant;

/**
 * Exception thrown when a user is not found in the system.
 * <p>
 * This runtime exception is used to indicate that a requested user could not be
 * located within the system. It signals that the user ID or information provided
 * does not correspond to any existing user, and thus the requested operation
 * involving that user cannot be completed.
 * </p>
 */
public class UserNotFoundException extends RuntimeException {

  /**
   * Constructs a new {@code UserNotFound} exception with a predefined message.
   * <p>
   * The default message for this exception is retrieved from the
   * {@link Constant#NOT_FOUND} constant, which provides a standard error message
   * indicating that the user could not be found.
   * </p>
   */
  public UserNotFoundException() {
    super(Constant.NOT_FOUND);
  }

  /**
   * Constructs a new {@code UserNotFound} exception with the specified message.
   * <p>
   * This constructor allows for a custom message to be provided, which can be used
   * to give more specific details about why the user was not found or to include
   * additional context in the exception message.
   * </p>
   *
   * @param message the detail message to be included in the exception
   */
  public UserNotFoundException(final String message) {
    super(message);
  }
}
