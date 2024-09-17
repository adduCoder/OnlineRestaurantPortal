package com.restaurants.util;

/**
 * A utility class that holds constant values used throughout the application.
 * This class provides static constant messages that can be used in various parts of the code
 * such as exception messages, success responses, and validation messages.
 *
 * The class is marked as `final` to prevent inheritance and the constructor is private
 * to prevent instantiation since it's intended to be used only for its static members.
 */
public final class Constant {

  /**
   * Private constructor to prevent instantiation of this utility class.
   */
  private Constant() {
    // Prevent instantiation
  }

  /**
   * Message for when a user is not found in the system.
   */
  public static final String USER_NOT_FOUND = "user not found";

  /**
   * Message for when a restaurant is not found.
   */
  public static final String RESTAURANT_NOT_FOUND = "restaurant not found";

  /**
   * Success message for when a restaurant is successfully added.
   */
  public static final String RESTAURANT_ADDED_SUCCESS = "restaurant added success";

  /**
   * Success message for when a restaurant is successfully updated.
   */
  public static final String RESTAURANT_UPDATED_SUCCESS = "restaurant updated success";

  /**
   * Message for when a category is not found in the system.
   */
  public static final String CATEGORY_NOT_FOUND = "category not found";

  /**
   * Success message for when a category is successfully created.
   */
  public static final String CATEGORY_CREATED_SUCCESS = "category created success";

  /**
   * Message for when a category already exists with the same name.
   */
  public static final String CATEGORY_ALREADY_EXISTS = "category already exists with same name";

  /**
   * Success message for when a category is successfully updated.
   */
  public static final String CATEGORY_UPDATED_SUCCESS = "category updated success";

  /**
   * Message for when a food item already exists with the same name.
   */
  public static final String FOODITEM_ALREADY_EXISTS = "food items already exists with same name";

  /**
   * Success message for when a food item is successfully created.
   */
  public static final String FOODITEM_CREATED_SUCCESS = "food item created success";

  /**
   * Success message for when a food item is successfully updated.
   */
  public static final String FOODITEM_UPDATED_SUCCESS = "food item updated success";

  /**
   * Message for when a food item is not found in the system.
   */
  public static final String FOODITEM_NOT_FOUND = "food item not found";

  /**
   * Message for when an operation is not allowed due to the user not being an owner.
   */
  public static final String OPERATION_NOT_ALLOWED = "This operation can't be performed as you are not an Owner";

  /**
   * Message for when the image file has an invalid extension.
   */
  public static final String BAD_IMAGE_EXTENSION = "image should end with either .jpg or .jpeg or .png";
}
