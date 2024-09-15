package com.user.indto;

import static org.junit.jupiter.api.Assertions.*;

import com.user.dto.AddressInDto;
import org.junit.jupiter.api.Test;

public class AddressInDtoTest {

  @Test
  public void testGetterAndSetter() {
    AddressInDto addressInDto = new AddressInDto();

    assertNull(addressInDto.getStreet());
    String street = "Tower Square";
    addressInDto.setStreet(street);
    assertEquals(street, addressInDto.getStreet());

    assertNull(addressInDto.getState());
    String state = "MP";
    addressInDto.setState(state);
    assertEquals(state, addressInDto.getState());

    assertNull(addressInDto.getCity());
    String city = "Indore";
    addressInDto.setCity(city);
    assertEquals(city, addressInDto.getCity());

    assertNull(addressInDto.getPinCode());
    Integer pinCode = 452001;
    addressInDto.setPinCode(pinCode);
    assertEquals(pinCode, addressInDto.getPinCode());

    assertNull(addressInDto.getUserId());
    Integer userId = 1;
    addressInDto.setUserId(userId);
    assertEquals(userId, addressInDto.getUserId());
  }

  @Test
  public void testToString() {
    AddressInDto addressInDto = new AddressInDto();

    String street = "Tower Square";
    addressInDto.setStreet(street);

    String state = "MP";
    addressInDto.setState(state);

    String city = "Indore";
    addressInDto.setCity(city);

    Integer pinCode = 452001;
    addressInDto.setPinCode(pinCode);

    Integer userId = 1;
    addressInDto.setUserId(userId);

    assertEquals("AddressInDto(street=Tower Square, state=MP, city=Indore, pinCode=452001, userId=1)",
      addressInDto.toString());
  }

  @Test
  public void testEqualsAndHashcode() {
    String street = "Tower Square";
    String state = "MP";
    String city = "Indore";
    Integer pinCode = 452001;
    Integer userId = 1;

    AddressInDto addressInDto1 = buildAddressInDto(street, state, city, pinCode, userId);

    assertEquals(addressInDto1, addressInDto1);
    assertEquals(addressInDto1.hashCode(), addressInDto1.hashCode());

    assertNotEquals(addressInDto1, new Object());

    AddressInDto addressInDto2 = buildAddressInDto(street, state, city, pinCode, userId);
    assertEquals(addressInDto1, addressInDto2);
    assertEquals(addressInDto1.hashCode(), addressInDto2.hashCode());

    addressInDto2 = buildAddressInDto(street + " ", state, city, pinCode, userId);
    assertNotEquals(addressInDto1, addressInDto2);
    assertNotEquals(addressInDto1.hashCode(), addressInDto2.hashCode());

    addressInDto2 = buildAddressInDto(street, state + " ", city, pinCode, userId);
    assertNotEquals(addressInDto1, addressInDto2);
    assertNotEquals(addressInDto1.hashCode(), addressInDto2.hashCode());

    addressInDto2 = buildAddressInDto(street, state, city + " ", pinCode, userId);
    assertNotEquals(addressInDto1, addressInDto2);
    assertNotEquals(addressInDto1.hashCode(), addressInDto2.hashCode());

    addressInDto2 = buildAddressInDto(street, state, city, pinCode + 1, userId);
    assertNotEquals(addressInDto1, addressInDto2);
    assertNotEquals(addressInDto1.hashCode(), addressInDto2.hashCode());

    addressInDto2 = buildAddressInDto(street, state, city, pinCode, userId + 1);
    assertNotEquals(addressInDto1, addressInDto2);
    assertNotEquals(addressInDto1.hashCode(), addressInDto2.hashCode());

    addressInDto1 = new AddressInDto();
    addressInDto2 = new AddressInDto();
    assertEquals(addressInDto1, addressInDto2);
    assertEquals(addressInDto1.hashCode(), addressInDto2.hashCode());
  }

  private AddressInDto buildAddressInDto(String street, String state, String city, Integer pinCode, Integer userId) {
    AddressInDto addressInDto = new AddressInDto();

    addressInDto.setStreet(street);
    addressInDto.setState(state);
    addressInDto.setCity(city);
    addressInDto.setPinCode(pinCode);
    addressInDto.setUserId(userId);

    return addressInDto;
  }
}
