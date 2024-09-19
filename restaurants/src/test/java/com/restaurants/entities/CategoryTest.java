package com.restaurants.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

  private Category category;

  @BeforeEach
  public void setUp() {
    // Initialize the Category object with placeholder data
    category = new Category();
    category.setId(1); // Placeholder ID
    category.setRestaurantId(100); // Placeholder Restaurant ID
    category.setName("Test Category"); // Placeholder Name
  }

  @Test
  public void testCategoryFields() {
    // Test the fields of the Category entity
    assertEquals(1, category.getId());
    assertEquals(100, category.getRestaurantId());
    assertEquals("Test Category", category.getName());
  }

  @Test
  public void testCategoryMutations() {
    // Test setting new values for the Category entity
    category.setName("Updated Category");
    assertEquals("Updated Category", category.getName());

    category.setRestaurantId(200);
    assertEquals(200, category.getRestaurantId());
  }

  @Test
  public void testCategoryEquality() {
    Category anotherCategory = new Category();
    anotherCategory.setId(1);
    anotherCategory.setRestaurantId(100);
    anotherCategory.setName("Test Category");

    assertEquals(category, anotherCategory);
    assertEquals(category.hashCode(), anotherCategory.hashCode());

    anotherCategory.setId(2);
    assertNotEquals(category, anotherCategory);
    assertNotEquals(category.hashCode(), anotherCategory.hashCode());
  }

  @Test
  public void testToString() {
    String expectedString = "Category(id=1, restaurantId=100, name=Test Category)";
    assertEquals(expectedString, category.toString());
  }
}
