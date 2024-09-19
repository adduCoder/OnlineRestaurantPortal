package com.user.util;

/**
 * This class contains constant values used throughout the application.
 * These constants include messages, email addresses, and various configuration values.
 */
public final class Constant {

  /**
   * The message indicating that no address was found.
   */
  public static final String NO_ADDRESS_FOUND = "no address found";
  /**
   * The message indicating that no customer was found.
   */
  public static final String NO_CUSTOMER_FOUND = "no customer found";
  /**
   * The message indicating that a user already exists.
   */
  public static final String USER_ALREADY_EXISTED = "user already existed";
  /**
   * The message indicating that an address was successfully added.
   */
  public static final String ADDRESS_ADDED_SUCCESS = "address added successfull";
  /**
   * The message indicating that an address was successfully updated.
   */
  public static final String ADDRESS_UPDATED_SUCCESS = "address updated successfull";
  /**
   * The message indicating that a user was successfully updated.
   */
  public static final String USER_UPDATED_SUCCESS = "user updated successfull";
  /**
   * The message indicating that a user was successfully created.
   */

  public static final String USER_CREATED_SUCCESS = "user created successfull";

  /**
   * The message indicating that an email was successfully sent.
   */
  public static final String MAIL_SENDED_SUCCESS = "email sended successfull";
  /**
   * The default email address used for sending emails.
   */
  public static final String SENDER = "iadityapatel1729@gmail.com";
  /**
   * The minimum length required for a street name.
   */
  public static final int MIN_STREET_LENGTH = 3;

  /**
   * The minimum length required for a state name.
   */
  public static final int MIN_STATE_LENGTH = 2;
  /**
   * The minimum length required for a city name.
   */
  public static final int MIN_CITY_LENGTH = 3;
  /**
   * The default wallet balance assigned to a user.
   */
  public static final Double DEFAULT_WALLET_BALANCE = 1000.0;

  /**
   * Fod Invalid Password.
   */
  public static final String INVALID_PASSWORD = "Password must be at least 4 characters having at least one uppercase ,"
    + " one lowercase , and one digit.";

  /**
   * Private constructor to prevent instantiation of this utility class.
   */
  private Constant() {

  }

}
