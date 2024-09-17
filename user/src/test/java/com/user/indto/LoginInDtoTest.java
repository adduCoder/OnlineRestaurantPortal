package com.user.indto;

import com.user.dto.LoginInDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginInDtoTest {

  private Validator validator;
  private LoginInDto validLoginInDto;

  @BeforeEach
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();

    // Initialize a valid LoginInDto object
    validLoginInDto = new LoginInDto();
    validLoginInDto.setEmail("test@gmail.com");
    validLoginInDto.setPassword("password123");
  }

  @Test
  public void testValidLoginInDto() {
    Set<ConstraintViolation<LoginInDto>> violations = validator.validate(validLoginInDto);
    assertTrue(violations.isEmpty(), "Expected no validation errors");
  }

  @Test
  public void testInvalidEmail() {
    LoginInDto loginInDto = new LoginInDto();
    loginInDto.setEmail("invalid-email");
    loginInDto.setPassword("password123");

    Set<ConstraintViolation<LoginInDto>> violations = validator.validate(loginInDto);
    assertFalse(violations.isEmpty(), "Expected validation errors due to invalid email");

    boolean emailInvalidFormatFound = false;
    boolean emailPatternMismatchFound = false;

    for (ConstraintViolation<LoginInDto> violation : violations) {
      String message = violation.getMessage();
      if ("Email should be valid".equals(message)) {
        emailInvalidFormatFound = true;
      } else if ("Email must contain at least one alphabetic character before '@gmail.com' or '@nucleusteq.com'".equals(message)) {
        emailPatternMismatchFound = true;
      }
    }

    assertTrue(emailInvalidFormatFound || emailPatternMismatchFound,
      "Expected validation errors related to email format or pattern mismatch");
  }

  @Test
  public void testEmailEnding() {
    LoginInDto loginInDto = new LoginInDto();
    loginInDto.setEmail("test@yahoo.com");
    loginInDto.setPassword("password123");

    Set<ConstraintViolation<LoginInDto>> violations = validator.validate(loginInDto);
    assertFalse(violations.isEmpty(), "Expected validation errors due to incorrect email domain");

    for (ConstraintViolation<LoginInDto> violation : violations) {
      assertEquals("Email must contain at least one alphabetic character before '@gmail.com'" +
        " or '@nucleusteq.com'", violation.getMessage());
    }
  }

  @Test
  public void testBlankEmail() {
    LoginInDto loginInDto = new LoginInDto();
    loginInDto.setEmail("");
    loginInDto.setPassword("password123");

    Set<ConstraintViolation<LoginInDto>> violations = validator.validate(loginInDto);
    assertFalse(violations.isEmpty(), "Expected validation errors due to blank email");

    boolean hasBlankEmailError = false;
    boolean hasPatternError = false;

    for (ConstraintViolation<LoginInDto> violation : violations) {
      if ("Email cannot be blank".equals(violation.getMessage())) {
        hasBlankEmailError = true;
      } else if (("Email must contain at least one alphabetic " +
        "character before '@gmail.com' or '@nucleusteq.com'").equals(violation.getMessage())) {
        hasPatternError = true;
      }
    }

    assertTrue(hasBlankEmailError, "Expected 'Email cannot be blank' error message");
    assertTrue(hasPatternError, "Unexpected 'Email must contain at " +
      "least one alphabetic character before '@gmail.com' or '@nucleusteq.com'' error message");
  }

  @Test
  public void testBlankPassword() {
    LoginInDto loginInDto = new LoginInDto();
    loginInDto.setEmail("test@gmail.com");
    loginInDto.setPassword("");

    Set<ConstraintViolation<LoginInDto>> violations = validator.validate(loginInDto);
    assertFalse(violations.isEmpty(), "Expected validation errors due to blank password");

    for (ConstraintViolation<LoginInDto> violation : violations) {
      assertEquals("Password cannot be blank", violation.getMessage());
    }
  }

  @Test
  public void testInvalidDomain() {
    LoginInDto loginInDto = new LoginInDto();
    loginInDto.setEmail("example@randomdomain.com");
    loginInDto.setPassword("password123");

    Set<ConstraintViolation<LoginInDto>> violations = validator.validate(loginInDto);
    assertFalse(violations.isEmpty(), "Expected validation errors for invalid email domain");

    for (ConstraintViolation<LoginInDto> violation : violations) {
      assertEquals("Email must contain at least one alphabetic character before " +
        "'@gmail.com' or '@nucleusteq.com'", violation.getMessage());
    }
  }

  @Test
  public void testToString() {
    // Adjust the expected format to match the actual output
    String expectedToString = "LoginInDto(email=test@gmail.com, password=password123)";
    assertEquals(expectedToString, validLoginInDto.toString());
  }


  @Test
  public void testEqualsAndHashCode() {
    LoginInDto loginInDto2 = new LoginInDto();
    loginInDto2.setEmail("test@gmail.com");
    loginInDto2.setPassword("password123");

    assertEquals(validLoginInDto, loginInDto2);
    assertEquals(validLoginInDto.hashCode(), loginInDto2.hashCode());

    // Modify one property at a time to test inequality
    loginInDto2.setEmail("test@different.com");
    assertNotEquals(validLoginInDto, loginInDto2);
    assertNotEquals(validLoginInDto.hashCode(), loginInDto2.hashCode());

    loginInDto2.setEmail("test@gmail.com");
    loginInDto2.setPassword("differentPassword");
    assertNotEquals(validLoginInDto, loginInDto2);
    assertNotEquals(validLoginInDto.hashCode(), loginInDto2.hashCode());

    validLoginInDto = new LoginInDto();
    loginInDto2 = new LoginInDto();
    assertEquals(validLoginInDto, loginInDto2);
    assertEquals(validLoginInDto.hashCode(), loginInDto2.hashCode());
  }
}
