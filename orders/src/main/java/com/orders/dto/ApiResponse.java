package com.orders.dto;

import lombok.Data;


/**
 * A DTO (Data Transfer Object) used to represent a generic API response.
 * <p>
 * This class is primarily used for sending response messages to the client.
 * It contains a single field {@code message} that holds the status or message
 * to be conveyed in the API response.
 * </p>
 */
@Data
public class ApiResponse {
  /**
   * The message to be included in the API response.
   * <p>
   * This message can represent a success message, error message, or any other
   * information the API needs to return.
   * </p>
   */
  private String message;

  /**
   * Default constructor.
   * <p>
   * This constructor creates an empty {@code ApiResponse} object.
   * </p>
   */
  public ApiResponse() {

  }

  /**
   * Constructs an {@code ApiResponse} with the specified message.
   *
   * @param message The message to be included in the API response.
   */
  public ApiResponse(final String message) {
    this.message = message;
  }
}
