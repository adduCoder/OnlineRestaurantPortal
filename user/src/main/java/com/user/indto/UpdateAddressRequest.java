package com.user.indto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdateAddressRequest {

  @NotBlank(message = "Street cannot be blank")
  @Size(min = 3, message = "Street must be at least 3 characters long")
  private String street;

  @NotBlank(message = "state cannot be blank")
  @Size(min = 2, message = "state must be at least 3 characters long")
  private String state;

  @NotBlank(message = "city cannot be blank")
  @Size(min = 3, message = "city must be at least 3 characters long")
  private String city;

  @NotNull(message = "pinCode cannot be blank")
  private Integer pinCode;

}


