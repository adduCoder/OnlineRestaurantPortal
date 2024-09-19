package com.orders.exception;

import com.orders.util.Constant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SessionExpiredExceptionTest {

  @Test
  public void testSessionExpiredExceptionMessage() {
    SessionExpiredException exception = new SessionExpiredException();

    String expectedMessage = Constant.SESSION_EXPIRED;

    assertEquals(expectedMessage, exception.getMessage(), "The exception message should match the constant SESSION_EXPIRED.");
  }
}
