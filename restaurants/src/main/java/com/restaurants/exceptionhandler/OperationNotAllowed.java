package com.restaurants.exceptionhandler;

import com.restaurants.util.Constant;

/**
 * Custom exception thrown when an operation is not allowed.
 * This exception extends {@link RuntimeException} and can be used to signal that a specific
 * action or operation is forbidden or not permitted in the application context.
 */
public class OperationNotAllowed extends RuntimeException {
  /**
   * Constructs a new {@code OperationNotAllowed} exception with a default message
   * from the {@link Constant#OPERATION_NOT_ALLOWED} constant.
   * This constructor is typically used when no specific message is provided, and
   * the default message is sufficient to indicate the operation is not allowed.
   */
  public OperationNotAllowed() {
    super(Constant.OPERATION_NOT_ALLOWED);
  }

  /**
   * Constructs a new {@code OperationNotAllowed} exception with the specified detail message.
   * This constructor can be used to provide a more specific error message.
   *
   * @param message the detail message providing additional information about the exception
   */
  public OperationNotAllowed(final String message) {
    super(message);
  }
}
