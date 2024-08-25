package com.user.exceptionhandler;

public class UserAlreadyExisted extends RuntimeException {
  public UserAlreadyExisted() {
    super("User Already Existed");
  }
}

