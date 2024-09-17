package com.restaurants.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodItemTest {

  private FoodItem foodItem;

  @BeforeEach
  public void setUp() {
    // Initialize the FoodItem object with placeholder data
    foodItem = new FoodItem();
    foodItem.setId(1); // Placeholder ID
    foodItem.setFoodName("Test Food"); // Placeholder Food Name
    foodItem.setRestaurantId(100); // Placeholder Restaurant ID
    foodItem.setDescription("Test Description"); // Placeholder Description
    foodItem.setCategoryId(20); // Placeholder Category ID
    foodItem.setIsAvailable(true); // Placeholder Availability
    foodItem.setPrice(9.99); // Placeholder Price
  }

  @Test
  public void testFoodItemFields() {
    // Test the fields of the FoodItem entity
    assertEquals(1, foodItem.getId());
    assertEquals("Test Food", foodItem.getFoodName());
    assertEquals(100, foodItem.getRestaurantId());
    assertEquals("Test Description", foodItem.getDescription());
    assertEquals(20, foodItem.getCategoryId());
    assertTrue(foodItem.getIsAvailable());
    assertEquals(9.99, foodItem.getPrice());
  }

  @Test
  public void testFoodItemMutations() {
    // Test setting new values for the FoodItem entity
    foodItem.setFoodName("Updated Food");
    assertEquals("Updated Food", foodItem.getFoodName());

    foodItem.setPrice(8.99);
    assertEquals(8.99, foodItem.getPrice());

    foodItem.setIsAvailable(false);
    assertFalse(foodItem.getIsAvailable());
  }

  @Test
  public void testFoodItemEquality() {
    // Test equality and hashcode
    FoodItem anotherFoodItem = new FoodItem();
    anotherFoodItem.setId(1); // Same placeholder ID
    anotherFoodItem.setFoodName("Test Food"); // Same placeholder Food Name
    anotherFoodItem.setRestaurantId(100); // Same placeholder Restaurant ID
    anotherFoodItem.setDescription("Test Description"); // Same placeholder Description
    anotherFoodItem.setCategoryId(20); // Same placeholder Category ID
    anotherFoodItem.setIsAvailable(true); // Same placeholder Availability
    anotherFoodItem.setPrice(9.99); // Same placeholder Price

    assertEquals(foodItem, anotherFoodItem);
    assertEquals(foodItem.hashCode(), anotherFoodItem.hashCode());

    anotherFoodItem.setId(2); // Different ID
    assertNotEquals(foodItem, anotherFoodItem);
    assertNotEquals(foodItem.hashCode(), anotherFoodItem.hashCode());
  }
}
