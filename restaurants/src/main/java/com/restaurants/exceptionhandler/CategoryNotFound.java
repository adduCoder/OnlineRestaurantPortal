package com.restaurants.exceptionhandler;


public class CategoryNotFound extends RuntimeException {
  public CategoryNotFound() {
    super("No Such Category Existed");
  }
}
