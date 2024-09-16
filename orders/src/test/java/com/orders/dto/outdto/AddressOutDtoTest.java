package com.orders.dto.outdto;

import com.orders.dto.AddressOutDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressOutDtoTest {

  @Test
  public void testGetterAndSetter() {
    AddressOutDto dto = new AddressOutDto();

    // Test addressId
    assertNull(dto.getAddressId());
    Integer addressId = 1;
    dto.setAddressId(addressId);
    assertEquals(addressId, dto.getAddressId());

    // Test userId
    assertNull(dto.getUserId());
    Integer userId = 2;
    dto.setUserId(userId);
    assertEquals(userId, dto.getUserId());

    // Test street
    assertNull(dto.getStreet());
    String street = "123 Main St";
    dto.setStreet(street);
    assertEquals(street, dto.getStreet());

    // Test city
    assertNull(dto.getCity());
    String city = "Jaipur";
    dto.setCity(city);
    assertEquals(city, dto.getCity());

    // Test state
    assertNull(dto.getState());
    String state = "Rajasthan";
    dto.setState(state);
    assertEquals(state, dto.getState());

    // Test pinCode
    assertNull(dto.getPinCode());
    Integer pinCode = 302001;
    dto.setPinCode(pinCode);
    assertEquals(pinCode, dto.getPinCode());
  }

  @Test
  public void testToString() {
    AddressOutDto dto = new AddressOutDto();
    dto.setAddressId(1);
    dto.setUserId(2);
    dto.setStreet("123 Main St");
    dto.setCity("Jaipur");
    dto.setState("Rajasthan");
    dto.setPinCode(302001);

    String expectedToString = "AddressOutDto(addressId=1, userId=2, street=123 Main St, city=Jaipur, state=Rajasthan, pinCode=302001)";
    assertEquals(expectedToString, dto.toString());
  }

  @Test
  public void testEqualsAndHashCode() {
    AddressOutDto dto1 = buildAddressOutDto(1, 2, "123 Main St", "Jaipur", "Rajasthan", 302001);
    AddressOutDto dto2 = buildAddressOutDto(1, 2, "123 Main St", "Jaipur", "Rajasthan", 302001);

    assertEquals(dto1, dto2);
    assertEquals(dto1.hashCode(), dto2.hashCode());

    dto2.setAddressId(2);
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    dto2 = buildAddressOutDto(1, 2, "456 Elm St", "Jaipur", "Rajasthan", 302001);
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    dto2 = buildAddressOutDto(1, 2, "123 Main St", "Delhi", "Delhi", 110001);
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
