package com.user.util;

import lombok.Data;

@Data
public class UserApiResponse {
  private String message;
  private Integer userId;
  private Role role;
}
