package com.restaurants.indto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CategoryInDtoTest {

  private CategoryInDto categoryInDto;

  @BeforeEach
  public void setUp() {
    categoryInDto = new CategoryInDto();
  }

  @Test
  public void testGetRestaurantId() {
    categoryInDto.setRestaurantId(1);
    assertEquals(1, categoryInDto.getRestaurantId());
  }

  @Test
  public void testSetRestaurantId() {
    categoryInDto.setRestaurantId(2);
    assertEquals(2, categoryInDto.getRestaurantId());
  }

  @Test
  public void testGetName() {
    categoryInDto.setName("Category A");
    assertEquals("Category A", categoryInDto.getName());
  }

  @Test
  public void testSetName() {
    categoryInDto.setName("Category B");
    assertEquals("Category B", categoryInDto.getName());
  }

  @Test
  public void testToString() {
    categoryInDto.setRestaurantId(1);
    categoryInDto.setName("Category A");

    String expected = "CategoryInDto(restaurantId=1, name=Category A)";
    assertEquals(expected, categoryInDto.toString());
  }

  @Test
  public void testHashCode() {
    CategoryInDto categoryInDto1 = new CategoryInDto();
    categoryInDto1.setRestaurantId(1);
    categoryInDto1.setName("Category A");

    CategoryInDto categoryInDto2 = new CategoryInDto();
    categoryInDto2.setRestaurantId(1);
    categoryInDto2.setName("Category A");

    assertEquals(categoryInDto1.hashCode(), categoryInDto2.hashCode());

    categoryInDto2.setName("Category B");
    assertNotEquals(categoryInDto1.hashCode(), categoryInDto2.hashCode());
  }

  @Test
  public void testEquals() {
    CategoryInDto categoryInDto1 = new CategoryInDto();
    categoryInDto1.setRestaurantId(1);
    categoryInDto1.setName("Category A");

    CategoryInDto categoryInDto2 = new CategoryInDto();
    categoryInDto2.setRestaurantId(1);
    categoryInDto2.setName("Category A");

    assertEquals(categoryInDto1, categoryInDto2);

    categoryInDto2.setName("Category B");
    assertNotEquals(categoryInDto1, categoryInDto2);

    assertEquals(categoryInDto1, categoryInDto1);

    assertNotEquals(categoryInDto1, null);

    assertNotEquals(categoryInDto1, new Object());
  }
}
