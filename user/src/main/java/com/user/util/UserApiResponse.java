package com.user.util;

import lombok.Data;

/**
 * Represents the response returned by API endpoints related to user operations.
 * <p>
 * This class contains information about the result of the API operation, including a message,
 * the user's ID, and their role.
 * </p>
 */
@Data
public class UserApiResponse {
  /**
   * A message providing additional information about the result of the API operation.
   */
  private String message;
  /**
   * The unique identifier of the user associated with the API response.
   * <p>
   * This field represents the user ID and is typically used to reference the user in subsequent operations.
   * </p>
   */
  private Integer userId;
  /**
   * The role of the user associated with the API response.
   * <p>
   * This field indicates the type of access or permissions the user has, represented by the {@link Role} enum.
   * </p>
   */
  private Role role;
}
