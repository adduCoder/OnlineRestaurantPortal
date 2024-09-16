package com.user.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserApiResponseTest {

  private UserApiResponse userApiResponse;

  @BeforeEach
  public void setUp() {
    userApiResponse = new UserApiResponse();
    userApiResponse.setMessage("Success");
    userApiResponse.setUserId(123);
    userApiResponse.setRole(Role.USER);
  }

  @Test
  public void testGetMessage() {
    assertEquals("Success", userApiResponse.getMessage());
  }

  @Test
  public void testSetMessage() {
    userApiResponse.setMessage("Updated Success");
    assertEquals("Updated Success", userApiResponse.getMessage());
  }

  @Test
  public void testGetUserId() {
    assertEquals(123, userApiResponse.getUserId());
  }

  @Test
  public void testSetUserId() {
    userApiResponse.setUserId(456);
    assertEquals(456, userApiResponse.getUserId());
  }

  @Test
  public void testGetRole() {
    assertEquals(Role.USER, userApiResponse.getRole());
  }

  @Test
  public void testSetRole() {
    userApiResponse.setRole(Role.OWNER);
    assertEquals(Role.OWNER, userApiResponse.getRole());
  }

  @Test
  public void testToString() {
    String expectedToString = "UserApiResponse(message=Success, userId=123, role=USER)";
    assertEquals(expectedToString, userApiResponse.toString());
  }

  @Test
  public void testEqualsAndHashCode() {
    UserApiResponse response1 = new UserApiResponse();
    response1.setMessage("Success");
    response1.setUserId(123);
    response1.setRole(Role.USER);

    UserApiResponse response2 = new UserApiResponse();
    response2.setMessage("Success");
    response2.setUserId(123);
    response2.setRole(Role.USER);

    assertEquals(response1, response2);
    assertEquals(response1.hashCode(), response2.hashCode());

    response2.setMessage("Different Message");
    assertNotEquals(response1, response2);
    assertNotEquals(response1.hashCode(), response2.hashCode());

    response2.setMessage("Success");
    response2.setUserId(999);
    assertNotEquals(response1, response2);
    assertNotEquals(response1.hashCode(), response2.hashCode());

    response2.setUserId(123);
    response2.setRole(Role.OWNER);
    assertNotEquals(response1, response2);
    assertNotEquals(response1.hashCode(), response2.hashCode());

    response1 = new UserApiResponse();
    response2 = new UserApiResponse();
    assertEquals(response1, response2);
    assertEquals(response1.hashCode(), response2.hashCode());
  }
}
