package com.orders.servicetest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ApiResponseTest {

  @Test
  public void testGettersAndSetters() {
    // Create an instance of ApiResponse
    ApiResponse apiResponse = new ApiResponse();

    // Set a message
    String expectedMessage = "Test message";
    apiResponse.setMessage(expectedMessage);

    // Get the message and verify it
    String actualMessage = apiResponse.getMessage();
    assertEquals(expectedMessage, actualMessage, "The message should be the same as the one set.");
  }

  @Test
  public void testHashCode() {
    // Create instances of ApiResponse
    ApiResponse apiResponse1 = new ApiResponse();
    ApiResponse apiResponse2 = new ApiResponse();

    // Set the same message
    String message = "Test message";
    apiResponse1.setMessage(message);
    apiResponse2.setMessage(message);

    // Test that hashCode is equal for equal objects
    assertEquals(apiResponse1.hashCode(), apiResponse2.hashCode(), "Hash codes should be equal for objects with the same state.");
  }

  @Test
  public void testEquals() {
    // Create instances of ApiResponse
    ApiResponse apiResponse1 = new ApiResponse();
    ApiResponse apiResponse2 = new ApiResponse();
    ApiResponse apiResponse3 = new ApiResponse();

    // Set the same message for apiResponse1 and apiResponse2
    String message = "Test message";
    apiResponse1.setMessage(message);
    apiResponse2.setMessage(message);

    // Set a different message for apiResponse3
    String differentMessage = "Different message";
    apiResponse3.setMessage(differentMessage);

    // Test that apiResponse1 and apiResponse2 are equal
    assertEquals(apiResponse1, apiResponse2, "ApiResponse instances with the same state should be equal.");

    // Test that apiResponse1 and apiResponse3 are not equal
    assertNotEquals(apiResponse1, apiResponse3, "ApiResponse instances with different states should not be equal.");

    // Test that apiResponse1 is not equal to null
    assertNotEquals(apiResponse1, null, "ApiResponse instance should not be equal to null.");

    // Test that apiResponse1 is not equal to an object of a different class
    assertNotEquals(apiResponse1, new Object(), "ApiResponse instance should not be equal to an object of a different class.");
  }

  @Test
  public void testToString() {
    // Create an instance of ApiResponse
    ApiResponse apiResponse = new ApiResponse();

    // Set a message
    String message = "Test message";
    apiResponse.setMessage(message);

    // Expected toString output
    String expectedString = "ApiResponse(message=Test message)";

    // Verify the toString output
    assertEquals(expectedString, apiResponse.toString(), "The toString method should return the expected string representation.");
  }

}
