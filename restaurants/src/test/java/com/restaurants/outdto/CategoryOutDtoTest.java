package com.restaurants.outdto;

import com.restaurants.dto.CategoryOutDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CategoryOutDtoTest {

  private CategoryOutDto categoryOutDto;

  @BeforeEach
  public void setUp() {
    categoryOutDto = new CategoryOutDto();
    categoryOutDto.setId(1);
    categoryOutDto.setResturantId(1001);
    categoryOutDto.setName("Test Category");
  }

  @Test
  public void testGetId() {
    assertEquals(1, categoryOutDto.getId());
  }

  @Test
  public void testSetId() {
    categoryOutDto.setId(2);
    assertEquals(2, categoryOutDto.getId());
  }

  @Test
  public void testGetResturantId() {
    assertEquals(1001, categoryOutDto.getResturantId());
  }

  @Test
  public void testSetResturantId() {
    categoryOutDto.setResturantId(2002);
    assertEquals(2002, categoryOutDto.getResturantId());
  }

  @Test
  public void testGetName() {
    assertEquals("Test Category", categoryOutDto.getName());
  }

  @Test
  public void testSetName() {
    categoryOutDto.setName("Updated Category");
    assertEquals("Updated Category", categoryOutDto.getName());
  }
  @Test
  public void testHashCode() {
    CategoryOutDto categoryOutDto1 = new CategoryOutDto();
    categoryOutDto1.setId(1);
    categoryOutDto1.setResturantId(1001);
    categoryOutDto1.setName("Test Category");

    CategoryOutDto categoryOutDto2 = new CategoryOutDto();
    categoryOutDto2.setId(1);
    categoryOutDto2.setResturantId(1001);
    categoryOutDto2.setName("Test Category");

    assertEquals(categoryOutDto1.hashCode(), categoryOutDto2.hashCode());

    categoryOutDto2.setName("Updated Category");
    assertNotEquals(categoryOutDto1.hashCode(), categoryOutDto2.hashCode());
  }

  @Test
  public void testEquals() {
    CategoryOutDto categoryOutDto1 = new CategoryOutDto();
    categoryOutDto1.setId(1);
    categoryOutDto1.setResturantId(1001);
    categoryOutDto1.setName("Test Category");

    CategoryOutDto categoryOutDto2 = new CategoryOutDto();
    categoryOutDto2.setId(1);
    categoryOutDto2.setResturantId(1001);
    categoryOutDto2.setName("Test Category");

    assertEquals(categoryOutDto1, categoryOutDto2);

    categoryOutDto2.setName("Updated Category");
    assertNotEquals(categoryOutDto1, categoryOutDto2);

    assertEquals(categoryOutDto1, categoryOutDto1);

    assertNotEquals(categoryOutDto1, null);

    assertNotEquals(categoryOutDto1, new Object());
  }
}
