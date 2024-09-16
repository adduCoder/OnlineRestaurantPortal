package com.orders.exceptionhandler;

import com.orders.util.Constant;

/**
 * Exception thrown when there is insufficient balance for a transaction.
 * This runtime exception is used to signal that a user does not have
 * enough funds to complete an operation.
 */
public class InsufficientBalance extends RuntimeException {
  /**
   * Constructs a new {@code InsufficientBalance} exception with a default message.
   * The default message is retrieved from the {@link Constant#INSUFFICIENT_AMOUNT}.
   */
  public InsufficientBalance() {
    super(Constant.INSUFFICIENT_AMOUNT);
  }
  public InsufficientBalance(String message) {
    super(message);
  }
}
