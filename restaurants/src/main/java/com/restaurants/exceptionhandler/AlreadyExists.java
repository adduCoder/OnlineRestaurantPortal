package com.restaurants.exceptionhandler;

public class AlreadyExists extends RuntimeException{
  public AlreadyExists(){

  }
  public AlreadyExists(String message){
    super(message);
  }
}
