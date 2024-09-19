package com.user.outdto;

import com.user.dto.UserOutDto;
import com.user.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserOutDtoTest {

  private UserOutDto userOutDto1;
  private UserOutDto userOutDto2;

  @BeforeEach
  public void setUp() {
    userOutDto1 = new UserOutDto();
    userOutDto1.setId(1);
    userOutDto1.setName("Test User A");
    userOutDto1.setEmail("test.user.a@example.com");
    userOutDto1.setWalletBalance(123.45);
    userOutDto1.setPhoneNo("5551234567");
    userOutDto1.setRole(Role.USER);

    userOutDto2 = new UserOutDto();
    userOutDto2.setId(1);
    userOutDto2.setName("Test User A");
    userOutDto2.setEmail("test.user.a@example.com");
    userOutDto2.setWalletBalance(123.45);
    userOutDto2.setPhoneNo("5551234567");
    userOutDto2.setRole(Role.USER);
  }

  @Test
  public void testGetId() {
    assertEquals(1, userOutDto1.getId());
  }

  @Test
  public void testSetId() {
    userOutDto1.setId(2);
    assertEquals(2, userOutDto1.getId());
  }

  @Test
  public void testGetName() {
    assertEquals("Test User A", userOutDto1.getName());
  }

  @Test
  public void testSetName() {
    userOutDto1.setName("Test User B");
    assertEquals("Test User B", userOutDto1.getName());
  }

  @Test
  public void testGetEmail() {
    assertEquals("test.user.a@example.com", userOutDto1.getEmail());
  }

  @Test
  public void testSetEmail() {
    userOutDto1.setEmail("test.user.b@example.com");
    assertEquals("test.user.b@example.com", userOutDto1.getEmail());
  }

  @Test
  public void testGetWalletBalance() {
    assertEquals(123.45, userOutDto1.getWalletBalance());
  }

  @Test
  public void testSetWalletBalance() {
    userOutDto1.setWalletBalance(678.90);
    assertEquals(678.90, userOutDto1.getWalletBalance());
  }

  @Test
  public void testGetPhoneNo() {
    assertEquals("5551234567", userOutDto1.getPhoneNo());
  }

  @Test
  public void testSetPhoneNo() {
    userOutDto1.setPhoneNo("5557654321");
    assertEquals("5557654321", userOutDto1.getPhoneNo());
  }

  @Test
  public void testGetRole() {
    assertEquals(Role.USER, userOutDto1.getRole());
  }

  @Test
  public void testSetRole() {
    userOutDto1.setRole(Role.OWNER);
    assertEquals(Role.OWNER, userOutDto1.getRole());
  }

  @Test
  public void testToString() {
    String expected = "UserOutDto(id=1, name=Test User A, email=test.user.a@example.com, walletBalance=123.45, phoneNo=5551234567, role=USER)";
    assertEquals(expected, userOutDto1.toString());
  }

  @Test
  public void testHashCode() {
    assertEquals(userOutDto1.hashCode(), userOutDto2.hashCode());

    userOutDto2.setName("Test User B");
    assertNotEquals(userOutDto1.hashCode(), userOutDto2.hashCode());
  }

  @Test
  public void testEquals() {
    assertEquals(userOutDto1, userOutDto2);

    userOutDto2.setName("Test User B");
    assertNotEquals(userOutDto1, userOutDto2);

    assertEquals(userOutDto1, userOutDto1);

    assertNotEquals(userOutDto1, null);

    assertNotEquals(userOutDto1, new Object());
  }

}
