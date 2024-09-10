package com.orders.dto.outdto;

import com.orders.util.Role;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for representing the details of a user in the response.
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
   * The balance available in the user's wallet.
   */
  private Double walletBalance;

  /**
   * The phone number of the user.
   */
  private String phoneNo;

  /**
   * The role of the user, which could be a user or owner.
   */
  private Role role;
}
