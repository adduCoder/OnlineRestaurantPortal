package com.restaurants.exceptionhandler;

import com.restaurants.util.Constant;

/**
 * Exception thrown when a requested category is not found.
 * <p>
 * This exception is used to indicate that the category being
 * searched for does not exist in the system.
 * </p>
 */
public class CategoryNotFound extends RuntimeException {

  /**
   * Constructs a new {@code CategoryNotFound} exception with a default message.
   * The default message is "No Such Category Existed".
   */
  public CategoryNotFound() {
    super(Constant.CATEGORY_NOT_FOUND);
  }
}
