package com.user.indto;

import com.user.util.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserInDto {
  @NotBlank(message = "Name cannot be blank")
  @Size(min = 3, message = "Name must be at least 3 characters long")
  private String name;


  @NotBlank(message = "Email cannot be blank")
  @Email(message = "Email should be valid")
  @Pattern(regexp = ".*\\.com$", message = "Email should end with '.com'")
  private String email;

  @NotBlank(message = "Phone number cannot be blank")
  @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
  private String phoneNo;

  @NotBlank(message = "Password cannot be blank")
  private String password;

  @NotNull(message = "Role cannot be blank")
  private Role role;
}
