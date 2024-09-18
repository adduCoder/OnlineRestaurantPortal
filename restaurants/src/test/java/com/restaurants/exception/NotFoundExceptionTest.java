package com.restaurants.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotFoundExceptionTest {

  @Test
  public void testDefaultConstructor() {
    NotFoundException exception = new NotFoundException();
    assertNull(exception.getMessage(), "Expected exception message to be null");
  }

  @Test
  public void testParameterizedConstructor() {
    String message = "Resource not found";
    NotFoundException exception = new NotFoundException(message);
    assertEquals(message, exception.getMessage(), "Exception message does not match");
  }
}
