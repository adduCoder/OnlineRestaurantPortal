package com.user.exceptionhandler;


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
   */
  public UserAlreadyExisted() {
    super(Constant.USER_ALREADY_EXISTED);
  }

  public UserAlreadyExisted(String message) {
    super(message);
  }

}

