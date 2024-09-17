package com.restaurants.exceptionhandler;

/**
 * Custom exception thrown when a requested resource is not found.
 * This exception extends {@link RuntimeException} and can be used to signal
 * that a resource, such as an entity, was not found in the database or elsewhere.
 */
public class NotFound extends RuntimeException {
  /**
   * Constructs a new {@code NotFound} exception with {@code null} as its detail message.
   * This is the default constructor.
   */
  public NotFound() {

  }

  /**
   * Constructs a new {@code NotFound} exception with the specified detail message.
   *
   * @param message the detail message, which provides additional information about the exception
   */
  public NotFound(final String message) {
    super(message);
  }
}
