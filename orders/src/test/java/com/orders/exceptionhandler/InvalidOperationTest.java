package com.orders.exceptionhandler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidOperationTest {

  @Test
  public void testInvalidOperationExceptionMessage() {
    // Create an instance of InvalidOperation
    InvalidOperation exception = new InvalidOperation();

    // Verify that the message is set to the expected value
    String expectedMessage = "Operation cant be performed";
    assertEquals(expectedMessage, exception.getMessage(), "The exception message should be 'Operation cant be performed'.");
  }
}
