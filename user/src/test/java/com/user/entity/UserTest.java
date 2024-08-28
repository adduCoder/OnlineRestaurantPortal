package com.user.entity;

import com.user.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserTest {

  private User user1;
  private User user2;

  @BeforeEach
  public void setUp() {
    user1 = new User();
    user1.setId(1);
    user1.setName("Aarav");
    user1.setEmail("aarav@gmail.com");
    user1.setPassword("1123");
    user1.setPhoneNo("9876543210");
    user1.setRole(Role.USER);
    user1.setWalletBalance(1000.0);

    user2 = new User();
    user2.setId(1);
    user2.setName("Aarav");
    user2.setEmail("aarav@gmail.com");
    user2.setPassword("1123");
    user2.setPhoneNo("9876543210");
    user2.setRole(Role.USER);
    user2.setWalletBalance(1000.0);
  }

  @Test
  public void testGetId() {
    assertEquals(1, user1.getId());
  }

  @Test
  public void testSetId() {
    user1.setId(2);
    assertEquals(2, user1.getId());
  }

  @Test
  public void testGetName() {
    assertEquals("Aarav", user1.getName());
  }

  @Test
  public void testSetName() {
    user1.setName("Isha");
    assertEquals("Isha", user1.getName());
  }

  @Test
  public void testGetEmail() {
    assertEquals("aarav@gmail.com", user1.getEmail());
  }

  @Test
  public void testSetEmail() {
    user1.setEmail("isha@gmail.com");
    assertEquals("isha@gmail.com", user1.getEmail());
  }

  @Test
  public void testGetPassword() {
    assertEquals("1123", user1.getPassword());
  }

  @Test
  public void testSetPassword() {
    user1.setPassword("1123");
    assertEquals("1123", user1.getPassword());
  }

  @Test
  public void testGetPhoneNo() {
    assertEquals("9876543210", user1.getPhoneNo());
  }

  @Test
  public void testSetPhoneNo() {
    user1.setPhoneNo("1234567890");
    assertEquals("1234567890", user1.getPhoneNo());
  }

  @Test
  public void testGetRole() {
    assertEquals(Role.USER, user1.getRole());
  }

  @Test
  public void testSetRole() {
    user1.setRole(Role.OWNER);
    assertEquals(Role.OWNER, user1.getRole());
  }

  @Test
  public void testGetWalletBalance() {
    assertEquals(1000.0, user1.getWalletBalance());
  }

  @Test
  public void testSetWalletBalance() {
    user1.setWalletBalance(1500.0);
    assertEquals(1500.0, user1.getWalletBalance());
  }

  @Test
  public void testToString() {
    String expected =
      "User(id=1, name=Aarav, password=1123, email=aarav@gmail.com, walletBalance=1000.0, phoneNo=9876543210, role=USER)";
    assertEquals(expected, user1.toString());
  }

  @Test
  public void testHashCode() {
    assertEquals(user1.hashCode(), user2.hashCode());

    user2.setName("Isha");
    assertNotEquals(user1.hashCode(), user2.hashCode());
  }

  @Test
  public void testEquals() {
    assertEquals(user1, user2);

    user2.setName("Isha");
    assertNotEquals(user1, user2);

    assertEquals(user1, user1);

    assertNotEquals(user1, null);

    assertNotEquals(user1, new Object());
  }
}
