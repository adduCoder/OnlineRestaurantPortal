package com.orders.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressOutDtoTest {

  @Test
  public void testGetterAndSetter() {
    AddressOutDto dto = new AddressOutDto();


    assertNull(dto.getAddressId());
    Integer addressId = 1001;
    dto.setAddressId(addressId);
    assertEquals(addressId, dto.getAddressId());


    assertNull(dto.getUserId());
    Integer userId = 2002;
    dto.setUserId(userId);
    assertEquals(userId, dto.getUserId());


    assertNull(dto.getStreet());
    String street = "Test Street";
    dto.setStreet(street);
    assertEquals(street, dto.getStreet());


    assertNull(dto.getCity());
    String city = "Test City";
    dto.setCity(city);
    assertEquals(city, dto.getCity());


    assertNull(dto.getState());
    String state = "Test State";
    dto.setState(state);
    assertEquals(state, dto.getState());


    assertNull(dto.getPinCode());
    Integer pinCode = 123456;
    dto.setPinCode(pinCode);
    assertEquals(pinCode, dto.getPinCode());
  }

  @Test
  public void testToString() {
    AddressOutDto dto = new AddressOutDto();
    dto.setAddressId(1001);
    dto.setUserId(2002);
    dto.setStreet("Test Street");
    dto.setCity("Test City");
    dto.setState("Test State");
    dto.setPinCode(123456);

    String expectedToString = "AddressOutDto(addressId=1001, userId=2002, street=Test Street, city=Test City, state=Test State, pinCode=123456)";
    assertEquals(expectedToString, dto.toString());
  }

  @Test
  public void testEqualsAndHashCode() {
    AddressOutDto dto1 = buildAddressOutDto(1001, 2002, "Test Street", "Test City", "Test State", 123456);
    AddressOutDto dto2 = buildAddressOutDto(1001, 2002, "Test Street", "Test City", "Test State", 123456);

    assertEquals(dto1, dto2);
    assertEquals(dto1.hashCode(), dto2.hashCode());

    dto2.setAddressId(2002);
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    dto2 = buildAddressOutDto(1001, 2002, "Different Street", "Test City", "Test State", 123456);
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    dto2 = buildAddressOutDto(1001, 2002, "Test Street", "Different City", "Test State", 123456);
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    dto1 = new AddressOutDto();
    dto2 = new AddressOutDto();
    assertEquals(dto1, dto2);
    assertEquals(dto1.hashCode(), dto2.hashCode());
  }

  private AddressOutDto buildAddressOutDto(Integer addressId, Integer userId,
                                           String street, String city, String state, Integer pinCode) {
    AddressOutDto dto = new AddressOutDto();
    dto.setAddressId(addressId);
    dto.setUserId(userId);
    dto.setStreet(street);
    dto.setCity(city);
    dto.setState(state);
    dto.setPinCode(pinCode);
    return dto;
  }
}
