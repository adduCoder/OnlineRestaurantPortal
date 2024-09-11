package com.user.indto;

import com.user.dto.AddressInDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AddressRequestTest {

  private com.user.dto.AddressInDto AddressInDto;

  @BeforeEach
  public void setUp() {
    AddressInDto = new AddressInDto();
  }

  @Test
  public void testGetStreet() {
    AddressInDto.setStreet("MG Road");
    assertEquals("MG Road", AddressInDto.getStreet());
  }

  @Test
  public void testSetStreet() {
    AddressInDto.setStreet("Brigade Road");
    assertEquals("Brigade Road", AddressInDto.getStreet());
  }

  @Test
  public void testGetState() {
    AddressInDto.setState("Karnataka");
    assertEquals("Karnataka", AddressInDto.getState());
  }

  @Test
  public void testSetState() {
    AddressInDto.setState("Maharashtra");
    assertEquals("Maharashtra", AddressInDto.getState());
  }

  @Test
  public void testGetCity() {
    AddressInDto.setCity("Bangalore");
    assertEquals("Bangalore", AddressInDto.getCity());
  }

  @Test
  public void testSetCity() {
    AddressInDto.setCity("Mumbai");
    assertEquals("Mumbai", AddressInDto.getCity());
  }

  @Test
  public void testGetPinCode() {
    AddressInDto.setPinCode(560001);
    assertEquals(560001, AddressInDto.getPinCode());
  }

  @Test
  public void testSetPinCode() {
    AddressInDto.setPinCode(400001);
    assertEquals(400001, AddressInDto.getPinCode());
  }

  @Test
  public void testGetUserId() {
    AddressInDto.setUserId(1);
    assertEquals(1, AddressInDto.getUserId());
  }

  @Test
  public void testSetUserId() {
    AddressInDto.setUserId(2);
    assertEquals(2, AddressInDto.getUserId());
  }

  @Test
  public void testToString() {
    AddressInDto.setStreet("MG Road");
    AddressInDto.setState("Karnataka");
    AddressInDto.setCity("Bangalore");
    AddressInDto.setPinCode(560001);
    AddressInDto.setUserId(1);
    String expected = "AddressInDto(street=MG Road, state=Karnataka, city=Bangalore, pinCode=560001, userId=1)";
    assertEquals(expected, AddressInDto.toString());
  }

  @Test
  public void testHashCode() {
    AddressInDto AddressInDto1 = new AddressInDto();
    AddressInDto1.setStreet("MG Road");
    AddressInDto1.setState("Karnataka");
    AddressInDto1.setCity("Bangalore");
    AddressInDto1.setPinCode(560001);
    AddressInDto1.setUserId(1);

    AddressInDto AddressInDto2 = new AddressInDto();
    AddressInDto2.setStreet("MG Road");
    AddressInDto2.setState("Karnataka");
    AddressInDto2.setCity("Bangalore");
    AddressInDto2.setPinCode(560001);
    AddressInDto2.setUserId(1);

    assertEquals(AddressInDto1.hashCode(), AddressInDto2.hashCode());
  }

  @Test
  public void testEquals() {
    AddressInDto AddressInDto1 = new AddressInDto();
    AddressInDto1.setStreet("MG Road");
    AddressInDto1.setState("Karnataka");
    AddressInDto1.setCity("Bangalore");
    AddressInDto1.setPinCode(560001);
    AddressInDto1.setUserId(1);

    AddressInDto AddressInDto2 = new AddressInDto();
    AddressInDto2.setStreet("MG Road");
    AddressInDto2.setState("Karnataka");
    AddressInDto2.setCity("Bangalore");
    AddressInDto2.setPinCode(560001);
    AddressInDto2.setUserId(1);

    assertEquals(AddressInDto1, AddressInDto2);

    AddressInDto2.setCity("Mumbai");
    assertNotEquals(AddressInDto1, AddressInDto2);
  }

}
