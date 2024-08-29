package com.restaurants.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {

  private Category category;

  @BeforeEach
  public void setUp() {
    // Initialize the Category object
    category = new Category();
    category.setId(1);
    category.setRestaurantId(101);
    category.setName("Appetizers");
  }

  @Test
  public void testCategoryFields() {
    // Test the fields of the Category entity
    assertEquals(1, category.getId());
    assertEquals(101, category.getRestaurantId());
    assertEquals("Appetizers", category.getName());
  }

  @Test
  public void testCategoryMutations() {
    // Test setting new values for the Category entity
    category.setName("Main Course");
    assertEquals("Main Course", category.getName());

    category.setRestaurantId(102);
    assertEquals(102, category.getRestaurantId());
  }

  @Test
  public void testCategoryEquality() {
    // Test equality and hashcode
    Category anotherCategory = new Category();
    anotherCategory.setId(1);
    anotherCategory.setRestaurantId(101);
    anotherCategory.setName("Appetizers");

    assertEquals(category, anotherCategory);
    assertEquals(category.hashCode(), anotherCategory.hashCode());

    anotherCategory.setId(2);
    assertNotEquals(category, anotherCategory);
    assertNotEquals(category.hashCode(), anotherCategory.hashCode());
  }
}
