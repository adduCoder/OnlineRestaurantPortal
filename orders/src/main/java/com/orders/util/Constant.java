package com.orders.util;

/**
 * Utility class that holds constant string values used throughout the application.
 * This class is not intended to be instantiated or extended.
 * It provides a centralized place to manage constant messages and status codes.
 */
public final class Constant {

  /**
   * Message indicating that an update operation was successful.
   */
  public static final String UPDATED = "Updated Successfull";

  /**
   * Message indicating that there is insufficient amount for a transaction.
   */
  public static final String INSUFFICIENT_AMOUNT = "Insufficient amount";

  /**
   * Message indicating that a user was not found.
   */
  public static final String NOT_FOUND = "User Not Found !";

  /**
   * Message indicating that an order was not found.
   */
  public static final String ORDER_NOT_FOUND = "Order Not Found";

  /**
   * Message indicating that the session has expired, typically due to a time limit exceeding 30 seconds.
   */
  public static final String SESSION_EXPIRED = "Session Expired (greater than 30 seconds)";


  /**
   * Message indicating that an item was successfully added.
   */
  public static final String SUCCESS = "Successfully added";

  /**
   * Message indicating that an item was successfully added to the cart.
   */
  public static final String CART_ADDED_SUCCESS = "Successfully added to cart";

  /**
   * Message indicating that an order was successfully updated in the cart.
   */
  public static final String CART_UPDATED_SUCCESS = "Successfully  UPDATED ORDER";

  /**
   * Message indicating that an owner is not allowed to perform a certain operation.
   */
  public static final String OWNER_NOT_ALLOWED = "Owner not allowed to perform operation";

  /**
   * Private constructor to prevent instantiation of the utility class.
   */
  private Constant() {
  }
}
