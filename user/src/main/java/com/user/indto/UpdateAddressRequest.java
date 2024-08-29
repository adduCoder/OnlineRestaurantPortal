package com.user.indto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Data Transfer Object (DTO) for updating an existing address.
 * This class is used to encapsulate the data sent in a request for updating an address.
 */
@Data
public class UpdateAddressRequest {

  /**
   * The street of the address.
   * This field cannot be blank and must be at least 3 characters long.
   */
  @NotBlank(message = "Street cannot be blank")
  @Size(min = 3, message = "Street must be at least 3 characters long")
  private String street;

  /**
   * The state of the address.
   * This field cannot be blank and must be at least 2 characters long.
   */
  @NotBlank(message = "state cannot be blank")
  @Size(min = 2, message = "state must be at least 3 characters long")
  private String state;

  /**
   * The city of the address.
   * This field cannot be blank and must be at least 3 characters long.
   */
  @NotBlank(message = "city cannot be blank")
  @Size(min = 3, message = "city must be at least 3 characters long")
  private String city;

  /**
   * The pin code of the address.
   * This field cannot be null.
   */
  @NotNull(message = "pinCode cannot be blank")
  private Integer pinCode;

}


