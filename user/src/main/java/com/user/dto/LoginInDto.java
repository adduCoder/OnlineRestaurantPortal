package com.user.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * Data Transfer Object (DTO) for user login.
 * This class is used to encapsulate the data sent in a request for user login.
 */
@Data
public class LoginInDto {

  /**
   * The email address of the user.
   * This field cannot be blank, must be a valid email format, and should end with '.com'.
   */
  @NotBlank(message = "Email cannot be blank")
  @Email(message = "Email should be valid")
  @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9._%+-]+@(?:gmail\\.com|nucleusteq\\.com)$",
    message = "Email must contain at least one alphabetic character before '@gmail.com' or '@nucleusteq.com'")
  private String email;

  /**
   * The password of the user.
   * This field cannot be blank.
   */
  @NotBlank(message = "Password cannot be blank")
  private String password;
}

