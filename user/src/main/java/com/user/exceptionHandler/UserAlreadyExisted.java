package com.user.exceptionHandler;

public class UserAlreadyExisted extends RuntimeException {
  public UserAlreadyExisted() {
    super("User Already Existed");
  }
}