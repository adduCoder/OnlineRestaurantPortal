package com.orders.exceptionhandler;

import com.orders.util.Constant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserNotFoundTest {

  @Test
  public void testUserNotFoundExceptionMessage() {
    // Create an instance of UserNotFound
    UserNotFound exception = new UserNotFound();

    // Verify that the message is set to the constant value
    String expectedMessage = Constant.NOT_FOUND;
    assertEquals(expectedMessage, exception.getMessage(), "The exception message should be equal to the constant NOT_FOUND.");
  }
}
