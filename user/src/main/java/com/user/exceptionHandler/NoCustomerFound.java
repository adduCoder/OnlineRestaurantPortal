package com.user.exceptionHandler;

public class NoCustomerFound extends RuntimeException {
  public NoCustomerFound() {
    super("No Customer Found Exception");
  }
}