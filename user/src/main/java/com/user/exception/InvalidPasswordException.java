package com.user.exception;

/**
 * Exception thrown when an invalid password is encountered.
 * This exception is typically thrown during authentication
 * or validation processes when the provided password does not meet
 * the required criteria.
 */
public class InvalidPasswordException extends RuntimeException {

  /**
   * Constructs a new InvalidPasswordException with the specified detail message.
   *
   * @param message the detail message, which provides more information about the reason for the exception
   */
  public InvalidPasswordException(final String message) {
    super(message);
  }
}
