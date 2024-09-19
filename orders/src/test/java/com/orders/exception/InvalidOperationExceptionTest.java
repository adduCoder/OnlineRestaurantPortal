package com.orders.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidOperationExceptionTest {

  @Test
  public void testInvalidOperationExceptionMessage() {
    InvalidOperationException exception = new InvalidOperationException();

    String expectedMessage = "Operation cant be performed";
    assertEquals(expectedMessage, exception.getMessage(), "The exception message should be 'Operation cant be performed'.");
  }
}
