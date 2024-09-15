package com.user.indto;

import com.user.dto.LoginInDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class LoginInDtoTest {

  private Validator validator;

  @BeforeEach
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void testValidLoginInDto() {
    LoginInDto loginInDto = new LoginInDto();
    loginInDto.setEmail("test@gmail.com");
    loginInDto.setPassword("password123");

    Set<ConstraintViolation<LoginInDto>> violations = validator.validate(loginInDto);
    assertTrue(violations.isEmpty(), "Expected no validation errors");
  }

  @Test
  public void testInvalidEmail() {
    LoginInDto loginInDto = new LoginInDto();
    loginInDto.setEmail("invalid-email"); // Invalid email format
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

    // Assert that both possible validation errors are correctly handled
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
      assertEquals("Email must contain at least one alphabetic character before " +
        "'@gmail.com' or '@nucleusteq.com'", violation.getMessage());
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
      if (violation.getMessage().equals("Email cannot be blank")) {
        hasBlankEmailError = true;
      } else if (violation.getMessage().equals("Email must contain at least" +
        " one alphabetic character before '@gmail.com' or '@nucleusteq.com'")) {
        hasPatternError = true;
      }
    }

    assertTrue(hasBlankEmailError, "Expected 'Email cannot " +
      "be blank' error message");
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
    assertFalse(violations.isEmpty(), "Expected validation" +
      " errors for invalid email domain");

    for (ConstraintViolation<LoginInDto> violation : violations) {
      assertEquals("Email must contain at least one alphabetic " +
        "character before '@gmail.com' or '@nucleusteq.com'", violation.getMessage());
    }
  }
}
