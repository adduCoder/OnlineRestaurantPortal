package com.user.dto;

import com.user.util.Role;
import lombok.Data;

@Data
/**
 * Data Transfer Object (DTO) for representing user information in responses.
 * This class encapsulates the details of a user that are returned in response to user-related requests.
 */
public class UserOutDto {
  /**
   * The unique identifier for the user.
   */
  private Integer id;
  /**
   * The name of the user.
   * This field represents the full name of the user.
   */
  private String name;
  /**
   * The email address of the user.
   * This field is used for communication and login purposes.
   */
  private String email;
  /**
   * The current balance in the user's wallet.
   * This field represents the amount of money available to the user.
   */
  private Double walletBalance;
  /**
   * The phone number of the user.
   * This field is used for contact and verification purposes.
   */
  private String phoneNo;
  /**
   * The role assigned to the user.
   * This field indicates the user's role within the system, such as USER or OWNER.
   */
  private Role role;
}


