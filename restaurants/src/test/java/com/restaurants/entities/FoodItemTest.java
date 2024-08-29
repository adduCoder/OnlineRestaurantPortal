package com.restaurants.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodItemTest {

  private FoodItem foodItem;

  @BeforeEach
  public void setUp() {
    // Initialize the FoodItem object
    foodItem = new FoodItem();
    foodItem.setId(1);
    foodItem.setFoodName("Pizza");
    foodItem.setRestaurantId(101);
    foodItem.setDescription("Cheesy Margherita Pizza");
    foodItem.setCategoryId(10);
    foodItem.setIsAvailable(true);
    foodItem.setPrice(12.99);
    foodItem.setImageUrl("http://example.com/pizza.jpg");
  }

  @Test
  public void testFoodItemFields() {
    // Test the fields of the FoodItem entity
    assertEquals(1, foodItem.getId());
    assertEquals("Pizza", foodItem.getFoodName());
    assertEquals(101, foodItem.getRestaurantId());
    assertEquals("Cheesy Margherita Pizza", foodItem.getDescription());
    assertEquals(10, foodItem.getCategoryId());
    assertTrue(foodItem.getIsAvailable());
    assertEquals(12.99, foodItem.getPrice());
    assertEquals("http://example.com/pizza.jpg", foodItem.getImageUrl());
  }

  @Test
  public void testFoodItemMutations() {
    // Test setting new values for the FoodItem entity
    foodItem.setFoodName("Pasta");
    assertEquals("Pasta", foodItem.getFoodName());

    foodItem.setPrice(10.99);
    assertEquals(10.99, foodItem.getPrice());

    foodItem.setIsAvailable(false);
    assertFalse(foodItem.getIsAvailable());
  }

  @Test
  public void testFoodItemEquality() {
    // Test equality and hashcode
    FoodItem anotherFoodItem = new FoodItem();
    anotherFoodItem.setId(1);
    anotherFoodItem.setFoodName("Pizza");
    anotherFoodItem.setRestaurantId(101);
    anotherFoodItem.setDescription("Cheesy Margherita Pizza");
    anotherFoodItem.setCategoryId(10);
    anotherFoodItem.setIsAvailable(true);
    anotherFoodItem.setPrice(12.99);
    anotherFoodItem.setImageUrl("http://example.com/pizza.jpg");

    assertEquals(foodItem, anotherFoodItem);
    assertEquals(foodItem.hashCode(), anotherFoodItem.hashCode());

    anotherFoodItem.setId(2);
    assertNotEquals(foodItem, anotherFoodItem);
    assertNotEquals(foodItem.hashCode(), anotherFoodItem.hashCode());
  }
}
