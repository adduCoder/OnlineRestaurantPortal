package com.user.entity;

import com.user.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Unit tests for the {@code User} class.
 */
public class UserTest {

  /**
   * The first {@code User} instance used for testing.
   */
  private User user1;

  /**
   * The second {@code User} instance used for testing.
   */
  private User user2;

  /**
   * Sets up the test data before each test.
   */
  @BeforeEach
  public void setUp() {
    user1 = new User();
    user1.setId(1);
    user1.setName("Fictional Name A");
    user1.setEmail("fictionalA@example.com");
    user1.setPassword("password123");
    user1.setPhoneNo("1234567890");
    user1.setRole(Role.USER);
    user1.setWalletBalance(5000.0);

    user2 = new User();
    user2.setId(1);
    user2.setName("Fictional Name A");
    user2.setEmail("fictionalA@example.com");
    user2.setPassword("password123");
    user2.setPhoneNo("1234567890");
    user2.setRole(Role.USER);
    user2.setWalletBalance(5000.0);
  }

  /**
   * Tests the {@code getId} method.
   */
  @Test
  public void testGetId() {
    assertEquals(1, user1.getId());
  }

  /**
   * Tests the {@code setId} method.
   */
  @Test
  public void testSetId() {
    user1.setId(2);
    assertEquals(2, user1.getId());
  }

  /**
   * Tests the {@code getName} method.
   */
  @Test
  public void testGetName() {
    assertEquals("Fictional Name A", user1.getName());
  }

  /**
   * Tests the {@code setName} method.
   */
  @Test
  public void testSetName() {
    user1.setName("Fictional Name B");
    assertEquals("Fictional Name B", user1.getName());
  }

  /**
   * Tests the {@code getEmail} method.
   */
  @Test
  public void testGetEmail() {
    assertEquals("fictionalA@example.com", user1.getEmail());
  }

  /**
   * Tests the {@code setEmail} method.
   */
  @Test
  public void testSetEmail() {
    user1.setEmail("fictionalB@example.com");
    assertEquals("fictionalB@example.com", user1.getEmail());
  }

  /**
   * Tests the {@code getPassword} method.
   */
  @Test
  public void testGetPassword() {
    assertEquals("password123", user1.getPassword());
  }

  /**
   * Tests the {@code setPassword} method.
   */
  @Test
  public void testSetPassword() {
    user1.setPassword("newpassword456");
    assertEquals("newpassword456", user1.getPassword());
  }

  /**
   * Tests the {@code getPhoneNo} method.
   */
  @Test
  public void testGetPhoneNo() {
    assertEquals("1234567890", user1.getPhoneNo());
  }

  /**
   * Tests the {@code setPhoneNo} method.
   */
  @Test
  public void testSetPhoneNo() {
    user1.setPhoneNo("0987654321");
    assertEquals("0987654321", user1.getPhoneNo());
  }

  /**
   * Tests the {@code getRole} method.
   */
  @Test
  public void testGetRole() {
    assertEquals(Role.USER, user1.getRole());
  }

  /**
   * Tests the {@code setRole} method.
   */
  @Test
  public void testSetRole() {
    user1.setRole(Role.OWNER);
    assertEquals(Role.OWNER, user1.getRole());
  }

  /**
   * Tests the {@code getWalletBalance} method.
   */
  @Test
  public void testGetWalletBalance() {
    assertEquals(5000.0, user1.getWalletBalance());
  }

  /**
   * Tests the {@code setWalletBalance} method.
   */
  @Test
  public void testSetWalletBalance() {
    user1.setWalletBalance(7500.0);
    assertEquals(7500.0, user1.getWalletBalance());
  }

  /**
   * Tests the {@code toString} method.
   */
  @Test
  public void testToString() {
    String expected =
      "User(id=1, name=Fictional Name A, password=password123, email=fictionalA@example.com, walletBalance=5000.0, phoneNo=1234567890, role=USER)";
    assertEquals(expected, user1.toString());
  }

  /**
   * Tests the {@code hashCode} method.
   */
  @Test
  public void testHashCode() {
    assertEquals(user1.hashCode(), user2.hashCode());

    user2.setName("Fictional Name B");
    assertNotEquals(user1.hashCode(), user2.hashCode());
  }

  /**
   * Tests the {@code equals} method.
   */
  @Test
  public void testEquals() {
    assertEquals(user1, user2);

    user2.setName("Fictional Name B");
    assertNotEquals(user1, user2);

    assertEquals(user1, user1);

    assertNotEquals(user1, null);

    assertNotEquals(user1, new Object());
  }
}
