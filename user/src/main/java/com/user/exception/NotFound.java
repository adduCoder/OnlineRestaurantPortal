package com.user.exception;

/**
 * Custom exception class to indicate that a requested resource was not found.
 * This exception extends {@link RuntimeException} and can be used to signal
 * situations where a resource or entity is missing in the application.
 */
public class NotFound extends RuntimeException {
  /**
   * Constructs a new {@link NotFound} exception with no detail message.
   */
  public NotFound() {

  }

  /**
   * Constructs a new {@link NotFound} exception with the specified detail message.
   * The detail message can be used to provide more information about the reason
   * for the exception.
   *
   * @param message the detail message to be included with the exception
   */
  public NotFound(final String message) {
    super(message);
  }
}
