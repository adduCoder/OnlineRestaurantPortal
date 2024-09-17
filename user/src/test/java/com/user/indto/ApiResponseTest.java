package com.user.indto;

import com.user.dto.ApiResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for {@link ApiResponse} class.
 * These tests verify the correctness of constructors, getter and setter methods,
 * the {@link ApiResponse#toString()} method, and the equals and hashCode methods.
 */
public class ApiResponseTest {

  /**
   * Tests the default constructor of {@link ApiResponse}.
   */
  @Test
  public void testDefaultConstructor() {
    final ApiResponse apiResponse = new ApiResponse();
    assertNull(apiResponse.getMessage(), "Default message should be null");
  }

  /**
   * Tests the parameterized constructor of {@link ApiResponse}.
   */
  @Test
  public void testParameterizedConstructor() {
    final String message = "Operation Successful"; // Placeholder value
    final ApiResponse apiResponse = new ApiResponse(message);
    assertEquals(message, apiResponse.getMessage(), "Message should match the parameterized constructor value");
  }

  /**
   * Tests the getter and setter methods for the message field of {@link ApiResponse}.
   */
  @Test
  public void testGetterAndSetter() {
    final ApiResponse apiResponse = new ApiResponse();
    assertNull(apiResponse.getMessage(), "Initial message should be null");

    final String message = "Operation Failed"; // Placeholder value
    apiResponse.setMessage(message);
    assertEquals(message, apiResponse.getMessage(), "Message value should match the set value");
  }

  /**
   * Tests the {@link ApiResponse#toString()} method.
   */
  @Test
  public void testToString() {
    final ApiResponse apiResponse = new ApiResponse("Operation Completed"); // Placeholder value
    final String expectedString = "ApiResponse(message=Operation Completed)";
    assertEquals(expectedString, apiResponse.toString(), "toString() should match the expected format");
  }

  /**
   * Tests the {@link ApiResponse#equals(Object)} and {@link ApiResponse#hashCode()} methods.
   */
  @Test
  public void testEqualsAndHashcode() {
    final ApiResponse apiResponse1 = new ApiResponse("Task Successful"); // Placeholder value
    final ApiResponse apiResponse2 = new ApiResponse("Task Successful"); // Placeholder value

    // Test equality with another object having the same message
    assertEquals(apiResponse1, apiResponse2, "Objects with the same message should be equal");
    assertEquals(apiResponse1.hashCode(), apiResponse2.hashCode(), "Hash codes should match for equal objects");

    final ApiResponse apiResponse3 = new ApiResponse("Task Failed"); // Placeholder value
    assertNotEquals(apiResponse1, apiResponse3, "Objects with different messages should not be equal");
    assertNotEquals(apiResponse1.hashCode(), apiResponse3.hashCode(), "Hash codes should not match for unequal objects");
  }
}
