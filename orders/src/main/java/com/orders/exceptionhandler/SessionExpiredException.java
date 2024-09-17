package com.orders.exceptionhandler;

import com.orders.util.Constant;

/**
 * Exception thrown when a user's session has expired.
 * <p>
 * This runtime exception is used to indicate that a user's session has ended,
 * and as a result, the user needs to re-authenticate or start a new session to
 * continue interacting with the system. This exception is typically used when
 * session management detects that the session is no longer valid or has timed out.
 * </p>
 */
public class SessionExpiredException extends RuntimeException {

  /**
   * Constructs a new {@code SessionExpiredException} with a predefined message.
   * <p>
   * The default message for this exception is retrieved from the
   * {@link Constant#SESSION_EXPIRED} constant, which provides a standard error
   * message indicating that the session has expired.
   * </p>
   */
  public SessionExpiredException() {
    super(Constant.SESSION_EXPIRED);
  }
  /**
   * Constructs a new {@code SessionExpiredException} with the specified message.
   * <p>
   * This constructor allows for a custom message to be provided, which can be
   * used to give more specific details about the session expiration.
   * </p>
   *
   * @param message the detail message to be included in the exception
   */
  public SessionExpiredException(final String message) {
    super(message);
  }
}
