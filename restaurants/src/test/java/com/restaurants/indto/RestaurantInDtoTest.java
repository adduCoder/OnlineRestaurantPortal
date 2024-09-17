package com.restaurants.indto;

import com.restaurants.dto.RestaurantInDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantInDtoTest {

  private RestaurantInDto restaurantInDto;

  @BeforeEach
  public void setUp() {
    restaurantInDto = new RestaurantInDto();
    restaurantInDto.setUserId(1);
    restaurantInDto.setRestaurantName("Test Restaurant");
    restaurantInDto.setAddress("123 Test Avenue, Test City");
    restaurantInDto.setContactNumber("1234567890");
    restaurantInDto.setDescription("Test description for the restaurant");
  }

  @Test
  public void testGetUserId() {
    assertEquals(1, restaurantInDto.getUserId());
  }

  @Test
  public void testSetUserId() {
    restaurantInDto.setUserId(2);
    assertEquals(2, restaurantInDto.getUserId());
  }

  @Test
  public void testGetRestaurantName() {
    assertEquals("Test Restaurant", restaurantInDto.getRestaurantName());
  }

  @Test
  public void testSetRestaurantName() {
    restaurantInDto.setRestaurantName("Updated Restaurant");
    assertEquals("Updated Restaurant", restaurantInDto.getRestaurantName());
  }

  @Test
  public void testGetAddress() {
    assertEquals("123 Test Avenue, Test City", restaurantInDto.getAddress());
  }

  @Test
  public void testSetAddress() {
    restaurantInDto.setAddress("456 Updated Avenue, Updated City");
    assertEquals("456 Updated Avenue, Updated City", restaurantInDto.getAddress());
  }

  @Test
  public void testGetContactNumber() {
    assertEquals("1234567890", restaurantInDto.getContactNumber());
  }

  @Test
  public void testSetContactNumber() {
    restaurantInDto.setContactNumber("0987654321");
    assertEquals("0987654321", restaurantInDto.getContactNumber());
  }

  @Test
  public void testGetDescription() {
    assertEquals("Test description for the restaurant", restaurantInDto.getDescription());
  }

  @Test
  public void testSetDescription() {
    restaurantInDto.setDescription("Updated description");
    assertEquals("Updated description", restaurantInDto.getDescription());
  }

  @Test
  public void testHashCode() {
    RestaurantInDto restaurantInDto1 = new RestaurantInDto();
    restaurantInDto1.setUserId(1);
    restaurantInDto1.setRestaurantName("Test Restaurant");
    restaurantInDto1.setAddress("123 Test Avenue, Test City");
    restaurantInDto1.setContactNumber("1234567890");
    restaurantInDto1.setDescription("Test description for the restaurant");

    RestaurantInDto restaurantInDto2 = new RestaurantInDto();
    restaurantInDto2.setUserId(1);
    restaurantInDto2.setRestaurantName("Test Restaurant");
    restaurantInDto2.setAddress("123 Test Avenue, Test City");
    restaurantInDto2.setContactNumber("1234567890");
    restaurantInDto2.setDescription("Test description for the restaurant");

    assertEquals(restaurantInDto1.hashCode(), restaurantInDto2.hashCode());

    restaurantInDto2.setRestaurantName("Updated Restaurant");
    assertNotEquals(restaurantInDto1.hashCode(), restaurantInDto2.hashCode());
  }

  @Test
  public void testEquals() {
    RestaurantInDto restaurantInDto1 = new RestaurantInDto();
    restaurantInDto1.setUserId(1);
    restaurantInDto1.setRestaurantName("Test Restaurant");
    restaurantInDto1.setAddress("123 Test Avenue, Test City");
    restaurantInDto1.setContactNumber("1234567890");
    restaurantInDto1.setDescription("Test description for the restaurant");

    RestaurantInDto restaurantInDto2 = new RestaurantInDto();
    restaurantInDto2.setUserId(1);
    restaurantInDto2.setRestaurantName("Test Restaurant");
    restaurantInDto2.setAddress("123 Test Avenue, Test City");
    restaurantInDto2.setContactNumber("1234567890");
    restaurantInDto2.setDescription("Test description for the restaurant");

    assertEquals(restaurantInDto1, restaurantInDto2);

    restaurantInDto2.setRestaurantName("Updated Restaurant");
    assertNotEquals(restaurantInDto1, restaurantInDto2);

    assertEquals(restaurantInDto1, restaurantInDto1);

    assertNotEquals(restaurantInDto1, null);

    assertNotEquals(restaurantInDto1, new Object());
  }
}
