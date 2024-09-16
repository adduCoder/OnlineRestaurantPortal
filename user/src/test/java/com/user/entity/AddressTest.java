package com.user.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

  private Address address;

  @BeforeEach
  public void setUp() {
    address = new Address();
    address.setId(1);
    address.setStreet("Tower Square");
    address.setCity("Indore");
    address.setState("MP");
    address.setPinCode(452001);
    address.setUserId(101);
  }

  @Test
  public void testGetId() {
    assertEquals(1, address.getId());
  }

  @Test
  public void testSetId() {
    address.setId(2);
    assertEquals(2, address.getId());
  }

  @Test
  public void testGetStreet() {
    assertEquals("Tower Square", address.getStreet());
  }

  @Test
  public void testSetStreet() {
    address.setStreet("New Tower Square");
    assertEquals("New Tower Square", address.getStreet());
  }

  @Test
  public void testGetCity() {
    assertEquals("Indore", address.getCity());
  }

  @Test
  public void testSetCity() {
    address.setCity("Bhopal");
    assertEquals("Bhopal", address.getCity());
  }

  @Test
  public void testGetState() {
    assertEquals("MP", address.getState());
  }

  @Test
  public void testSetState() {
    address.setState("Maharashtra");
    assertEquals("Maharashtra", address.getState());
  }

  @Test
  public void testGetPinCode() {
    assertEquals(452001, address.getPinCode());
  }

  @Test
  public void testSetPinCode() {
    address.setPinCode(462001);
    assertEquals(462001, address.getPinCode());
  }

  @Test
  public void testGetUserId() {
    assertEquals(101, address.getUserId());
  }

  @Test
  public void testSetUserId() {
    address.setUserId(102);
    assertEquals(102, address.getUserId());
  }

  @Test
  public void testToString() {
    String expectedToString = "Address(id=1, street=Tower Square, state=MP, city=Indore, pinCode=452001, userId=101)";
    assertEquals(expectedToString, address.toString());
  }

  @Test
  public void testEqualsAndHashCode() {
    Address address2 = new Address();
    address2.setId(1);
    address2.setStreet("Tower Square");
    address2.setCity("Indore");
    address2.setState("MP");
    address2.setPinCode(452001);
    address2.setUserId(101);

    assertEquals(address, address2);
    assertEquals(address.hashCode(), address2.hashCode());

    // Modify one property at a time to test inequality
    address2.setStreet("Different Street");
    assertNotEquals(address, address2);
    assertNotEquals(address.hashCode(), address2.hashCode());

    address2.setStreet("Tower Square");
    address2.setCity("Different City");
    assertNotEquals(address, address2);
    assertNotEquals(address.hashCode(), address2.hashCode());

    address2.setCity("Indore");
    address2.setState("Different State");
    assertNotEquals(address, address2);
    assertNotEquals(address.hashCode(), address2.hashCode());

    address2.setState("MP");
    address2.setPinCode(123456);
    assertNotEquals(address, address2);
    assertNotEquals(address.hashCode(), address2.hashCode());

    address2.setPinCode(452001);
    address2.setUserId(202);
    assertNotEquals(address, address2);
    assertNotEquals(address.hashCode(), address2.hashCode());

    // Test with two new empty Address instances
    Address address3 = new Address();
    Address address4 = new Address();
    assertEquals(address3, address4);
    assertEquals(address3.hashCode(), address4.hashCode());
  }
}
