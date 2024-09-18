package com.restaurants.indto;


import com.restaurants.dto.CategoryUpdateInDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryUpdateInDtoTest {

  @Test
  public void testToString() {
    CategoryUpdateInDto categoryUpdateInDto = new CategoryUpdateInDto();
    categoryUpdateInDto.setName("FoodName");

    assertEquals("CategoryUpdateInDto(name=FoodName)", categoryUpdateInDto.toString());
  }

  @Test
  public void testHashCode() {
    CategoryUpdateInDto categoryUpdateInDto1 = new CategoryUpdateInDto();
    categoryUpdateInDto1.setName("FoodName");

    CategoryUpdateInDto categoryUpdateInDto2 = new CategoryUpdateInDto();
    categoryUpdateInDto2.setName("FoodName");

    assertEquals(categoryUpdateInDto1.hashCode(), categoryUpdateInDto2.hashCode());

    categoryUpdateInDto2.setName("DifferentName");
    assertNotEquals(categoryUpdateInDto1.hashCode(), categoryUpdateInDto2.hashCode());
  }

  @Test
  public void testEquals() {
    CategoryUpdateInDto categoryUpdateInDto1 = new CategoryUpdateInDto();
    categoryUpdateInDto1.setName("FoodName");

    CategoryUpdateInDto categoryUpdateInDto2 = new CategoryUpdateInDto();
    categoryUpdateInDto2.setName("FoodName");

    assertEquals(categoryUpdateInDto1, categoryUpdateInDto2);

    categoryUpdateInDto2.setName("DifferentName");
    assertNotEquals(categoryUpdateInDto1, categoryUpdateInDto2);

    assertEquals(categoryUpdateInDto1, categoryUpdateInDto1);

    assertNotEquals(categoryUpdateInDto1, null);

    assertNotEquals(categoryUpdateInDto1, new Object());
  }
}
