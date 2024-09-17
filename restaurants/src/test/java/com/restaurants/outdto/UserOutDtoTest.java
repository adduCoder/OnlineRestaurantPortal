package com.restaurants.outdto;

import com.restaurants.dto.UserOutDto;
import com.restaurants.util.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserOutDtoTest {

  @Test
  public void testGetterAndSetter() {
    UserOutDto userOutDto = new UserOutDto();

    // Test id
    assertNull(userOutDto.getId());
    Integer id = 1;
    userOutDto.setId(id);
    assertEquals(id, userOutDto.getId());

    // Test name
    assertNull(userOutDto.getName());
    String name = "Sample Name";
    userOutDto.setName(name);
    assertEquals(name, userOutDto.getName());

    // Test email
    assertNull(userOutDto.getEmail());
    String email = "sample.email@example.com";
    userOutDto.setEmail(email);
    assertEquals(email, userOutDto.getEmail());

    // Test walletBalance
    assertNull(userOutDto.getWalletBalance());
    Double walletBalance = 1000.00;
    userOutDto.setWalletBalance(walletBalance);
    assertEquals(walletBalance, userOutDto.getWalletBalance());

    // Test phoneNo
    assertNull(userOutDto.getPhoneNo());
    String phoneNo = "1234567890";
    userOutDto.setPhoneNo(phoneNo);
    assertEquals(phoneNo, userOutDto.getPhoneNo());

    // Test role
    assertNull(userOutDto.getRole());
    Role role = Role.USER; // Assuming Role enum has USER constant
    userOutDto.setRole(role);
    assertEquals(role, userOutDto.getRole());
  }

  @Test
  public void testToString() {
    UserOutDto userOutDto = new UserOutDto();
    userOutDto.setId(1);
    userOutDto.setName("Sample Name");
    userOutDto.setEmail("sample.email@example.com");
    userOutDto.setWalletBalance(1000.00);
    userOutDto.setPhoneNo("1234567890");
    userOutDto.setRole(Role.USER); // Assuming Role enum has USER constant

    String expectedToString = "UserOutDto(id=1, name=Sample Name, email=sample.email@example.com, " +
      "walletBalance=1000.0, phoneNo=1234567890, role=USER)";
    assertEquals(expectedToString, userOutDto.toString());
  }

  @Test
  public void testEqualsAndHashCode() {
    UserOutDto userOutDto1 = buildUserOutDto(1, "Sample Name", "sample.email@example.com", 1000.00, "1234567890", Role.USER);

    assertEquals(userOutDto1, userOutDto1);
    assertEquals(userOutDto1.hashCode(), userOutDto1.hashCode());

    assertNotEquals(userOutDto1, new Object());

    UserOutDto userOutDto2 = buildUserOutDto(1, "Sample Name", "sample.email@example.com", 1000.00, "1234567890", Role.USER);
    assertEquals(userOutDto1, userOutDto2);
    assertEquals(userOutDto1.hashCode(), userOutDto2.hashCode());

    userOutDto2 = buildUserOutDto(2, "Sample Name", "sample.email@example.com", 1000.00, "1234567890", Role.USER);
    assertNotEquals(userOutDto1, userOutDto2);
    assertNotEquals(userOutDto1.hashCode(), userOutDto2.hashCode());

    userOutDto2 = buildUserOutDto(1, "Different Name", "sample.email@example.com", 1000.00, "1234567890", Role.USER);
    assertNotEquals(userOutDto1, userOutDto2);
    assertNotEquals(userOutDto1.hashCode(), userOutDto2.hashCode());

    userOutDto2 = buildUserOutDto(1, "Sample Name", "different.email@example.com", 1000.00, "1234567890", Role.USER);
    assertNotEquals(userOutDto1, userOutDto2);
    assertNotEquals(userOutDto1.hashCode(), userOutDto2.hashCode());

    userOutDto2 = buildUserOutDto(1, "Sample Name", "sample.email@example.com", 2000.00, "1234567890", Role.USER);
    assertNotEquals(userOutDto1, userOutDto2);
    assertNotEquals(userOutDto1.hashCode(), userOutDto2.hashCode());

    userOutDto2 = buildUserOutDto(1, "Sample Name", "sample.email@example.com", 1000.00, "0987654321", Role.USER);
    assertNotEquals(userOutDto1, userOutDto2);
    assertNotEquals(userOutDto1.hashCode(), userOutDto2.hashCode());

    // Test with OWNER role
    userOutDto2 = buildUserOutDto(1, "Sample Name", "sample.email@example.com", 1000.00, "1234567890", Role.OWNER);
    assertNotEquals(userOutDto1, userOutDto2);
    assertNotEquals(userOutDto1.hashCode(), userOutDto2.hashCode());

    userOutDto1 = new UserOutDto();
    userOutDto2 = new UserOutDto();
    assertEquals(userOutDto1, userOutDto2);
    assertEquals(userOutDto1.hashCode(), userOutDto2.hashCode());
  }

  private UserOutDto buildUserOutDto(Integer id, String name, String email, Double walletBalance, String phoneNo, Role role) {
    UserOutDto userOutDto = new UserOutDto();
    userOutDto.setId(id);
    userOutDto.setName(name);
    userOutDto.setEmail(email);
    userOutDto.setWalletBalance(walletBalance);
    userOutDto.setPhoneNo(phoneNo);
    userOutDto.setRole(role);
    return userOutDto;
  }
}
