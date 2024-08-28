package com.restaurants.exceptionhandler;

/**
 * Custom exception thrown when no customer is found.
 * <p>
 * This exception is used to signal that a requested customer could not be found
 * in the system. It extends {@link RuntimeException} and provides a default error message.
 * </p>
 */
public class NoRestaurantFound extends RuntimeException {
  /**
   * Constructs a new {@link NoRestaurantFound} exception with a default error message.
   */
  public NoRestaurantFound() {
    super("No Customer Found Exception");
  }
}


