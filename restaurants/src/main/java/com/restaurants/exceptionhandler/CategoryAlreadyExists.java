package com.restaurants.exceptionhandler;

import com.restaurants.util.Constant;

public class CategoryAlreadyExists extends RuntimeException {
  public CategoryAlreadyExists() {
    super(Constant.CATEGORY_ALREADY_EXISTS);
  }
}
