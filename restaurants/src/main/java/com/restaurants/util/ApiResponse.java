package com.restaurants.util;

import lombok.Data;

/**
 * Utility class representing a standard API response with a message.
 * This class is typically used to encapsulate responses from the API with a message string.
 */
@Data
public class ApiResponse {
  /**
   * The message included in the API response.
   */
  private String message;
  /**
   * Default constructor for creating an empty {@link ApiResponse} object.
   */
  public ApiResponse() {

  }
  /**
   * Constructs an {@link ApiResponse} with a specific message.
   *
   * @param message the message to include in the API response
   */
  public ApiResponse(final String message) {
    this.message = message;
  }
}
