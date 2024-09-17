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
    // Test equality and hashcode
    Category anotherCategory = new Category();
    anotherCategory.setId(1); // Same placeholder ID
    anotherCategory.setRestaurantId(100); // Same placeholder Restaurant ID
    anotherCategory.setName("Test Category"); // Same placeholder Name

    assertEquals(category, anotherCategory);
    assertEquals(category.hashCode(), anotherCategory.hashCode());

    anotherCategory.setId(2); // Different ID
    assertNotEquals(category, anotherCategory);
    assertNotEquals(category.hashCode(), anotherCategory.hashCode());
  }
}
