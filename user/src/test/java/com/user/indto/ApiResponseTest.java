package com.user.indto;

import static org.junit.jupiter.api.Assertions.*;

import com.user.dto.ApiResponse;
import org.junit.jupiter.api.Test;

public class ApiResponseTest {

  @Test
  public void testDefaultConstructor() {
    ApiResponse apiResponse = new ApiResponse();
    assertNull(apiResponse.getMessage());
  }

  @Test
  public void testParameterizedConstructor() {
    String message = "Success";
    ApiResponse apiResponse = new ApiResponse(message);
    assertEquals(message, apiResponse.getMessage());
  }

  @Test
  public void testGetterAndSetter() {
    ApiResponse apiResponse = new ApiResponse();
    assertNull(apiResponse.getMessage());

    String message = "Error occurred";
    apiResponse.setMessage(message);
    assertEquals(message, apiResponse.getMessage());
  }

  @Test
  public void testToString() {
    ApiResponse apiResponse = new ApiResponse("Data saved successfully");

    String expectedString = "ApiResponse(message=Data saved successfully)";
    assertEquals(expectedString, apiResponse.toString());
  }

  @Test
  public void testEqualsAndHashcode() {
    ApiResponse apiResponse1 = new ApiResponse("Operation completed");
    ApiResponse apiResponse2 = new ApiResponse("Operation completed");

    assertEquals(apiResponse1, apiResponse2);
    assertEquals(apiResponse1.hashCode(), apiResponse2.hashCode());

    ApiResponse apiResponse3 = new ApiResponse("Error occurred");
    assertNotEquals(apiResponse1, apiResponse3);
    assertNotEquals(apiResponse1.hashCode(), apiResponse3.hashCode());
  }
}
