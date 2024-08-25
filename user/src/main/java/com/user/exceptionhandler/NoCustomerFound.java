package com.user.exceptionhandler;

public class NoCustomerFound extends RuntimeException {
  public NoCustomerFound() {
    super("No Customer Found Exception");
  }
}


