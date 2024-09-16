package com.user.indto;

import com.user.dto.UserInDto;
import com.user.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UserInDtoTest {

  private Validator validator;
  private UserInDto validUserInDto;

  @BeforeEach
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();

    // Initialize a valid UserInDto object
    validUserInDto = new UserInDto();
    validUserInDto.setName("Aarav Kumar");
    validUserInDto.setEmail("aarav.kumar@gmail.com");
    validUserInDto.setPhoneNo("9876543210");
    validUserInDto.setPassword("password123");
    validUserInDto.setRole(Role.USER); // Use existing roles
  }

  @Test
  public void testValidUserInDto() {
    Set<ConstraintViolation<UserInDto>> violations = validator.validate(validUserInDto);
    assertTrue(violations.isEmpty(), "Expected no validation errors");
  }

  @Test
  public void testInvalidName() {
    validUserInDto.setName("Aar"); // Invalid name (too short)

    Set<ConstraintViolation<UserInDto>> violations = validator.validate(validUserInDto);
    assertTrue(violations.isEmpty(), "Expected validation errors due to invalid name");

    for (ConstraintViolation<UserInDto> violation : violations) {
      assertEquals("Name must contain only alphabets and spaces", violation.getMessage());
    }
  }

  @Test
  public void testInvalidEmail() {
    validUserInDto.setEmail("invalid-email"); // Invalid email format

    Set<ConstraintViolation<UserInDto>> violations = validator.validate(validUserInDto);
    assertFalse(violations.isEmpty(), "Expected validation errors due to invalid email");

    for (ConstraintViolation<UserInDto> violation : violations) {
      String message = violation.getMessage();
      if (message.equals("Email should be valid")) {
        assertTrue(message.contains("Email should be valid"));
      } else if (message.equals("Email must contain at least one alphabetic character before '@gmail.com' or '@nucleusteq.com'")) {
        assertTrue(message.contains("Email must contain at least one alphabetic character before '@gmail.com' or '@nucleusteq.com'"));
      } else {
        fail("Unexpected validation message: " + message);
      }
    }
  }

  @Test
  public void testEmailEnding() {
    validUserInDto.setEmail("aarav.kumar@otherdomain.com"); // Email not ending with @gmail.com or @nucleusteq.com

    Set<ConstraintViolation<UserInDto>> violations = validator.validate(validUserInDto);
    assertFalse(violations.isEmpty(), "Expected validation errors due to incorrect email domain");

    for (ConstraintViolation<UserInDto> violation : violations) {
      assertEquals("Email must contain at least one alphabetic character before " +
        "'@gmail.com' or '@nucleusteq.com'", violation.getMessage());
    }
  }

  @Test
  public void testInvalidPhoneNo() {
    validUserInDto.setPhoneNo("12345"); // Invalid phone number

    Set<ConstraintViolation<UserInDto>> violations = validator.validate(validUserInDto);
    assertFalse(violations.isEmpty(), "Expected validation errors due to invalid phone number");

    for (ConstraintViolation<UserInDto> violation : violations) {
      assertEquals("Phone number must be exactly 10 digits and start with 7, 8, 9, or 6", violation.getMessage());
    }
  }

  @Test
  public void testBlankPassword() {
    validUserInDto.setPassword(""); // Blank password

    Set<ConstraintViolation<UserInDto>> violations = validator.validate(validUserInDto);
    assertFalse(violations.isEmpty(), "Expected validation errors due to blank password");

    for (ConstraintViolation<UserInDto> violation : violations) {
      assertEquals("Password cannot be blank", violation.getMessage());
    }
  }

  @Test
  public void testNullRole() {
    validUserInDto.setRole(null); // Null role

    Set<ConstraintViolation<UserInDto>> violations = validator.validate(validUserInDto);
    assertFalse(violations.isEmpty(), "Expected validation errors due to null role");

    for (ConstraintViolation<UserInDto> violation : violations) {
      assertEquals("Role cannot be blank", violation.getMessage());
    }
  }

  @Test
  public void testToString() {
    String expectedToString = "UserInDto{name='Aarav Kumar'," +
      " email='aarav.kumar@gmail.com', phoneNo='9876543210', password='password123', role=USER}";
    assertEquals(expectedToString, validUserInDto.toString());
  }

  @Test
  public void testEqualsAndHashCode() {
    UserInDto userInDto2 = new UserInDto();
    userInDto2.setName("Aarav Kumar");
    userInDto2.setEmail("aarav.kumar@gmail.com");
    userInDto2.setPhoneNo("9876543210");
    userInDto2.setPassword("password123");
    userInDto2.setRole(Role.USER);

    assertEquals(validUserInDto, userInDto2);
    assertEquals(validUserInDto.hashCode(), userInDto2.hashCode());

    // Modify one property at a time to test inequality
    userInDto2.setName("Aarav Kumar Updated");
    assertNotEquals(validUserInDto, userInDto2);
    assertNotEquals(validUserInDto.hashCode(), userInDto2.hashCode());

    userInDto2.setName("Aarav Kumar");
    userInDto2.setEmail("new.email@gmail.com");
    assertNotEquals(validUserInDto, userInDto2);
    assertNotEquals(validUserInDto.hashCode(), userInDto2.hashCode());

    userInDto2.setEmail("aarav.kumar@gmail.com");
    userInDto2.setPhoneNo("1234567890");
    assertNotEquals(validUserInDto, userInDto2);
    assertNotEquals(validUserInDto.hashCode(), userInDto2.hashCode());

    userInDto2.setPhoneNo("9876543210");
    userInDto2.setPassword("newpassword");
    assertNotEquals(validUserInDto, userInDto2);
    assertNotEquals(validUserInDto.hashCode(), userInDto2.hashCode());

    userInDto2.setPassword("password123");
    userInDto2.setRole(Role.OWNER);
    assertNotEquals(validUserInDto, userInDto2);
    assertNotEquals(validUserInDto.hashCode(), userInDto2.hashCode());

    validUserInDto = new UserInDto();
    userInDto2 = new UserInDto();
    assertEquals(validUserInDto, userInDto2);
    assertEquals(validUserInDto.hashCode(), userInDto2.hashCode());
  }
}
