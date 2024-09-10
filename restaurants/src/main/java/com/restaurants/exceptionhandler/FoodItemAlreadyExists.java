package com.restaurants.exceptionhandler;

import com.restaurants.util.Constant;

public class FoodItemAlreadyExists extends RuntimeException {
  public FoodItemAlreadyExists() {
    super(Constant.FOODITEM_ALREADY_EXISTS);
  }
}
