package com.user.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
