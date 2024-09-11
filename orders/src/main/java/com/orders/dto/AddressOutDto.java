package com.orders.dto;

import lombok.Data;

@Data
public class AddressOutDto {
  /**
   * The unique identifier for the address.
   */
  private Integer addressId;
  /**
   * The unique identifier for the user associated with the address.
   */
  private Integer userId;
  /**
   * The street address.
   * This field represents the street portion of the address.
   */
  private String street;
  /**
   * The city in which the address is located.
   */
  private String city;
  /**
   * The state in which the address is located.
   */
  private String state;
  /**
   * The postal code (PIN code) of the address.
   * This field represents the postal code for mail delivery.
   */
  private Integer pinCode;
}
