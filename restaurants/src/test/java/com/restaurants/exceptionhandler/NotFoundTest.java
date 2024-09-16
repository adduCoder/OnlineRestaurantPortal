package com.restaurants.exceptionhandler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotFoundTest {

  @Test
  public void testDefaultConstructor() {
    NotFound exception = new NotFound();
    assertNull(exception.getMessage(), "Expected exception message to be null");
  }

  @Test
  public void testParameterizedConstructor() {
    String message = "Resource not found";
    NotFound exception = new NotFound(message);
    assertEquals(message, exception.getMessage(), "Exception message does not match");
  }
}
