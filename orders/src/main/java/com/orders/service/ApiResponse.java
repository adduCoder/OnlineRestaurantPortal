package com.orders.service;

import lombok.Data;

/**
 * Represents a standard API response.
 * This class is used to provide a response with a message to the client.
 */
@Data
public class ApiResponse {

  /**
   * The message to be included in the API response.
   * This could be a success message, error message, or any other relevant information.
   */
  private String message;
}
