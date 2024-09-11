package com.orders.dto.outdto;

import com.orders.dto.UserOutDto;
import com.orders.util.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserOutDtoTest {

  @Test
  public void testIdGetterAndSetter() {
    UserOutDto userOutDto = new UserOutDto();
    Integer expectedId = 1;
    userOutDto.setId(expectedId);
    Integer actualId = userOutDto.getId();
    assertEquals(expectedId, actualId, "The id should be equal to the expected value.");
  }

  @Test
  public void testNameGetterAndSetter() {
    UserOutDto userOutDto = new UserOutDto();
    String expectedName = "Aarav";
    userOutDto.setName(expectedName);
    String actualName = userOutDto.getName();
    assertEquals(expectedName, actualName, "The name should be equal to the expected value.");
  }

  @Test
  public void testEmailGetterAndSetter() {
    UserOutDto userOutDto = new UserOutDto();
    String expectedEmail = "aarav@example.com";
    userOutDto.setEmail(expectedEmail);
    String actualEmail = userOutDto.getEmail();
    assertEquals(expectedEmail, actualEmail, "The email should be equal to the expected value.");
  }

  @Test
  public void testWalletBalanceGetterAndSetter() {
    UserOutDto userOutDto = new UserOutDto();
    Double expectedWalletBalance = 1000.50;
    userOutDto.setWalletBalance(expectedWalletBalance);
    Double actualWalletBalance = userOutDto.getWalletBalance();
    assertEquals(expectedWalletBalance, actualWalletBalance, "The walletBalance should be equal to the expected value.");
  }

  @Test
  public void testPhoneNoGetterAndSetter() {
    UserOutDto userOutDto = new UserOutDto();
    String expectedPhoneNo = "9876543210";
    userOutDto.setPhoneNo(expectedPhoneNo);
    String actualPhoneNo = userOutDto.getPhoneNo();
    assertEquals(expectedPhoneNo, actualPhoneNo, "The phoneNo should be equal to the expected value.");
  }

  @Test
  public void testRoleGetterAndSetter() {
    UserOutDto userOutDto = new UserOutDto();
    Role expectedRole = Role.USER; // Assuming USER is a valid enum value in Role
    userOutDto.setRole(expectedRole);
    Role actualRole = userOutDto.getRole();
    assertEquals(expectedRole, actualRole, "The role should be equal to the expected value.");
  }

  @Test
  public void testEquals() {
    UserOutDto userOutDto1 = new UserOutDto();
    userOutDto1.setId(1);
    userOutDto1.setName("Aarav");
    userOutDto1.setEmail("aarav@example.com");
    userOutDto1.setWalletBalance(1000.50);
    userOutDto1.setPhoneNo("9876543210");
    userOutDto1.setRole(Role.USER);

    UserOutDto userOutDto2 = new UserOutDto();
    userOutDto2.setId(1);
    userOutDto2.setName("Aarav");
    userOutDto2.setEmail("aarav@example.com");
    userOutDto2.setWalletBalance(1000.50);
    userOutDto2.setPhoneNo("9876543210");
    userOutDto2.setRole(Role.USER);

    assertEquals(userOutDto1, userOutDto2, "The two UserOutDto objects should be equal.");

    userOutDto2.setId(2);
    assertNotEquals(userOutDto1, userOutDto2, "The two UserOutDto objects should not be equal.");
  }

  @Test
  public void testHashCode() {
    UserOutDto userOutDto1 = new UserOutDto();
    userOutDto1.setId(1);
    userOutDto1.setName("Aarav");
    userOutDto1.setEmail("aarav@example.com");
    userOutDto1.setWalletBalance(1000.50);
    userOutDto1.setPhoneNo("9876543210");
    userOutDto1.setRole(Role.USER);

    UserOutDto userOutDto2 = new UserOutDto();
    userOutDto2.setId(1);
    userOutDto2.setName("Aarav");
    userOutDto2.setEmail("aarav@example.com");
    userOutDto2.setWalletBalance(1000.50);
    userOutDto2.setPhoneNo("9876543210");
    userOutDto2.setRole(Role.USER);

    assertEquals(userOutDto1.hashCode(), userOutDto2.hashCode(), "The hashCode should be equal for two equal UserOutDto objects.");

    userOutDto2.setId(2);
    assertNotEquals(userOutDto1.hashCode(), userOutDto2.hashCode(),
      "The hashCode should not be equal for two unequal UserOutDto objects.");
  }

  @Test
  public void testToString() {
    UserOutDto userOutDto = new UserOutDto();
    userOutDto.setId(1);
    userOutDto.setName("Aarav");
    userOutDto.setEmail("aarav@example.com");
    userOutDto.setWalletBalance(1000.50);
    userOutDto.setPhoneNo("9876543210");
    userOutDto.setRole(Role.USER);

    String expectedToString =
      "UserOutDto(id=1, name=Aarav, email=aarav@example.com, walletBalance=1000.5, phoneNo=9876543210, role=USER)";
    String actualToString = userOutDto.toString();
    assertEquals(expectedToString, actualToString, "The toString method should return the expected string.");
  }
}
