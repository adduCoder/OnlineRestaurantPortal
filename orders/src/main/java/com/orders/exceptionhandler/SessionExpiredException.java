package com.orders.exceptionhandler;

import com.orders.util.Constant;

/**
 * Exception thrown when a user session has expired.
 * This runtime exception is used to signal that a user's session has ended
 * and requires re-authentication or a new session.
 */
public class SessionExpiredException extends RuntimeException {
  /**
   * Constructs a new {@code SessionExpiredException} with a predefined message.
   * The message is obtained from the {@link Constant#SESSION_EXPIRED} constant.
   */
  public SessionExpiredException() {
    super(Constant.SESSION_EXPIRED);
  }
}
