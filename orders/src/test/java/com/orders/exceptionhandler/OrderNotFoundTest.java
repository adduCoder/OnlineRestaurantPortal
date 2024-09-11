package com.orders.exceptionhandler;

import com.orders.util.Constant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderNotFoundTest {

  @Test
  public void testOrderNotFoundExceptionMessage() {
    // Create an instance of OrderNotFound
    OrderNotFound exception = new OrderNotFound();

    // Verify that the message is set to the constant value
    String expectedMessage = Constant.ORDER_NOT_FOUND;
    assertEquals(expectedMessage, exception.getMessage());
  }
}
