package com.restaurants.exceptionhandler;

import com.restaurants.outdto.CategoryOutDto;

public class CategoryNotFound extends RuntimeException{
  public CategoryNotFound(){
    super("No Such Category Existed");
  }
}
