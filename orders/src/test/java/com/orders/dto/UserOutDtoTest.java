package com.orders.dto;

import com.orders.util.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserOutDtoTest {

  @Test
  public void testIdGetterAndSetter() {
    UserOutDto userOutDto = new UserOutDto();
    Integer expectedId = 1001;
    userOutDto.setId(expectedId);
    Integer actualId = userOutDto.getId();
    assertEquals(expectedId, actualId, "The id should be equal to the expected value.");
  }

  @Test
  public void testNameGetterAndSetter() {
    UserOutDto userOutDto = new UserOutDto();
    String expectedName = "TestName";
    userOutDto.setName(expectedName);
    String actualName = userOutDto.getName();
    assertEquals(expectedName, actualName, "The name should be equal to the expected value.");
  }

  @Test
  public void testEmailGetterAndSetter() {
    UserOutDto userOutDto = new UserOutDto();
    String expectedEmail = "testname@example.com";
    userOutDto.setEmail(expectedEmail);
    String actualEmail = userOutDto.getEmail();
    assertEquals(expectedEmail, actualEmail, "The email should be equal to the expected value.");
  }

  @Test
  public void testWalletBalanceGetterAndSetter() {
    UserOutDto userOutDto = new UserOutDto();
    Double expectedWalletBalance = 999.99;
    userOutDto.setWalletBalance(expectedWalletBalance);
    Double actualWalletBalance = userOutDto.getWalletBalance();
    assertEquals(expectedWalletBalance, actualWalletBalance, "The walletBalance should be equal to the expected value.");
  }

  @Test
  public void testPhoneNoGetterAndSetter() {
    UserOutDto userOutDto = new UserOutDto();
    String expectedPhoneNo = "0000000000";
    userOutDto.setPhoneNo(expectedPhoneNo);
    String actualPhoneNo = userOutDto.getPhoneNo();
    assertEquals(expectedPhoneNo, actualPhoneNo, "The phoneNo should be equal to the expected value.");
  }

  @Test
  public void testRoleGetterAndSetter() {
    UserOutDto userOutDto = new UserOutDto();
    Role expectedRole = Role.OWNER; // Assuming OWNER is a valid enum value in Role
    userOutDto.setRole(expectedRole);
    Role actualRole = userOutDto.getRole();
    assertEquals(expectedRole, actualRole, "The role should be equal to the expected value.");
  }

  @Test
  public void testEquals() {
    UserOutDto userOutDto1 = new UserOutDto();
    userOutDto1.setId(1001);
    userOutDto1.setName("TestName");
    userOutDto1.setEmail("testname@example.com");
    userOutDto1.setWalletBalance(999.99);
    userOutDto1.setPhoneNo("0000000000");
    userOutDto1.setRole(Role.OWNER);

    UserOutDto userOutDto2 = new UserOutDto();
    userOutDto2.setId(1001);
    userOutDto2.setName("TestName");
    userOutDto2.setEmail("testname@example.com");
    userOutDto2.setWalletBalance(999.99);
    userOutDto2.setPhoneNo("0000000000");
    userOutDto2.setRole(Role.OWNER);

    assertEquals(userOutDto1, userOutDto2, "The two UserOutDto objects should be equal.");

    userOutDto2.setId(2002);
    assertNotEquals(userOutDto1, userOutDto2, "The two UserOutDto objects should not be equal.");
  }

  @Test
  public void testHashCode() {
    UserOutDto userOutDto1 = new UserOutDto();
    userOutDto1.setId(1001);
    userOutDto1.setName("TestName");
    userOutDto1.setEmail("testname@example.com");
    userOutDto1.setWalletBalance(999.99);
    userOutDto1.setPhoneNo("0000000000");
    userOutDto1.setRole(Role.OWNER);

    UserOutDto userOutDto2 = new UserOutDto();
    userOutDto2.setId(1001);
    userOutDto2.setName("TestName");
    userOutDto2.setEmail("testname@example.com");
    userOutDto2.setWalletBalance(999.99);
    userOutDto2.setPhoneNo("0000000000");
    userOutDto2.setRole(Role.OWNER);

    assertEquals(userOutDto1.hashCode(), userOutDto2.hashCode(), "The hashCode should be equal for two equal UserOutDto objects.");

    userOutDto2.setId(2002);
    assertNotEquals(userOutDto1.hashCode(), userOutDto2.hashCode(),
      "The hashCode should not be equal for two unequal UserOutDto objects.");
  }

  @Test
  public void testToString() {
    UserOutDto userOutDto = new UserOutDto();
    userOutDto.setId(1001);
    userOutDto.setName("TestName");
    userOutDto.setEmail("testname@example.com");
    userOutDto.setWalletBalance(999.99);
    userOutDto.setPhoneNo("0000000000");
    userOutDto.setRole(Role.OWNER);

    String expectedToString =
      "UserOutDto(id=1001, name=TestName, email=testname@example.com, walletBalance=999.99, phoneNo=0000000000, role=OWNER)";
    String actualToString = userOutDto.toString();
    assertEquals(expectedToString, actualToString, "The toString method should return the expected string.");
  }
}
