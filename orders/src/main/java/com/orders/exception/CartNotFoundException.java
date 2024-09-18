package com.orders.exception;

import com.orders.util.Constant;
/**
 * Exception thrown when a cart is not found.
 * <p>
 * This exception is used to indicate that a cart with the specified ID could not
 * be found in the system. It extends {@link RuntimeException} and provides a
 * predefined error message.
 * </p>
 */
public class CartNotFoundException extends RuntimeException {
  /**
   * Constructs a new {@code CartNotFoundException} with a default error message.
   * <p>
   * The default message is retrieved from the {@link Constant#CART_NOT_FOUND}
   * constant, indicating that the cart could not be found.
   * </p>
   */
  public CartNotFoundException() {
    super(Constant.CART_NOT_FOUND);
  }
}
