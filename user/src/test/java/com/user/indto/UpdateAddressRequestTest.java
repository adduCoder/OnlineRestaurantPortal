package com.user.indto;

import com.user.dto.UpdateAddressInDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UpdateAddressInDtoTest {

  private UpdateAddressInDto updateAddressInDto;

  @BeforeEach
  public void setUp() {
    updateAddressInDto = new UpdateAddressInDto();
  }

  @Test
  public void testGetStreet() {
    updateAddressInDto.setStreet("MG Road");
    assertEquals("MG Road", updateAddressInDto.getStreet());
  }

  @Test
  public void testSetStreet() {
    updateAddressInDto.setStreet("Brigade Road");
    assertEquals("Brigade Road", updateAddressInDto.getStreet());
  }

  @Test
  public void testGetState() {
    updateAddressInDto.setState("Karnataka");
    assertEquals("Karnataka", updateAddressInDto.getState());
  }

  @Test
  public void testSetState() {
    updateAddressInDto.setState("Maharashtra");
    assertEquals("Maharashtra", updateAddressInDto.getState());
  }

  @Test
  public void testGetCity() {
    updateAddressInDto.setCity("Bangalore");
    assertEquals("Bangalore", updateAddressInDto.getCity());
  }

  @Test
  public void testSetCity() {
    updateAddressInDto.setCity("Mumbai");
    assertEquals("Mumbai", updateAddressInDto.getCity());
  }

  @Test
  public void testGetPinCode() {
    updateAddressInDto.setPinCode(560001);
    assertEquals(560001, updateAddressInDto.getPinCode());
  }

  @Test
  public void testSetPinCode() {
    updateAddressInDto.setPinCode(400001);
    assertEquals(400001, updateAddressInDto.getPinCode());
  }

  @Test
  public void testToString() {
    updateAddressInDto.setStreet("MG Road");
    updateAddressInDto.setState("Karnataka");
    updateAddressInDto.setCity("Bangalore");
    updateAddressInDto.setPinCode(560001);

    String expected = "UpdateAddressInDto(street=MG Road, state=Karnataka, city=Bangalore, pinCode=560001)";
    assertEquals(expected, updateAddressInDto.toString());
  }

  @Test
  public void testHashCode() {
    UpdateAddressInDto updateAddressInDto1 = new UpdateAddressInDto();
    updateAddressInDto1.setStreet("MG Road");
    updateAddressInDto1.setState("Karnataka");
    updateAddressInDto1.setCity("Bangalore");
    updateAddressInDto1.setPinCode(560001);

    UpdateAddressInDto updateAddressInDto2 = new UpdateAddressInDto();
    updateAddressInDto2.setStreet("MG Road");
    updateAddressInDto2.setState("Karnataka");
    updateAddressInDto2.setCity("Bangalore");
    updateAddressInDto2.setPinCode(560001);

    assertEquals(updateAddressInDto1.hashCode(), updateAddressInDto2.hashCode());

    updateAddressInDto2.setCity("Mumbai");
    assertNotEquals(updateAddressInDto1.hashCode(), updateAddressInDto2.hashCode());
  }

  @Test
  public void testEquals() {
    UpdateAddressInDto updateAddressInDto1 = new UpdateAddressInDto();
    updateAddressInDto1.setStreet("MG Road");
    updateAddressInDto1.setState("Karnataka");
    updateAddressInDto1.setCity("Bangalore");
    updateAddressInDto1.setPinCode(560001);

    UpdateAddressInDto updateAddressInDto2 = new UpdateAddressInDto();
    updateAddressInDto2.setStreet("MG Road");
    updateAddressInDto2.setState("Karnataka");
    updateAddressInDto2.setCity("Bangalore");
    updateAddressInDto2.setPinCode(560001);

    // Check that two objects with the same values are equal
    assertEquals(updateAddressInDto1, updateAddressInDto2);

    // Change a field and check that they are no longer equal
    updateAddressInDto2.setCity("Mumbai");
    assertNotEquals(updateAddressInDto1, updateAddressInDto2);

    // Check that an object is equal to itself
    assertEquals(updateAddressInDto1, updateAddressInDto1);

    // Check that an object is not equal to null
    assertNotEquals(updateAddressInDto1, null);

    // Check that an object is not equal to a different type of object
    assertNotEquals(updateAddressInDto1, new Object());
  }

}
