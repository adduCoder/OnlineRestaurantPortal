package com.restaurants.dto.outdto;

import com.restaurants.util.Role;
import lombok.Data;

@Data
public class UserOutDto {
  private Integer id;
  private String name;
  private String email;
  private Double walletBalance;
  private String phoneNo;
  private Role role;
}
