package com.user.util;

/**
 * Enum representing the different roles a user can have in the system.
 * <p>
 * The roles define the type of access or permissions a user has.
 * <ul>
 *   <li>{@link #USER} - Represents a regular user with standard permissions.</li>
 *   <li>{@link #OWNER} - Represents a restaurant owner with elevated permissions.</li>
 * </ul>
 */
public enum Role {

  /**
   * Represents a regular user with standard permissions.
   */
  USER,
  /**
   * Represents a restaurant owner with elevated permissions.
   */
  OWNER;
}
