package com.restaurants.exceptionhandler;

public class OperationNotAllowed extends RuntimeException{
  public OperationNotAllowed(){
    super("This operation can't be performed as you are not an Owner");
  }
}
