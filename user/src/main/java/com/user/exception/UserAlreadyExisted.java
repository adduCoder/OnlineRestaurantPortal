package com.user.exception;


import com.user.util.Constant;

/**
 * Custom exception thrown when a user already exists in the system.
 * <p>
 * This exception is used to indicate that an operation cannot be completed because
 * a user with the same identifier or details already exists. It extends {@link RuntimeException}
 * and provides a default error message.
 * </p>
 */
public class UserAlreadyExisted extends RuntimeException {

  /**
   * Constructs a new {@link UserAlreadyExisted} exception with a default error message.
   * The default message is retrieved from the {@link Constant#USER_ALREADY_EXISTED} constant.
   */
  public UserAlreadyExisted() {
    super(Constant.USER_ALREADY_EXISTED);
  }

  /**
   * Constructs a new {@link UserAlreadyExisted} exception with the specified detail message.
   * This constructor allows for a custom message to be provided, which can describe the specific
   * reason or context of the exception.
   *
   * @param message the detail message to be included with the exception
   */
  public UserAlreadyExisted(final String message) {
    super(message);
  }

}

