package com.restaurants.outdto;

import com.restaurants.dto.CategoryOutDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryOutDtoTest {

  private CategoryOutDto categoryOutDto;

  @BeforeEach
  public void setUp() {
    categoryOutDto = new CategoryOutDto();
    // Initialize the object with valid data
    categoryOutDto.setId(1);
    categoryOutDto.setResturantId(101);
    categoryOutDto.setName("Appetizers");
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
    assertEquals(101, categoryOutDto.getResturantId());
  }

  @Test
  public void testSetResturantId() {
    categoryOutDto.setResturantId(202);
    assertEquals(202, categoryOutDto.getResturantId());
  }

  @Test
  public void testGetName() {
    assertEquals("Appetizers", categoryOutDto.getName());
  }

  @Test
  public void testSetName() {
    categoryOutDto.setName("Desserts");
    assertEquals("Desserts", categoryOutDto.getName());
  }

  @Test
  public void testToString() {
    String expected = "CategoryOutDto(id=1, resturantId=101, name=Appetizers)";
    assertEquals(expected, categoryOutDto.toString());
  }

  @Test
  public void testHashCode() {
    CategoryOutDto categoryOutDto1 = new CategoryOutDto();
    categoryOutDto1.setId(1);
    categoryOutDto1.setResturantId(101);
    categoryOutDto1.setName("Appetizers");

    CategoryOutDto categoryOutDto2 = new CategoryOutDto();
    categoryOutDto2.setId(1);
    categoryOutDto2.setResturantId(101);
    categoryOutDto2.setName("Appetizers");

    assertEquals(categoryOutDto1.hashCode(), categoryOutDto2.hashCode());

    categoryOutDto2.setName("Main Course");
    assertNotEquals(categoryOutDto1.hashCode(), categoryOutDto2.hashCode());
  }

  @Test
  public void testEquals() {
    CategoryOutDto categoryOutDto1 = new CategoryOutDto();
    categoryOutDto1.setId(1);
    categoryOutDto1.setResturantId(101);
    categoryOutDto1.setName("Appetizers");

    CategoryOutDto categoryOutDto2 = new CategoryOutDto();
    categoryOutDto2.setId(1);
    categoryOutDto2.setResturantId(101);
    categoryOutDto2.setName("Appetizers");

    assertEquals(categoryOutDto1, categoryOutDto2);

    categoryOutDto2.setName("Main Course");
    assertNotEquals(categoryOutDto1, categoryOutDto2);

    assertEquals(categoryOutDto1, categoryOutDto1);

    assertNotEquals(categoryOutDto1, null);

    assertNotEquals(categoryOutDto1, new Object());
  }
}
