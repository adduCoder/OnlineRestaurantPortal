package com.restaurants.dto;

import com.restaurants.util.Role;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for transferring user details.
 * This DTO is used to send user information to the client.
 */
@Data
public class UserOutDto {

  /**
   * The unique identifier of the user.
   */
  private Integer id;

  /**
   * The name of the user.
   */
  private String name;

  /**
   * The email address of the user.
   */
  private String email;

  /**
   * The wallet balance of the user.
   */
  private Double walletBalance;

  /**
   * The phone number of the user.
   */
  private String phoneNo;

  /**
   * The role of the user, which can be either USER or OWNER.
   */
  private Role role;
}
