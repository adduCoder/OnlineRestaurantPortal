package com.orders.exception;

/**
 * Exception thrown when an invalid operation is attempted.
 * <p>
 * This runtime exception is used to signal that a requested operation
 * cannot be performed due to its invalidity. For example, it might be used
 * when an operation is attempted that does not conform to the application's
 * rules or state requirements.
 * </p>
 */
public class InvalidOperationException extends RuntimeException {
  /**
   * Constructs a new {@code InvalidOperation} exception with a default message.
   * <p>
   * The default message is "Operation can't be performed", which indicates
   * that the operation requested is invalid and cannot be executed.
   * </p>
   */
  public InvalidOperationException() {
    super("Operation cant be performed");
  }

  /**
   * Constructs a new {@code InvalidOperation} exception with the specified message.
   * <p>
   * This constructor allows for a custom message to be provided, which can be used
   * to give more specific details about why the operation is invalid.
   * </p>
   *
   * @param message the detail message to be included in the exception
   */
  public InvalidOperationException(final String message) {
    super(message);
  }
}
