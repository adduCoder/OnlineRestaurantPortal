package com.restaurants.exceptionhandler;

import com.restaurants.util.Constant;

/**
 * Exception thrown when a requested food item is not found.
 * <p>
 * This exception is used to indicate that the food item being
 * searched for does not exist in the system.
 * </p>
 */
public class FoodItemNotFound extends RuntimeException {
  /**
   * Constructs a new {@code FoodItemNotFound} exception with a default message.
   * The default message is "No FoodItem Found!".
   */
  public FoodItemNotFound() {
    super(Constant.FOODITEM_NOT_FOUND);
  }
}
