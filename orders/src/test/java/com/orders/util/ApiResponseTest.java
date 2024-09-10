package com.orders.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ApiResponseTest {

  private ApiResponse apiResponse;

  @BeforeEach
  public void setUp() {
    apiResponse = new ApiResponse();
  }

  @Test
  public void testGetMessage() {
    apiResponse.setMessage("Success");
    assertEquals("Success", apiResponse.getMessage());
  }

  @Test
  public void testSetMessage() {
    apiResponse.setMessage("Error");
    assertEquals("Error", apiResponse.getMessage());
  }

  @Test
  public void testToString() {
    apiResponse.setMessage("Test message");
    String expected = "ApiResponse(message=Test message)";
    assertEquals(expected, apiResponse.toString());
  }

  @Test
  public void testHashCode() {
    apiResponse.setMessage("Test message");
    ApiResponse other = new ApiResponse();
    other.setMessage("Test message");

    assertEquals(apiResponse.hashCode(), other.hashCode());

    other.setMessage("Different message");
    assertNotEquals(apiResponse.hashCode(), other.hashCode());
  }

  @Test
  public void testEquals() {
    apiResponse.setMessage("Test message");
    ApiResponse other = new ApiResponse();
    other.setMessage("Test message");

    assertEquals(apiResponse, other);

    other.setMessage("Different message");
    assertNotEquals(apiResponse, other);

    assertEquals(apiResponse, apiResponse);

    assertNotEquals(apiResponse, null);

    assertNotEquals(apiResponse, new Object());
  }
}
