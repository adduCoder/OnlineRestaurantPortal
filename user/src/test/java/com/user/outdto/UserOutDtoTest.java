package com.user.outdto;

import com.user.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UserOutDtoTest {

  private UserOutDto userOutDto1;
  private UserOutDto userOutDto2;

  @BeforeEach
  public void setUp() {
    userOutDto1 = new UserOutDto();
    userOutDto1.setId(1);
    userOutDto1.setName("Adi");
    userOutDto1.setEmail("adi@gmail.com");
    userOutDto1.setWalletBalance(100.50);
    userOutDto1.setPhoneNo("9876543210");
    userOutDto1.setRole(Role.USER);

    userOutDto2 = new UserOutDto();
    userOutDto2.setId(1);
    userOutDto2.setName("Adi");
    userOutDto2.setEmail("adi@gmail.com");
    userOutDto2.setWalletBalance(100.50);
    userOutDto2.setPhoneNo("9876543210");
    userOutDto2.setRole(Role.USER);
  }

  @Test
  public void testGetId() {
    assertEquals(1, userOutDto1.getId());
  }

  @Test
  public void testSetId() {
    userOutDto1.setId(2);
    assertEquals(2, userOutDto1.getId());
  }

  @Test
  public void testGetName() {
    assertEquals("Adi", userOutDto1.getName());
  }

  @Test
  public void testSetName() {
    userOutDto1.setName("Ravi");
    assertEquals("Ravi", userOutDto1.getName());
  }

  @Test
  public void testGetEmail() {
    assertEquals("adi@gmail.com", userOutDto1.getEmail());
  }

  @Test
  public void testSetEmail() {
    userOutDto1.setEmail("ravi@gmail.com");
    assertEquals("ravi@gmail.com", userOutDto1.getEmail());
  }

  @Test
  public void testGetWalletBalance() {
    assertEquals(100.50, userOutDto1.getWalletBalance());
  }

  @Test
  public void testSetWalletBalance() {
    userOutDto1.setWalletBalance(200.75);
    assertEquals(200.75, userOutDto1.getWalletBalance());
  }

  @Test
  public void testGetPhoneNo() {
    assertEquals("9876543210", userOutDto1.getPhoneNo());
  }

  @Test
  public void testSetPhoneNo() {
    userOutDto1.setPhoneNo("1234567890");
    assertEquals("1234567890", userOutDto1.getPhoneNo());
  }

  @Test
  public void testGetRole() {
    assertEquals(Role.USER, userOutDto1.getRole());
  }

  @Test
  public void testSetRole() {
    userOutDto1.setRole(Role.OWNER);
    assertEquals(Role.OWNER, userOutDto1.getRole());
  }

  @Test
  public void testToString() {
    String expected = "UserOutDto(id=1, name=Adi, email=adi@gmail.com, walletBalance=100.5, phoneNo=9876543210, role=USER)";
    assertEquals(expected, userOutDto1.toString());
  }

  @Test
  public void testHashCode() {
    assertEquals(userOutDto1.hashCode(), userOutDto2.hashCode());

    userOutDto2.setName("Ravi");
    assertNotEquals(userOutDto1.hashCode(), userOutDto2.hashCode());
  }

  @Test
  public void testEquals() {
    assertEquals(userOutDto1, userOutDto2);

    userOutDto2.setName("Ravi");
    assertNotEquals(userOutDto1, userOutDto2);

    assertEquals(userOutDto1, userOutDto1);

    assertNotEquals(userOutDto1, null);

    assertNotEquals(userOutDto1, new Object());
  }
}
