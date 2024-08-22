package com.user.exceptionHandler;

public class NoAddressFound extends RuntimeException {
  public NoAddressFound() {
    super("No Address Found Exception");
  }
}