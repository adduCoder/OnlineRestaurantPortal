package com.restaurants.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApiResponseTest {

  @Test
  public void testGetterAndSetter() {
    ApiResponse apiResponse = new ApiResponse();

    assertNull(apiResponse.getMessage());
    String message = "Test message";
    apiResponse.setMessage(message);
    assertEquals(message, apiResponse.getMessage());
  }

  @Test
  public void testToString() {
    ApiResponse apiResponse = new ApiResponse("Test message");

    assertEquals("ApiResponse(message=Test message)", apiResponse.toString());
  }

  @Test
  public void testEqualsAndHashCode() {
    ApiResponse apiResponse1 = new ApiResponse("Test message");
    ApiResponse apiResponse2 = new ApiResponse("Test message");

    // Test reflexivity
    assertEquals(apiResponse1, apiResponse1);

    // Test symmetry
    assertEquals(apiResponse1, apiResponse2);
    assertEquals(apiResponse2, apiResponse1);

    // Test transitivity
    ApiResponse apiResponse3 = new ApiResponse("Test message");
    assertEquals(apiResponse1, apiResponse3);
    assertEquals(apiResponse2, apiResponse3);

    // Test consistency
    assertEquals(apiResponse1, apiResponse2);
    assertEquals(apiResponse1.hashCode(), apiResponse2.hashCode());

    // Test inequality
    ApiResponse apiResponse4 = new ApiResponse("Different message");
    assertNotEquals(apiResponse1, apiResponse4);
    assertNotEquals(apiResponse1.hashCode(), apiResponse4.hashCode());

    // Test null comparison
    assertNotEquals(apiResponse1, null);

    // Test different class comparison
    assertNotEquals(apiResponse1, new Object());
  }

}
