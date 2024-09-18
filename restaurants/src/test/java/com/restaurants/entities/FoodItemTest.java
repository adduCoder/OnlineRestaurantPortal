package com.restaurants.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodItemTest {

  private FoodItem foodItem;

  @BeforeEach
  public void setUp() {
    foodItem = new FoodItem();
    foodItem.setId(1);
    foodItem.setFoodName("Test Food");
    foodItem.setRestaurantId(100);
    foodItem.setDescription("Test Description");
    foodItem.setCategoryId(20);
    foodItem.setIsAvailable(true);
    foodItem.setPrice(9.99);
  }

  @Test
  public void testFoodItemFields() {
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
    foodItem.setFoodName("Updated Food");
    assertEquals("Updated Food", foodItem.getFoodName());

    foodItem.setPrice(8.99);
    assertEquals(8.99, foodItem.getPrice());

    foodItem.setIsAvailable(false);
    assertFalse(foodItem.getIsAvailable());
  }

  @Test
  public void testFoodItemEquality() {
    FoodItem anotherFoodItem = new FoodItem();
    anotherFoodItem.setId(1);
    anotherFoodItem.setFoodName("Test Food");
    anotherFoodItem.setRestaurantId(100);
    anotherFoodItem.setDescription("Test Description");
    anotherFoodItem.setCategoryId(20);
    anotherFoodItem.setIsAvailable(true);
    anotherFoodItem.setPrice(9.99);

    assertEquals(foodItem, anotherFoodItem);
    assertEquals(foodItem.hashCode(), anotherFoodItem.hashCode());

    anotherFoodItem.setId(2);
    assertNotEquals(foodItem, anotherFoodItem);
    assertNotEquals(foodItem.hashCode(), anotherFoodItem.hashCode());
  }

  @Test
  public void testToString() {

    String expectedString = "FoodItem(id=1, foodName=Test Food, restaurantId=100, description=Test Description, categoryId=20, isAvailable=true, price=9.99, imageData=null)";
    assertEquals(expectedString, foodItem.toString());
  }
}
