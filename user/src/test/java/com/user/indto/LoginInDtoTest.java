package com.user.indto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class LoginInDtoTest {

  private LoginInDto loginInDto;

  @BeforeEach
  public void setUp() {
    loginInDto = new LoginInDto();
  }

  @Test
  public void testGetEmail() {
    loginInDto.setEmail("adi@gmail.com");
    assertEquals("adi@gmail.com", loginInDto.getEmail());
  }

  @Test
  public void testSetEmail() {
    loginInDto.setEmail("ravi@gmail.com");
    assertEquals("ravi@gmail.com", loginInDto.getEmail());
  }

  @Test
  public void testGetPassword() {
    loginInDto.setPassword("Password123");
    assertEquals("Password123", loginInDto.getPassword());
  }

  @Test
  public void testSetPassword() {
    loginInDto.setPassword("NewPassword123");
    assertEquals("NewPassword123", loginInDto.getPassword());
  }

  @Test
  public void testToString() {
    loginInDto.setEmail("adi@gmail.com");
    loginInDto.setPassword("Password123");

    String expected = "LoginInDto(email=adi@gmail.com, password=Password123)";
    assertEquals(expected, loginInDto.toString());
  }

  @Test
  public void testHashCode() {
    LoginInDto loginInDto1 = new LoginInDto();
    loginInDto1.setEmail("adi@gmail.com");
    loginInDto1.setPassword("Password123");

    LoginInDto loginInDto2 = new LoginInDto();
    loginInDto2.setEmail("adi@gmail.com");
    loginInDto2.setPassword("Password123");

    assertEquals(loginInDto1.hashCode(), loginInDto2.hashCode());

    loginInDto2.setEmail("ravi@gmail.com");
    assertNotEquals(loginInDto1.hashCode(), loginInDto2.hashCode());
  }

  @Test
  public void testEquals() {
    LoginInDto loginInDto1 = new LoginInDto();
    loginInDto1.setEmail("adi@gmail.com");
    loginInDto1.setPassword("Password123");

    LoginInDto loginInDto2 = new LoginInDto();
    loginInDto2.setEmail("adi@gmail.com");
    loginInDto2.setPassword("Password123");

    assertEquals(loginInDto1, loginInDto2);

    loginInDto2.setEmail("ravi@gmail.com");
    assertNotEquals(loginInDto1, loginInDto2);

    assertEquals(loginInDto1, loginInDto1);

    assertNotEquals(loginInDto1, null);

    assertNotEquals(loginInDto1, new Object());
  }

}
