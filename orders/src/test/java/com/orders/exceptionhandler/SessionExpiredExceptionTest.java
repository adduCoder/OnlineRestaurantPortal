package com.orders.exceptionhandler;

import com.orders.util.Constant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SessionExpiredExceptionTest {

  @Test
  public void testSessionExpiredExceptionMessage() {
    // Create an instance of SessionExpiredException
    SessionExpiredException exception = new SessionExpiredException();

    // Get the expected message from the Constant class
    String expectedMessage = Constant.SESSION_EXPIRED;

    // Verify that the message is set to the expected value
    assertEquals(expectedMessage, exception.getMessage(), "The exception message should match the constant SESSION_EXPIRED.");
  }
}
