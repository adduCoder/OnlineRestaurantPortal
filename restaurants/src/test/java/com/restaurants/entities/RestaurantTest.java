package com.restaurants.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {

  private Restaurant restaurant;

  @BeforeEach
  public void setUp() {
    // Initialize the Restaurant object
    restaurant = new Restaurant();
    restaurant.setId(1);
    restaurant.setUserId(1001);
    restaurant.setRestaurantName("The Spice House");
    restaurant.setAddress("123 Main St, Springfield");
    restaurant.setContactNumber("9876543210");
    restaurant.setDescription("A place for authentic spices and cuisine");
    restaurant.setImageUrl("http://example.com/spicehouse.jpg");
  }

  @Test
  public void testRestaurantFields() {
    // Test the fields of the Restaurant entity
    assertEquals(1, restaurant.getId());
    assertEquals(1001, restaurant.getUserId());
    assertEquals("The Spice House", restaurant.getRestaurantName());
    assertEquals("123 Main St, Springfield", restaurant.getAddress());
    assertEquals("9876543210", restaurant.getContactNumber());
    assertEquals("A place for authentic spices and cuisine", restaurant.getDescription());
    assertEquals("http://example.com/spicehouse.jpg", restaurant.getImageUrl());
  }

  @Test
  public void testRestaurantMutations() {
    // Test setting new values for the Restaurant entity
    restaurant.setRestaurantName("The Herb Garden");
    assertEquals("The Herb Garden", restaurant.getRestaurantName());

    restaurant.setAddress("456 Elm St, Metropolis");
    assertEquals("456 Elm St, Metropolis", restaurant.getAddress());

    restaurant.setContactNumber("8765432109");
    assertEquals("8765432109", restaurant.getContactNumber());
  }

  @Test
  public void testRestaurantEquality() {
    // Test equality and hashcode
    Restaurant anotherRestaurant = new Restaurant();
    anotherRestaurant.setId(1);
    anotherRestaurant.setUserId(1001);
    anotherRestaurant.setRestaurantName("The Spice House");
    anotherRestaurant.setAddress("123 Main St, Springfield");
    anotherRestaurant.setContactNumber("9876543210");
    anotherRestaurant.setDescription("A place for authentic spices and cuisine");
    anotherRestaurant.setImageUrl("http://example.com/spicehouse.jpg");

    assertEquals(restaurant, anotherRestaurant);
    assertEquals(restaurant.hashCode(), anotherRestaurant.hashCode());

    anotherRestaurant.setId(2);
    assertNotEquals(restaurant, anotherRestaurant);
    assertNotEquals(restaurant.hashCode(), anotherRestaurant.hashCode());
  }
}
