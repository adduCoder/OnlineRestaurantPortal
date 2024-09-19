package com.user.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Unit tests for the {@code Address} class.
 */
public class AddressTest {

  /**
   * The {@code Address} instance used for testing.
   */
  private Address address;

  /**
   * Sets up the test data before each test.
   */
  @BeforeEach
  public void setUp() {
    address = new Address();
    address.setId(1);
    address.setStreet("Fictional Street");
    address.setCity("Imaginary City");
    address.setState("FS");
    address.setPinCode(123456);
    address.setUserId(999);
  }

  /**
   * Tests the {@code getId} method.
   */
  @Test
  public void testGetId() {
    assertEquals(1, address.getId());
  }

  /**
   * Tests the {@code setId} method.
   */
  @Test
  public void testSetId() {
    address.setId(2);
    assertEquals(2, address.getId());
  }

  /**
   * Tests the {@code getStreet} method.
   */
  @Test
  public void testGetStreet() {
    assertEquals("Fictional Street", address.getStreet());
  }

  /**
   * Tests the {@code setStreet} method.
   */
  @Test
  public void testSetStreet() {
    address.setStreet("New Fictional Street");
    assertEquals("New Fictional Street", address.getStreet());
  }

  /**
   * Tests the {@code getCity} method.
   */
  @Test
  public void testGetCity() {
    assertEquals("Imaginary City", address.getCity());
  }

  /**
   * Tests the {@code setCity} method.
   */
  @Test
  public void testSetCity() {
    address.setCity("Mythical City");
    assertEquals("Mythical City", address.getCity());
  }

  /**
   * Tests the {@code getState} method.
   */
  @Test
  public void testGetState() {
    assertEquals("FS", address.getState());
  }

  /**
   * Tests the {@code setState} method.
   */
  @Test
  public void testSetState() {
    address.setState("FT");
    assertEquals("FT", address.getState());
  }

  /**
   * Tests the {@code getPinCode} method.
   */
  @Test
  public void testGetPinCode() {
    assertEquals(123456, address.getPinCode());
  }

  /**
   * Tests the {@code setPinCode} method.
   */
  @Test
  public void testSetPinCode() {
    address.setPinCode(654321);
    assertEquals(654321, address.getPinCode());
  }

  /**
   * Tests the {@code getUserId} method.
   */
  @Test
  public void testGetUserId() {
    assertEquals(999, address.getUserId());
  }

  /**
   * Tests the {@code setUserId} method.
   */
  @Test
  public void testSetUserId() {
    address.setUserId(1000);
    assertEquals(1000, address.getUserId());
  }

  /**
   * Tests the {@code toString} method.
   */
  @Test
  public void testToString() {
    String expectedToString = "Address(id=1, street=Fictional Street, state=FS, city=Imaginary City, pinCode=123456, userId=999)";
    assertEquals(expectedToString, address.toString());
  }

  /**
   * Tests the {@code equals} and {@code hashCode} methods.
   */
  @Test
  public void testEqualsAndHashCode() {
    Address address2 = new Address();
    address2.setId(1);
    address2.setStreet("Fictional Street");
    address2.setCity("Imaginary City");
    address2.setState("FS");
    address2.setPinCode(123456);
    address2.setUserId(999);

    assertEquals(address, address2);
    assertEquals(address.hashCode(), address2.hashCode());

    // Modify one property at a time to test inequality
    address2.setStreet("Different Street");
    assertNotEquals(address, address2);
    assertNotEquals(address.hashCode(), address2.hashCode());

    address2.setStreet("Fictional Street");
    address2.setCity("Different City");
    assertNotEquals(address, address2);
    assertNotEquals(address.hashCode(), address2.hashCode());

    address2.setCity("Imaginary City");
    address2.setState("Different State");
    assertNotEquals(address, address2);
    assertNotEquals(address.hashCode(), address2.hashCode());

    address2.setState("FS");
    address2.setPinCode(654321);
    assertNotEquals(address, address2);
    assertNotEquals(address.hashCode(), address2.hashCode());

    address2.setPinCode(123456);
    address2.setUserId(1000);
    assertNotEquals(address, address2);
    assertNotEquals(address.hashCode(), address2.hashCode());

    Address address3 = new Address();
    Address address4 = new Address();
    assertEquals(address3, address4);
    assertEquals(address3.hashCode(), address4.hashCode());
  }
}
