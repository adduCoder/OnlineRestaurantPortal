package com.orders.exceptionhandler;

import com.orders.util.Constant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsufficientBalanceTest {

  @Test
  public void testInsufficientBalanceExceptionMessage() {
    // Create an instance of InsufficientBalance
    InsufficientBalance exception = new InsufficientBalance();

    // Get the expected message from the Constant class
    String expectedMessage = Constant.INSUFFICIENT_AMOUNT;

    // Verify that the message is set to the expected value
    assertEquals(expectedMessage, exception.getMessage(), "The exception message should match the constant INSURANCE_AMOUNT.");
  }
}
