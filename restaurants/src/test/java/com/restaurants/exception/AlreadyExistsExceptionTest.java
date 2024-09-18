package com.restaurants.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlreadyExistsExceptionTest {

  @Test
  public void testDefaultConstructor() {
    AlreadyExistsException exception = new AlreadyExistsException();
    assertNull(exception.getMessage(), "Expected exception message to be null");
  }

  @Test
  public void testParameterizedConstructor() {
    String message = "This resource already exists";
    AlreadyExistsException exception = new AlreadyExistsException(message);
    assertEquals(message, exception.getMessage(), "Exception message does not match");
  }

}
