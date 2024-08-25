package com.user.exceptionhandler;

public class NoAddressFound extends RuntimeException {
  public NoAddressFound() {
    super("No Address Found Exception");
  }
}

