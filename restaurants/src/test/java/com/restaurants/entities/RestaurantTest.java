package com.restaurants.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {

  private Restaurant restaurant;

  @BeforeEach
  public void setUp() {
    restaurant = new Restaurant();
    restaurant.setId(1);
    restaurant.setUserId(1001);
    restaurant.setRestaurantName("Test Restaurant");
    restaurant.setAddress("123 Test St, Test City");
    restaurant.setContactNumber("1234567890");
    restaurant.setDescription("A test description for restaurant");
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
    anotherRestaurant.setId(1);
    anotherRestaurant.setUserId(1001);
    anotherRestaurant.setRestaurantName("Test Restaurant");
    anotherRestaurant.setAddress("123 Test St, Test City");
    anotherRestaurant.setContactNumber("1234567890");
    anotherRestaurant.setDescription("A test description for restaurant");

    assertEquals(restaurant, anotherRestaurant);
    assertEquals(restaurant.hashCode(), anotherRestaurant.hashCode());

    anotherRestaurant.setId(2);
    assertNotEquals(restaurant, anotherRestaurant);
    assertNotEquals(restaurant.hashCode(), anotherRestaurant.hashCode());
  }

  @Test
  public void testToString() {
    String expectedString = "Restaurant(id=1, userId=1001, restaurantName=Test Restaurant, address=123 Test St, Test City, contactNumber=1234567890, description=A test description for restaurant, imageData=null)";
    assertEquals(expectedString, restaurant.toString());
  }

}
