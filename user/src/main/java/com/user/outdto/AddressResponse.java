package com.user.outdto;

import lombok.Data;

@Data
public class AddressResponse {
  private Integer userId;
  private String street;
  private String city;
  private String state;
  private Integer pinCode;

}

