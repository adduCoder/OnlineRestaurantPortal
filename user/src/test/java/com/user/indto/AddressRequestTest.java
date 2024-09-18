package com.user.indto;

import com.user.dto.AddressInDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Unit tests for {@link AddressInDto} class.
 * These tests verify the correctness of getter and setter methods,
 * the {@link AddressInDto#toString()} method, and the equals and hashCode methods.
 */
public class AddressRequestTest {

  /** The {@link AddressInDto} instance used in the tests. */
  private final AddressInDto addressInDto = new AddressInDto();

  /**
   * Sets up a new {@link AddressInDto} instance before each test.
   */
  @BeforeEach
  public void setUp() {
    addressInDto.setStreet(null);
    addressInDto.setState(null);
    addressInDto.setCity(null);
    addressInDto.setPinCode(null);
    addressInDto.setUserId(null);
  }

  /**
   * Tests the getter method for the street field.
   */
  @Test
  public void testGetStreet() {
    addressInDto.setStreet("Sample Street");
    assertEquals("Sample Street", addressInDto.getStreet());
  }

  /**
   * Tests the setter method for the street field.
   */
  @Test
  public void testSetStreet() {
    addressInDto.setStreet("Another Sample Street");
    assertEquals("Another Sample Street", addressInDto.getStreet());
  }

  /**
   * Tests the getter method for the state field.
   */
  @Test
  public void testGetState() {
    addressInDto.setState("StateA");
    assertEquals("StateA", addressInDto.getState());
  }

  /**
   * Tests the setter method for the state field.
   */
  @Test
  public void testSetState() {
    addressInDto.setState("StateB");
    assertEquals("StateB", addressInDto.getState());
  }

  /**
   * Tests the getter method for the city field.
   */
  @Test
  public void testGetCity() {
    addressInDto.setCity("CityX");
    assertEquals("CityX", addressInDto.getCity());
  }

  /**
   * Tests the setter method for the city field.
   */
  @Test
  public void testSetCity() {
    addressInDto.setCity("CityY");
    assertEquals("CityY", addressInDto.getCity());
  }

  /**
   * Tests the getter method for the pinCode field.
   */
  @Test
  public void testGetPinCode() {
    addressInDto.setPinCode(111111);
    assertEquals(111111, addressInDto.getPinCode());
  }

  /**
   * Tests the setter method for the pinCode field.
   */
  @Test
  public void testSetPinCode() {
    addressInDto.setPinCode(222222);
    assertEquals(222222, addressInDto.getPinCode());
  }

  /**
   * Tests the getter method for the userId field.
   */
  @Test
  public void testGetUserId() {
    addressInDto.setUserId(10);
    assertEquals(10, addressInDto.getUserId());
  }

  /**
   * Tests the setter method for the userId field.
   */
  @Test
  public void testSetUserId() {
    addressInDto.setUserId(20);
    assertEquals(20, addressInDto.getUserId());
  }

  /**
   * Tests the {@link AddressInDto#toString()} method.
   */
  @Test
  public void testToString() {
    addressInDto.setStreet("Sample Street");
    addressInDto.setState("StateA");
    addressInDto.setCity("CityX");
    addressInDto.setPinCode(111111);
    addressInDto.setUserId(10);
    String expected = "AddressInDto(street=Sample Street, state=StateA, city=CityX, pinCode=111111, userId=10)";
    assertEquals(expected, addressInDto.toString());
  }

  /**
   * Tests the {@link AddressInDto#hashCode()} method.
   */
  @Test
  public void testHashCode() {
    final AddressInDto addressInDto1 = new AddressInDto();
    addressInDto1.setStreet("Sample Street");
    addressInDto1.setState("StateA");
    addressInDto1.setCity("CityX");
    addressInDto1.setPinCode(111111);
    addressInDto1.setUserId(10);

    final AddressInDto addressInDto2 = new AddressInDto();
    addressInDto2.setStreet("Sample Street");
    addressInDto2.setState("StateA");
    addressInDto2.setCity("CityX");
    addressInDto2.setPinCode(111111);
    addressInDto2.setUserId(10);

    assertEquals(addressInDto1.hashCode(), addressInDto2.hashCode());
  }

  /**
   * Tests the {@link AddressInDto#equals(Object)} method.
   */
  @Test
  public void testEquals() {
    final AddressInDto addressInDto1 = new AddressInDto();
    addressInDto1.setStreet("Sample Street");
    addressInDto1.setState("StateA");
    addressInDto1.setCity("CityX");
    addressInDto1.setPinCode(111111);
    addressInDto1.setUserId(10);

    final AddressInDto addressInDto2 = new AddressInDto();
    addressInDto2.setStreet("Sample Street");
    addressInDto2.setState("StateA");
    addressInDto2.setCity("CityX");
    addressInDto2.setPinCode(111111);
    addressInDto2.setUserId(10);

    assertEquals(addressInDto1, addressInDto2);

    addressInDto2.setCity("Different City");
    assertNotEquals(addressInDto1, addressInDto2);
  }

}
