package com.orders.exception;

import com.orders.util.Constant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserNotFoundExceptionTest {

  @Test
  public void testUserNotFoundExceptionMessage() {
    UserNotFoundException exception = new UserNotFoundException();

    String expectedMessage = Constant.NOT_FOUND;
    assertEquals(expectedMessage, exception.getMessage(), "The exception message should be equal to the constant NOT_FOUND.");
  }
}
