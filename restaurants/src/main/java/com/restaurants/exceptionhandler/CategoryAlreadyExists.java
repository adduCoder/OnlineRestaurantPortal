package com.restaurants.exceptionhandler;
public class CategoryAlreadyExists extends RuntimeException {
  public CategoryAlreadyExists() {
    super("Category with the same name already exists");
  }
}
