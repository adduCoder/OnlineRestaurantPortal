package com.user.util;


import lombok.Data;

/**
 * Represents the response containing information about an address operation.
 * This class is used to encapsulate the message related to address operations.
 */
@Data
public class AddressOutResponse {
  /**
   * The message associated with the address operation.
   * This can be a success or error message.
   */
  private String message;
}
