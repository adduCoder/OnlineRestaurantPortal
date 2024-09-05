package com.restaurants.exceptionhandler;

public class UserNotFound extends RuntimeException {
  public UserNotFound(){
    super("User Doesn't exists ");
  }
}

