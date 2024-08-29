package com.user.indto;

import com.user.util.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for creating or updating a user.
 * This class encapsulates the data required for user creation or updates, including validation constraints.
 */
@Data
public class UserInDto {
  /**
   * The name of the user.
   * This field cannot be blank, must be at least 3 characters long, and contain only alphabetic characters.
   */
  @NotBlank(message = "Name cannot be blank")
  @Size(min = 3, message = "Name must be at least 3 characters long")
  @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only alphabets")
  private String name;

  /**
   * The email of the user.
   * This field cannot be blank, must be a valid email format, and must end with either '@gmail.com' or '@nucleusteq.com'.
   */
  @NotBlank(message = "Email cannot be blank")
  @Email(message = "Email should be valid")
  @Pattern(regexp = "^(?=.*[a-zA-Z])[a-zA-Z0-9._%+-]+@(?:gmail\\.com|nucleusteq\\.com)$",
    message = "Email must contain at least one alphabetic character before '@gmail.com' or '@nucleusteq.com'")
  private String email;

  /**
   * The phone number of the user.
   * This field cannot be blank, must be exactly 10 digits long, and start with 7, 8, 9, or 6.
   */
  @NotBlank(message = "Phone number cannot be blank")
  @Pattern(regexp = "^[7896]\\d{9}$", message = "Phone number must be exactly 10 digits and start with 7, 8, 9, or 6")
  private String phoneNo;

  /**
   * The password of the user.
   * This field cannot be blank.
   */
  @NotBlank(message = "Password cannot be blank")
  private String password;

  /**
   * The role of the user.
   * This field cannot be null and must be a valid {@link Role} enum value.
   */
  @NotNull(message = "Role cannot be blank")
  private Role role;

  /**
   * Compares this UserInDto object to another object for equality.
   * Two UserInDto objects are considered equal if they have the same name, email, phoneNo, password, and role.
   *
   * @param o the object to compare with
   * @return true if this object is equal to the other object; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserInDto)) return false;
    UserInDto userInDto = (UserInDto) o;
    return Objects.equals(name, userInDto.name) && Objects.equals(email, userInDto.email) &&
      Objects.equals(phoneNo, userInDto.phoneNo) && Objects.equals(password, userInDto.password) &&
      role == userInDto.role;
  }

  /**
   * Returns a hash code value for this UserInDto object.
   * The hash code is computed based on the name, email, phoneNo, password, and role fields.
   *
   * @return the hash code value for this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, email, phoneNo, password, role);
  }


  /**
   * Returns a string representation of this UserInDto object.
   * The string representation includes the name, email, phoneNo, password, and role fields.
   *
   * @return a string representation of this object
   */
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
