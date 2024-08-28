package com.user.indto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AddressRequestTest {

  private AddressRequest addressRequest;

  @BeforeEach
  public void setUp() {
    addressRequest = new AddressRequest();
  }

  @Test
  public void testGetStreet() {
    addressRequest.setStreet("MG Road");
    assertEquals("MG Road", addressRequest.getStreet());
  }

  @Test
  public void testSetStreet() {
    addressRequest.setStreet("Brigade Road");
    assertEquals("Brigade Road", addressRequest.getStreet());
  }

  @Test
  public void testGetState() {
    addressRequest.setState("Karnataka");
    assertEquals("Karnataka", addressRequest.getState());
  }

  @Test
  public void testSetState() {
    addressRequest.setState("Maharashtra");
    assertEquals("Maharashtra", addressRequest.getState());
  }

  @Test
  public void testGetCity() {
    addressRequest.setCity("Bangalore");
    assertEquals("Bangalore", addressRequest.getCity());
  }

  @Test
  public void testSetCity() {
    addressRequest.setCity("Mumbai");
    assertEquals("Mumbai", addressRequest.getCity());
  }

  @Test
  public void testGetPinCode() {
    addressRequest.setPinCode(560001);
    assertEquals(560001, addressRequest.getPinCode());
  }

  @Test
  public void testSetPinCode() {
    addressRequest.setPinCode(400001);
    assertEquals(400001, addressRequest.getPinCode());
  }

  @Test
  public void testGetUserId() {
    addressRequest.setUserId(1);
    assertEquals(1, addressRequest.getUserId());
  }

  @Test
  public void testSetUserId() {
    addressRequest.setUserId(2);
    assertEquals(2, addressRequest.getUserId());
  }

  @Test
  public void testToString() {
    addressRequest.setStreet("MG Road");
    addressRequest.setState("Karnataka");
    addressRequest.setCity("Bangalore");
    addressRequest.setPinCode(560001);
    addressRequest.setUserId(1);
    String expected = "AddressRequest(street=MG Road, state=Karnataka, city=Bangalore, pinCode=560001, userId=1)";
    assertEquals(expected, addressRequest.toString());
  }

  @Test
  public void testHashCode() {
    AddressRequest addressRequest1 = new AddressRequest();
    addressRequest1.setStreet("MG Road");
    addressRequest1.setState("Karnataka");
    addressRequest1.setCity("Bangalore");
    addressRequest1.setPinCode(560001);
    addressRequest1.setUserId(1);

    AddressRequest addressRequest2 = new AddressRequest();
    addressRequest2.setStreet("MG Road");
    addressRequest2.setState("Karnataka");
    addressRequest2.setCity("Bangalore");
    addressRequest2.setPinCode(560001);
    addressRequest2.setUserId(1);

    assertEquals(addressRequest1.hashCode(), addressRequest2.hashCode());
  }

  @Test
  public void testEquals() {
    AddressRequest addressRequest1 = new AddressRequest();
    addressRequest1.setStreet("MG Road");
    addressRequest1.setState("Karnataka");
    addressRequest1.setCity("Bangalore");
    addressRequest1.setPinCode(560001);
    addressRequest1.setUserId(1);

    AddressRequest addressRequest2 = new AddressRequest();
    addressRequest2.setStreet("MG Road");
    addressRequest2.setState("Karnataka");
    addressRequest2.setCity("Bangalore");
    addressRequest2.setPinCode(560001);
    addressRequest2.setUserId(1);

    assertEquals(addressRequest1, addressRequest2);

    addressRequest2.setCity("Mumbai");
    assertNotEquals(addressRequest1, addressRequest2);
  }

}
