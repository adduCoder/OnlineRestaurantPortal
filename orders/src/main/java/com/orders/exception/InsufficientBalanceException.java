package com.orders.exception;

import com.orders.util.Constant;

/**
 * Exception thrown when there is insufficient balance for a transaction.
 * <p>
 * This runtime exception is used to signal that a user does not have
 * enough funds to complete an operation, such as making a purchase or
 * performing a financial transaction.
 * </p>
 */
public class InsufficientBalanceException extends RuntimeException {
  /**
   * Constructs a new {@code InsufficientBalance} exception with a default message.
   * <p>
   * The default message is retrieved from the {@link Constant#INSUFFICIENT_AMOUNT}.
   * This message typically indicates that the user has insufficient funds to
   * perform the requested operation.
   * </p>
   */
  public InsufficientBalanceException() {
    super(Constant.INSUFFICIENT_AMOUNT);
  }
  /**
   * Constructs a new {@code InsufficientBalance} exception with the specified message.
   * <p>
   * This constructor allows for a custom message to be provided, which can be used
   * to give more specific details about the reason for the exception.
   * </p>
   *
   * @param message the detail message to be included in the exception
   */
  public InsufficientBalanceException(final String message) {
    super(message);
  }
}
