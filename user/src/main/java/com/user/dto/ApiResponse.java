package com.user.dto;

import lombok.Data;


/**
 * A Data Transfer Object (DTO) representing the response structure for API calls.
 * This class is used to encapsulate a simple message that indicates the result of an operation.
 */
@Data
public class ApiResponse {

  /**
   * The message indicating the result of the API call, such as success or failure.
   */
  private String message;

  /**
   * Default constructor for ApiResponse.
   * Initializes an empty response message.
   */
  public ApiResponse() {

  }

  /**
   * Constructor for ApiResponse.
   * Initializes the response message with a given value.
   *
   * @param message the message indicating the result of the API call
   */
  public ApiResponse(final String message) {
    this.message = message;
  }
}
