package com.user.outdto;

import com.user.dto.AddressOutDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressOutDtoTest {

  private AddressOutDto addressOutDto;

  @BeforeEach
  public void setUp() {
    addressOutDto = new AddressOutDto();
    addressOutDto.setAddressId(1);
    addressOutDto.setUserId(100);
    addressOutDto.setStreet("Test Street");
    addressOutDto.setCity("Test City");
    addressOutDto.setState("Test State");
    addressOutDto.setPinCode(123456);
  }

  @Test
  public void testGetterAndSetter() {
    assertEquals(1, addressOutDto.getAddressId());
    assertEquals(100, addressOutDto.getUserId());
    assertEquals("Test Street", addressOutDto.getStreet());
    assertEquals("Test City", addressOutDto.getCity());
    assertEquals("Test State", addressOutDto.getState());
    assertEquals(123456, addressOutDto.getPinCode());
  }

  @Test
  public void testToString() {
    // Adjust this string to match the actual output of the toString method
    String expectedToString = "AddressOutDto(addressId=1, userId=100, street=Test Street," +
      " city=Test City, state=Test State, pinCode=123456)";
    assertEquals(expectedToString, addressOutDto.toString());
  }

  @Test
  public void testEqualsAndHashCode() {
    AddressOutDto addressOutDto2 = new AddressOutDto();
    addressOutDto2.setAddressId(1);
    addressOutDto2.setUserId(100);
    addressOutDto2.setStreet("Test Street");
    addressOutDto2.setCity("Test City");
    addressOutDto2.setState("Test State");
    addressOutDto2.setPinCode(123456);

    assertEquals(addressOutDto, addressOutDto2);
    assertEquals(addressOutDto.hashCode(), addressOutDto2.hashCode());

    addressOutDto2.setStreet("Updated Street");
    assertNotEquals(addressOutDto, addressOutDto2);
    assertNotEquals(addressOutDto.hashCode(), addressOutDto2.hashCode());

    addressOutDto2.setStreet("Test Street");
    addressOutDto2.setCity("Updated City");
    assertNotEquals(addressOutDto, addressOutDto2);
    assertNotEquals(addressOutDto.hashCode(), addressOutDto2.hashCode());

    addressOutDto2.setCity("Test City");
    addressOutDto2.setState("Updated State");
    assertNotEquals(addressOutDto, addressOutDto2);
    assertNotEquals(addressOutDto.hashCode(), addressOutDto2.hashCode());

    addressOutDto2.setState("Test State");
    addressOutDto2.setPinCode(654321);
    assertNotEquals(addressOutDto, addressOutDto2);
    assertNotEquals(addressOutDto.hashCode(), addressOutDto2.hashCode());
  }

  @Test
  public void testUpdateStreet() {
    addressOutDto.setStreet("Updated Street");
    assertEquals("Updated Street", addressOutDto.getStreet());
  }

  @Test
  public void testUpdateCity() {
    addressOutDto.setCity("Updated City");
    assertEquals("Updated City", addressOutDto.getCity());
  }

  @Test
  public void testUpdateState() {
    addressOutDto.setState("Updated State");
    assertEquals("Updated State", addressOutDto.getState());
  }

  @Test
  public void testUpdatePinCode() {
    addressOutDto.setPinCode(654321);
    assertEquals(654321, addressOutDto.getPinCode());
  }

}
