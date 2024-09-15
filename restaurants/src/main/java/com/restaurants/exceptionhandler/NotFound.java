package com.restaurants.exceptionhandler;

public class NotFound extends RuntimeException {
  public NotFound() {

  }
  public NotFound(String message) {
    super(message);
  }
}
