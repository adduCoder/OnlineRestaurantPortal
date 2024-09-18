package com.orders.exception;

import com.orders.util.Constant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsufficientBalanceExceptionTest {

  @Test
  public void testInsufficientBalanceExceptionMessage() {
    InsufficientBalanceException exception = new InsufficientBalanceException();

    String expectedMessage = Constant.INSUFFICIENT_AMOUNT;

    assertEquals(expectedMessage, exception.getMessage(), "The exception message should match the constant INSURANCE_AMOUNT.");
  }
}
