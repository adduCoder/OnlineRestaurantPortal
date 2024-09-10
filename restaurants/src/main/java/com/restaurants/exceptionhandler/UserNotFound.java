package com.restaurants.exceptionhandler;

import com.restaurants.util.Constant;

public class UserNotFound extends RuntimeException {
  public UserNotFound() {
    super(Constant.USER_NOT_FOUND);
  }
}

