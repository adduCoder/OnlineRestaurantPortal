package com.restaurants.exceptionhandler;


/**
 * Exception thrown when an attempt is made to create or add something
 * that already exists in the system.
 * This is a custom runtime exception.
 */
public class AlreadyExists extends RuntimeException {
  /**
   * Constructs a new {@code AlreadyExists} exception with no detail message.
   * The stack trace and cause will be initialized as null.
   */
  public AlreadyExists() {

  }
  /**
   * Constructs a new {@code AlreadyExists} exception with the specified detail message.
   *
   * @param message the detail message that explains the reason for the exception
   */
  public AlreadyExists(final String message) {
    super(message);
  }
}
