package com.restaurants.util;

import lombok.Data;

@Data
public class ApiResponse {
  private String message;
  public ApiResponse() {

  }
  public ApiResponse(String message) {
    this.message = message;
  }
}
