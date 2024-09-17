package com.user.util;

import java.util.Base64;
/**
 * Utility class for encoding and decoding passwords using Base64.
 * Provides methods to securely encode passwords before storage and decode them for authentication.
 */
public final class PasswordEncoder {
  /**
   * Encodes a plain text password using Base64 encoding.
   *
   * @param password the plain text password to be encoded
   * @return the Base64 encoded representation of the password
   */
  public static String encodePassword(final String password) {
    return Base64.getEncoder().encodeToString(password.getBytes());
  }

  /**
   * Decodes a Base64 encoded password back to its plain text representation.
   *
   * @param encodedPassword the Base64 encoded password to be decoded
   * @return the plain text representation of the password
   */
  public static String decodePassword(final String encodedPassword) {
    byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
    return new String(decodedBytes);
  }
}
