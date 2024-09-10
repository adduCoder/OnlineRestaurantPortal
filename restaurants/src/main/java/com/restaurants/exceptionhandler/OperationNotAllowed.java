package com.restaurants.exceptionhandler;

import com.restaurants.util.Constant;

public class OperationNotAllowed extends RuntimeException {
  public OperationNotAllowed() {
    super(Constant.OPERATION_NOT_ALLOWED);
  }
}
