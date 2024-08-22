package com.user.inDto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class LoginInDto {

  @NotBlank(message = "Email cannot be blank")
  @Email(message = "Email should be valid")
  @Pattern(regexp = ".*\\.com$", message = "Email should end with '.com'")
  private String email;

  @NotBlank(message = "Password cannot be blank")
  private String password;
}
