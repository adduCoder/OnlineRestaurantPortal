package com.user.exceptionhandler;

/**
 * Custom exception thrown when no address is found.
 * <p>
 * This exception is typically used to indicate that a requested address could not be found
 * in the system. It extends {@link RuntimeException} and provides a default error message.
 * </p>
 */
public class NoAddressFound extends RuntimeException {
  /**
   * Constructs a new {@link NoAddressFound} exception with a default error message.
   */
  public NoAddressFound() {
    super("No Address Found Exception");
  }
}

