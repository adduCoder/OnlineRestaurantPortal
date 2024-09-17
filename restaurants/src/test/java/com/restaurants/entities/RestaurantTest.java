package com.restaurants.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {

  private Restaurant restaurant;

  @BeforeEach
  public void setUp() {
    // Initialize the Restaurant object with placeholder data
    restaurant = new Restaurant();
    restaurant.setId(1); // Placeholder ID
    restaurant.setUserId(1001); // Placeholder User ID
    restaurant.setRestaurantName("Test Restaurant"); // Placeholder Restaurant Name
    restaurant.setAddress("123 Test St, Test City"); // Placeholder Address
    restaurant.setContactNumber("1234567890"); // Placeholder Contact Number
    restaurant.setDescription("A test description for restaurant"); // Placeholder Description
    //restaurant.setImageUrl("http://example.com/testrestaurant.jpg"); // Uncomment if needed
  }

  @Test
  public void testRestaurantFields() {
    // Test the fields of the Restaurant entity
    assertEquals(1, restaurant.getId());
    assertEquals(1001, restaurant.getUserId());
    assertEquals("Test Restaurant", restaurant.getRestaurantName());
    assertEquals("123 Test St, Test City", restaurant.getAddress());
    assertEquals("1234567890", restaurant.getContactNumber());
    assertEquals("A test description for restaurant", restaurant.getDescription());
    //assertEquals("http://example.com/testrestaurant.jpg", restaurant.getImageUrl()); // Uncomment if needed
  }

  @Test
  public void testRestaurantMutations() {
    // Test setting new values for the Restaurant entity
    restaurant.setRestaurantName("Updated Restaurant");
    assertEquals("Updated Restaurant", restaurant.getRestaurantName());

    restaurant.setAddress("456 Update Ave, Update City");
    assertEquals("456 Update Ave, Update City", restaurant.getAddress());

    restaurant.setContactNumber("0987654321");
    assertEquals("0987654321", restaurant.getContactNumber());
  }

  @Test
  public void testRestaurantEquality() {
    // Test equality and hashcode
    Restaurant anotherRestaurant = new Restaurant();
    anotherRestaurant.setId(1); // Placeholder ID
    anotherRestaurant.setUserId(1001); // Placeholder User ID
    anotherRestaurant.setRestaurantName("Test Restaurant"); // Placeholder Restaurant Name
    anotherRestaurant.setAddress("123 Test St, Test City"); // Placeholder Address
    anotherRestaurant.setContactNumber("1234567890"); // Placeholder Contact Number
    anotherRestaurant.setDescription("A test description for restaurant"); // Placeholder Description
    //anotherRestaurant.setImageUrl("http://example.com/testrestaurant.jpg"); // Uncomment if needed

    assertEquals(restaurant, anotherRestaurant);
    assertEquals(restaurant.hashCode(), anotherRestaurant.hashCode());

    anotherRestaurant.setId(2); // Different ID
    assertNotEquals(restaurant, anotherRestaurant);
    assertNotEquals(restaurant.hashCode(), anotherRestaurant.hashCode());
  }
}
