package com.user.indto;

import com.user.dto.UserInDto;
import com.user.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserInDtoTest {

  private UserInDto userInDto;
  private UserInDto userInDto1;
  private UserInDto userInDto2;


  @BeforeEach
  public void setUp() {
    userInDto = new UserInDto();
    userInDto1 = new UserInDto();
    userInDto1.setName("Adi");
    userInDto1.setEmail("adi@gmail.com");
    userInDto1.setPhoneNo("9876543210");
    userInDto1.setPassword("Password123");
    userInDto1.setRole(Role.USER);

    userInDto2 = new UserInDto();
    userInDto2.setName("Adi");
    userInDto2.setEmail("adi@gmail.com");
    userInDto2.setPhoneNo("9876543210");
    userInDto2.setPassword("Password123");
    userInDto2.setRole(Role.USER);
  }

  @Test
  public void testGetName() {
    userInDto.setName("Adi");
    assertEquals("Adi", userInDto.getName());
  }

  @Test
  public void testSetName() {
    userInDto.setName("Ravi");
    assertEquals("Ravi", userInDto.getName());
  }

  @Test
  public void testGetEmail() {
    userInDto.setEmail("adi@gmail.com");
    assertEquals("adi@gmail.com", userInDto.getEmail());
  }

  @Test
  public void testSetEmail() {
    userInDto.setEmail("ravi@gmail.com");
    assertEquals("ravi@gmail.com", userInDto.getEmail());
  }

  @Test
  public void testGetPhoneNo() {
    userInDto.setPhoneNo("9876543210");
    assertEquals("9876543210", userInDto.getPhoneNo());
  }

  @Test
  public void testSetPhoneNo() {
    userInDto.setPhoneNo("1234567890");
    assertEquals("1234567890", userInDto.getPhoneNo());
  }

  @Test
  public void testGetPassword() {
    userInDto.setPassword("Password123");
    assertEquals("Password123", userInDto.getPassword());
  }

  @Test
  public void testSetPassword() {
    userInDto.setPassword("NewPassword123");
    assertEquals("NewPassword123", userInDto.getPassword());
  }

  @Test
  public void testGetRole() {
    userInDto.setRole(Role.USER);
    assertEquals(Role.USER, userInDto.getRole());
  }

  @Test
  public void testSetRole() {
    userInDto.setRole(Role.OWNER);
    assertEquals(Role.OWNER, userInDto.getRole());
  }

  @Test
  public void testHashCode() {
    assertEquals(userInDto1.hashCode(), userInDto2.hashCode());
    userInDto2.setPhoneNo("1234567890");
    assertNotEquals(userInDto1.hashCode(), userInDto2.hashCode());
  }

  @Test
  public void testEquals() {
    assertEquals(userInDto1, userInDto2);

    userInDto2.setPhoneNo("1234567890");
    assertNotEquals(userInDto1, userInDto2);

    assertEquals(userInDto1, userInDto1);

    assertNotEquals(userInDto1, null);

    assertNotEquals(userInDto1, new Object());
  }
}
