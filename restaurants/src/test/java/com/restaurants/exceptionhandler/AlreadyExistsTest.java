package com.restaurants.exceptionhandler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AlreadyExistsTest {

  @Test
  public void testDefaultConstructor() {
    AlreadyExists exception = new AlreadyExists();
    assertNull(exception.getMessage(), "Expected exception message to be null");
  }

  @Test
  public void testParameterizedConstructor() {
    String message = "This resource already exists";
    AlreadyExists exception = new AlreadyExists(message);
    assertEquals(message, exception.getMessage(), "Exception message does not match");
  }

}
