package com.restaurants.exceptionhandler;
public class FoodItemAlreadyExists extends RuntimeException {
  public FoodItemAlreadyExists() {
    super("FoodItem with the same name already exists");
  }
}
