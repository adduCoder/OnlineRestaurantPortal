package com.orders.exceptionhandler;

/**
 * Exception thrown when an invalid operation is attempted.
 * This runtime exception is used to signal that a requested operation
 * cannot be performed due to its invalidity.
 */
public class InvalidOperation extends RuntimeException {
  /**
   * Constructs a new {@code InvalidOperation} exception with a default message.
   * The default message is "Operation can't be performed".
   */
  public InvalidOperation() {
    super("Operation cant be performed");
  }
}
