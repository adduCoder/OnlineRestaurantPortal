package com.restaurants.outdto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantOutDtoTest {

  private RestaurantOutDto restaurantOutDto;

  @BeforeEach
  public void setUp() {
    // Initialize the RestaurantOutDto object
    restaurantOutDto = new RestaurantOutDto();
    restaurantOutDto.setId(1);
    restaurantOutDto.setUserId(101);
    restaurantOutDto.setRestaurantName("Italian Bistro");
    restaurantOutDto.setAddress("123 Main Street, Springfield");
    restaurantOutDto.setContactNumber("9876543210");
    restaurantOutDto.setDescription("A cozy Italian restaurant with a variety of classic dishes.");
    restaurantOutDto.setImageUrl("https://example.com/restaurant.jpg");
  }

  @Test
  public void testRestaurantOutDtoFields() {
    // Test the fields of RestaurantOutDto
    assertEquals(1, restaurantOutDto.getId());
    assertEquals(101, restaurantOutDto.getUserId());
    assertEquals("Italian Bistro", restaurantOutDto.getRestaurantName());
    assertEquals("123 Main Street, Springfield", restaurantOutDto.getAddress());
    assertEquals("9876543210", restaurantOutDto.getContactNumber());
    assertEquals("A cozy Italian restaurant with a variety of classic dishes.", restaurantOutDto.getDescription());
    assertEquals("https://example.com/restaurant.jpg", restaurantOutDto.getImageUrl());
  }

  @Test
  public void testRestaurantOutDtoMutations() {
    // Test setting new values for RestaurantOutDto
    restaurantOutDto.setRestaurantName("Pizza Palace");
    assertEquals("Pizza Palace", restaurantOutDto.getRestaurantName());

    restaurantOutDto.setContactNumber("8765432109");
    assertEquals("8765432109", restaurantOutDto.getContactNumber());
  }
}
