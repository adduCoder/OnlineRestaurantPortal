package com.user.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressOutResponseTest {

  private AddressOutResponse addressOutResponse;

  @BeforeEach
  public void setUp() {
    addressOutResponse = new AddressOutResponse();
    addressOutResponse.setMessage("Operation successful");
  }

  @Test
  public void testGetMessage() {
    assertEquals("Operation successful", addressOutResponse.getMessage());
  }

  @Test
  public void testSetMessage() {
    addressOutResponse.setMessage("Operation failed");
    assertEquals("Operation failed", addressOutResponse.getMessage());
  }

  @Test
  public void testToString() {
    String expectedToString = "AddressOutResponse(message=Operation successful)";
    assertEquals(expectedToString, addressOutResponse.toString());
  }

  @Test
  public void testEqualsAndHashCode() {
    AddressOutResponse response1 = new AddressOutResponse();
    response1.setMessage("Operation successful");

    AddressOutResponse response2 = new AddressOutResponse();
    response2.setMessage("Operation successful");

    assertEquals(response1, response2);
    assertEquals(response1.hashCode(), response2.hashCode());

    response2.setMessage("Operation failed");
    assertNotEquals(response1, response2);
    assertNotEquals(response1.hashCode(), response2.hashCode());

    response1 = new AddressOutResponse();
    response2 = new AddressOutResponse();
    assertEquals(response1, response2);
    assertEquals(response1.hashCode(), response2.hashCode());
  }
}
