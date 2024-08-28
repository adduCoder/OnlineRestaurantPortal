package com.user.indto;

import com.user.util.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
public class UserInDto {
  @NotBlank(message = "Name cannot be blank")
  @Size(min = 3, message = "Name must be at least 3 characters long")
  @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only alphabets")
  private String name;

  @NotBlank(message = "Email cannot be blank")
  @Email(message = "Email should be valid")
  @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9._%+-]+@(?:gmail\\.com|nucleusteq\\.com)$",
    message = "Email must contain at least one alphabetic character before '@gmail.com' or '@nucleusteq.com'")
  private String email;

  @NotBlank(message = "Phone number cannot be blank")
  @Pattern(regexp = "^[7896]\\d{9}$", message = "Phone number must be exactly 10 digits and start with 7, 8, 9, or 6")
  private String phoneNo;

  @NotBlank(message = "Password cannot be blank")
  private String password;

  @NotNull(message = "Role cannot be blank")
  private Role role;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserInDto)) return false;
    UserInDto userInDto = (UserInDto) o;
    return Objects.equals(name, userInDto.name) && Objects.equals(email, userInDto.email) &&
      Objects.equals(phoneNo, userInDto.phoneNo) && Objects.equals(password, userInDto.password) &&
      role == userInDto.role;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, email, phoneNo, password, role);
  }

  @Override
  public String toString() {
    return "UserInDto{" +
      "name='" + name + '\'' +
      ", email='" + email + '\'' +
      ", phoneNo='" + phoneNo + '\'' +
      ", password='" + password + '\'' +
      ", role=" + role +
      '}';
  }
}
