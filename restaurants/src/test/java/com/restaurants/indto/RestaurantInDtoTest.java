package com.restaurants.indto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantInDtoTest {

  private RestaurantInDto restaurantInDto;

  @BeforeEach
  public void setUp() {
    restaurantInDto = new RestaurantInDto();
    // Initialize the object with valid data
    restaurantInDto.setUserId(1);
    restaurantInDto.setRestaurantName("Cafe Mocha");
    restaurantInDto.setAddress("123 Coffee Street, Indore");
    restaurantInDto.setContactNumber("7896541230");
    restaurantInDto.setDescription("A cozy place for coffee lovers");
    restaurantInDto.setImageUrl("http://example.com/image.jpg");
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
    assertEquals("Cafe Mocha", restaurantInDto.getRestaurantName());
  }

  @Test
  public void testSetRestaurantName() {
    restaurantInDto.setRestaurantName("Bistro Delight");
    assertEquals("Bistro Delight", restaurantInDto.getRestaurantName());
  }

  @Test
  public void testGetAddress() {
    assertEquals("123 Coffee Street, Indore", restaurantInDto.getAddress());
  }

  @Test
  public void testSetAddress() {
    restaurantInDto.setAddress("456 Tea Avenue, Bhopal");
    assertEquals("456 Tea Avenue, Bhopal", restaurantInDto.getAddress());
  }

  @Test
  public void testGetContactNumber() {
    assertEquals("7896541230", restaurantInDto.getContactNumber());
  }

  @Test
  public void testSetContactNumber() {
    restaurantInDto.setContactNumber("9876543210");
    assertEquals("9876543210", restaurantInDto.getContactNumber());
  }

  @Test
  public void testGetDescription() {
    assertEquals("A cozy place for coffee lovers", restaurantInDto.getDescription());
  }

  @Test
  public void testSetDescription() {
    restaurantInDto.setDescription("A perfect spot for brunch");
    assertEquals("A perfect spot for brunch", restaurantInDto.getDescription());
  }

  @Test
  public void testGetImageUrl() {
    assertEquals("http://example.com/image.jpg", restaurantInDto.getImageUrl());
  }

  @Test
  public void testSetImageUrl() {
    restaurantInDto.setImageUrl("http://example.com/newimage.jpg");
    assertEquals("http://example.com/newimage.jpg", restaurantInDto.getImageUrl());
  }

  @Test
  public void testToString() {
    String expected = "RestaurantInDto(userId=1, " +
      "restaurantName=Cafe Mocha, address=123 Coffee Street, Indore, contactNumber=7896541230" +
      ", description=A cozy place for coffee lovers, imageUrl=http://example.com/image.jpg)";
    assertEquals(expected, restaurantInDto.toString());
  }

  @Test
  public void testHashCode() {
    RestaurantInDto restaurantInDto1 = new RestaurantInDto();
    restaurantInDto1.setUserId(1);
    restaurantInDto1.setRestaurantName("Cafe Mocha");
    restaurantInDto1.setAddress("123 Coffee Street, Indore");
    restaurantInDto1.setContactNumber("7896541230");
    restaurantInDto1.setDescription("A cozy place for coffee lovers");
    restaurantInDto1.setImageUrl("http://example.com/image.jpg");

    RestaurantInDto restaurantInDto2 = new RestaurantInDto();
    restaurantInDto2.setUserId(1);
    restaurantInDto2.setRestaurantName("Cafe Mocha");
    restaurantInDto2.setAddress("123 Coffee Street, Indore");
    restaurantInDto2.setContactNumber("7896541230");
    restaurantInDto2.setDescription("A cozy place for coffee lovers");
    restaurantInDto2.setImageUrl("http://example.com/image.jpg");

    assertEquals(restaurantInDto1.hashCode(), restaurantInDto2.hashCode());

    restaurantInDto2.setRestaurantName("Bistro Delight");
    assertNotEquals(restaurantInDto1.hashCode(), restaurantInDto2.hashCode());
  }

  @Test
  public void testEquals() {
    RestaurantInDto restaurantInDto1 = new RestaurantInDto();
    restaurantInDto1.setUserId(1);
    restaurantInDto1.setRestaurantName("Cafe Mocha");
    restaurantInDto1.setAddress("123 Coffee Street, Indore");
    restaurantInDto1.setContactNumber("7896541230");
    restaurantInDto1.setDescription("A cozy place for coffee lovers");
    restaurantInDto1.setImageUrl("http://example.com/image.jpg");

    RestaurantInDto restaurantInDto2 = new RestaurantInDto();
    restaurantInDto2.setUserId(1);
    restaurantInDto2.setRestaurantName("Cafe Mocha");
    restaurantInDto2.setAddress("123 Coffee Street, Indore");
    restaurantInDto2.setContactNumber("7896541230");
    restaurantInDto2.setDescription("A cozy place for coffee lovers");
    restaurantInDto2.setImageUrl("http://example.com/image.jpg");

    assertEquals(restaurantInDto1, restaurantInDto2);

    restaurantInDto2.setRestaurantName("Bistro Delight");
    assertNotEquals(restaurantInDto1, restaurantInDto2);

    assertEquals(restaurantInDto1, restaurantInDto1);

    assertNotEquals(restaurantInDto1, null);

    assertNotEquals(restaurantInDto1, new Object());
  }
}
