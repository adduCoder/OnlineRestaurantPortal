package com.orders.servicetest;

import com.orders.dto.ApiResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ApiResponseTest {

  @Test
  public void testGettersAndSetters() {
    ApiResponse apiResponse = new ApiResponse();

    String expectedMessage = "Test message";
    apiResponse.setMessage(expectedMessage);

    String actualMessage = apiResponse.getMessage();
    assertEquals(expectedMessage, actualMessage, "The message should be the same as the one set.");
  }

  @Test
  public void testHashCode() {
    ApiResponse apiResponse1 = new ApiResponse();
    ApiResponse apiResponse2 = new ApiResponse();

    String message = "Test message";
    apiResponse1.setMessage(message);
    apiResponse2.setMessage(message);

    assertEquals(apiResponse1.hashCode(), apiResponse2.hashCode(), "Hash codes should be equal for objects with the same state.");
  }

  @Test
  public void testEquals() {
    ApiResponse apiResponse1 = new ApiResponse();
    ApiResponse apiResponse2 = new ApiResponse();
    ApiResponse apiResponse3 = new ApiResponse();

    String message = "Test message";
    apiResponse1.setMessage(message);
    apiResponse2.setMessage(message);

    String differentMessage = "Different message";
    apiResponse3.setMessage(differentMessage);

    assertEquals(apiResponse1, apiResponse2, "ApiResponse instances with the same state should be equal.");

    assertNotEquals(apiResponse1, apiResponse3, "ApiResponse instances with different states should not be equal.");

    assertNotEquals(apiResponse1, null, "ApiResponse instance should not be equal to null.");

    assertNotEquals(apiResponse1, new Object(), "ApiResponse instance should not be equal to an object of a different class.");
  }

  @Test
  public void testToString() {
    ApiResponse apiResponse = new ApiResponse();

    String message = "Test message";
    apiResponse.setMessage(message);

    String expectedString = "ApiResponse(message=Test message)";

    assertEquals(expectedString, apiResponse.toString(), "The toString method should return the expected string representation.");
  }

}
