package com.orders.exception;

import com.orders.util.Constant;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderNotFoundExceptionTest {

  @Test
  public void testOrderNotFoundExceptionMessage() {
    OrderNotFoundException exception = new OrderNotFoundException();

    String expectedMessage = Constant.ORDER_NOT_FOUND;
    assertEquals(expectedMessage, exception.getMessage());
  }
}
