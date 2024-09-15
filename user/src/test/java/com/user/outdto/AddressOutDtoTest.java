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
    addressOutDto.setStreet("Tower Square");
    addressOutDto.setCity("Indore");
    addressOutDto.setState("Madhya Pradesh");
    addressOutDto.setPinCode(452001);
  }

  @Test
  public void testValidAddressOutDto() {
    assertEquals(1, addressOutDto.getAddressId());
    assertEquals(100, addressOutDto.getUserId());
    assertEquals("Tower Square", addressOutDto.getStreet());
    assertEquals("Indore", addressOutDto.getCity());
    assertEquals("Madhya Pradesh", addressOutDto.getState());
    assertEquals(452001, addressOutDto.getPinCode());
  }

  @Test
  public void testUpdateStreet() {
    addressOutDto.setStreet("MG Road");
    assertEquals("MG Road", addressOutDto.getStreet());
  }

  @Test
  public void testUpdateCity() {
    addressOutDto.setCity("Bhopal");
    assertEquals("Bhopal", addressOutDto.getCity());
  }

  @Test
  public void testUpdateState() {
    addressOutDto.setState("Uttar Pradesh");
    assertEquals("Uttar Pradesh", addressOutDto.getState());
  }

  @Test
  public void testUpdatePinCode() {
    addressOutDto.setPinCode(110001);
    assertEquals(110001, addressOutDto.getPinCode());
  }
}
