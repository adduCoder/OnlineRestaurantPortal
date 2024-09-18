package com.restaurants.exception;

/**
 * Custom exception thrown when a requested resource is not found.
 * This exception extends {@link RuntimeException} and can be used to signal
 * that a resource, such as an entity, was not found in the database or elsewhere.
 */
public class NotFoundException extends RuntimeException {
  /**
   * Constructs a new {@code NotFound} exception with {@code null} as its detail message.
   * This is the default constructor.
   */
  public NotFoundException() {

  }

  /**
   * Constructs a new {@code NotFound} exception with the specified detail message.
   *
   * @param message the detail message, which provides additional information about the exception
   */
  public NotFoundException(final String message) {
    super(message);
  }
}
