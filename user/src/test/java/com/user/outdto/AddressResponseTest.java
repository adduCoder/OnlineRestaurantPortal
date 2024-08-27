package com.user.outdto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AddressResponseTest {

  private AddressResponse addressResponse1;
  private AddressResponse addressResponse2;

  @BeforeEach
  public void setUp() {
    addressResponse1 = new AddressResponse();
    addressResponse1.setUserId(1);
    addressResponse1.setStreet("MG Road");
    addressResponse1.setCity("Bangalore");
    addressResponse1.setState("Karnataka");
    addressResponse1.setPinCode(560001);

    addressResponse2 = new AddressResponse();
    addressResponse2.setUserId(1);
    addressResponse2.setStreet("MG Road");
    addressResponse2.setCity("Bangalore");
    addressResponse2.setState("Karnataka");
    addressResponse2.setPinCode(560001);
  }

  @Test
  public void testGetUserId() {
    assertEquals(1, addressResponse1.getUserId());
  }

  @Test
  public void testSetUserId() {
    addressResponse1.setUserId(2);
    assertEquals(2, addressResponse1.getUserId());
  }

  @Test
  public void testGetStreet() {
    assertEquals("MG Road", addressResponse1.getStreet());
  }

  @Test
  public void testSetStreet() {
    addressResponse1.setStreet("Brigade Road");
    assertEquals("Brigade Road", addressResponse1.getStreet());
  }

  @Test
  public void testGetCity() {
    assertEquals("Bangalore", addressResponse1.getCity());
  }

  @Test
  public void testSetCity() {
    addressResponse1.setCity("Mumbai");
    assertEquals("Mumbai", addressResponse1.getCity());
  }

  @Test
  public void testGetState() {
    assertEquals("Karnataka", addressResponse1.getState());
  }

  @Test
  public void testSetState() {
    addressResponse1.setState("Maharashtra");
    assertEquals("Maharashtra", addressResponse1.getState());
  }

  @Test
  public void testGetPinCode() {
    assertEquals(560001, addressResponse1.getPinCode());
  }

  @Test
  public void testSetPinCode() {
    addressResponse1.setPinCode(400001);
    assertEquals(400001, addressResponse1.getPinCode());
  }

  @Test
  public void testHashCode() {
    assertEquals(addressResponse1.hashCode(), addressResponse2.hashCode());

    addressResponse2.setCity("Mumbai");
    assertNotEquals(addressResponse1.hashCode(), addressResponse2.hashCode());
  }

  @Test
  public void testEquals() {
    assertEquals(addressResponse1, addressResponse2);

    addressResponse2.setCity("Mumbai");
    assertNotEquals(addressResponse1, addressResponse2);

    assertEquals(addressResponse1, addressResponse1);

    assertNotEquals(addressResponse1, null);

    assertNotEquals(addressResponse1, new Object());
  }
}
