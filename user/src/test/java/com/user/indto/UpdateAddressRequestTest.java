package com.user.indto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UpdateAddressRequestTest {

  private UpdateAddressRequest updateAddressRequest;

  @BeforeEach
  public void setUp() {
    updateAddressRequest = new UpdateAddressRequest();
  }

  @Test
  public void testGetStreet() {
    updateAddressRequest.setStreet("MG Road");
    assertEquals("MG Road", updateAddressRequest.getStreet());
  }

  @Test
  public void testSetStreet() {
    updateAddressRequest.setStreet("Brigade Road");
    assertEquals("Brigade Road", updateAddressRequest.getStreet());
  }

  @Test
  public void testGetState() {
    updateAddressRequest.setState("Karnataka");
    assertEquals("Karnataka", updateAddressRequest.getState());
  }

  @Test
  public void testSetState() {
    updateAddressRequest.setState("Maharashtra");
    assertEquals("Maharashtra", updateAddressRequest.getState());
  }

  @Test
  public void testGetCity() {
    updateAddressRequest.setCity("Bangalore");
    assertEquals("Bangalore", updateAddressRequest.getCity());
  }

  @Test
  public void testSetCity() {
    updateAddressRequest.setCity("Mumbai");
    assertEquals("Mumbai", updateAddressRequest.getCity());
  }

  @Test
  public void testGetPinCode() {
    updateAddressRequest.setPinCode(560001);
    assertEquals(560001, updateAddressRequest.getPinCode());
  }

  @Test
  public void testSetPinCode() {
    updateAddressRequest.setPinCode(400001);
    assertEquals(400001, updateAddressRequest.getPinCode());
  }

  @Test
  public void testToString() {
    updateAddressRequest.setStreet("MG Road");
    updateAddressRequest.setState("Karnataka");
    updateAddressRequest.setCity("Bangalore");
    updateAddressRequest.setPinCode(560001);

    String expected = "UpdateAddressRequest(street=MG Road, state=Karnataka, city=Bangalore, pinCode=560001)";
    assertEquals(expected, updateAddressRequest.toString());
  }

  @Test
  public void testHashCode() {
    UpdateAddressRequest updateAddressRequest1 = new UpdateAddressRequest();
    updateAddressRequest1.setStreet("MG Road");
    updateAddressRequest1.setState("Karnataka");
    updateAddressRequest1.setCity("Bangalore");
    updateAddressRequest1.setPinCode(560001);

    UpdateAddressRequest updateAddressRequest2 = new UpdateAddressRequest();
    updateAddressRequest2.setStreet("MG Road");
    updateAddressRequest2.setState("Karnataka");
    updateAddressRequest2.setCity("Bangalore");
    updateAddressRequest2.setPinCode(560001);

    assertEquals(updateAddressRequest1.hashCode(), updateAddressRequest2.hashCode());

    updateAddressRequest2.setCity("Mumbai");
    assertNotEquals(updateAddressRequest1.hashCode(), updateAddressRequest2.hashCode());
  }

  @Test
  public void testEquals() {
    UpdateAddressRequest updateAddressRequest1 = new UpdateAddressRequest();
    updateAddressRequest1.setStreet("MG Road");
    updateAddressRequest1.setState("Karnataka");
    updateAddressRequest1.setCity("Bangalore");
    updateAddressRequest1.setPinCode(560001);

    UpdateAddressRequest updateAddressRequest2 = new UpdateAddressRequest();
    updateAddressRequest2.setStreet("MG Road");
    updateAddressRequest2.setState("Karnataka");
    updateAddressRequest2.setCity("Bangalore");
    updateAddressRequest2.setPinCode(560001);

    // Check that two objects with the same values are equal
    assertEquals(updateAddressRequest1, updateAddressRequest2);

    // Change a field and check that they are no longer equal
    updateAddressRequest2.setCity("Mumbai");
    assertNotEquals(updateAddressRequest1, updateAddressRequest2);

    // Check that an object is equal to itself
    assertEquals(updateAddressRequest1, updateAddressRequest1);

    // Check that an object is not equal to null
    assertNotEquals(updateAddressRequest1, null);

    // Check that an object is not equal to a different type of object
    assertNotEquals(updateAddressRequest1, new Object());
  }

}
