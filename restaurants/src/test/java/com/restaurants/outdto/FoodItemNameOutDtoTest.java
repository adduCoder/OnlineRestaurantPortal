package com.restaurants.outdto;

import static org.junit.jupiter.api.Assertions.*;

import com.restaurants.dto.FoodItemNameOutDto;
import org.junit.jupiter.api.Test;

public class FoodItemNameOutDtoTest {

  @Test
  public void testGetterAndSetter() {
    FoodItemNameOutDto foodItemDto = new FoodItemNameOutDto();

    assertNull(foodItemDto.getId());
    Integer id = 101;
    foodItemDto.setId(id);
    assertEquals(id, foodItemDto.getId());

    assertNull(foodItemDto.getFoodItemName());
    String foodItemName = "FoodName";
    foodItemDto.setFoodItemName(foodItemName);
    assertEquals(foodItemName, foodItemDto.getFoodItemName());

    assertNull(foodItemDto.getPrice());
    Double price = 9.99;
    foodItemDto.setPrice(price);
    assertEquals(price, foodItemDto.getPrice());
  }

  @Test
  public void testToString() {
    FoodItemNameOutDto foodItemDto = new FoodItemNameOutDto();

    Integer id = 101;
    foodItemDto.setId(id);

    String foodItemName = "FoodName";
    foodItemDto.setFoodItemName(foodItemName);

    Double price = 9.99;
    foodItemDto.setPrice(price);

    assertEquals("FoodItemNameOutDto(id=101, foodItemName=FoodName, price=9.99)", foodItemDto.toString());
  }

  @Test
  public void testEqualsAndHashcode() {
    Integer id = 101;
    String foodItemName = "FoodName";
    Double price = 9.99;

    FoodItemNameOutDto foodItemDto1 = buildFoodItemNameOutDto(id, foodItemName, price);

    assertEquals(foodItemDto1, foodItemDto1);
    assertEquals(foodItemDto1.hashCode(), foodItemDto1.hashCode());

    assertNotEquals(foodItemDto1, new Object());

    FoodItemNameOutDto foodItemDto2 = buildFoodItemNameOutDto(id, foodItemName, price);
    assertEquals(foodItemDto1, foodItemDto2);
    assertEquals(foodItemDto1.hashCode(), foodItemDto2.hashCode());

    foodItemDto2 = buildFoodItemNameOutDto(id + 1, foodItemName, price);
    assertNotEquals(foodItemDto1, foodItemDto2);
    assertNotEquals(foodItemDto1.hashCode(), foodItemDto2.hashCode());

    foodItemDto2 = buildFoodItemNameOutDto(id, foodItemName + " ", price);
    assertNotEquals(foodItemDto1, foodItemDto2);
    assertNotEquals(foodItemDto1.hashCode(), foodItemDto2.hashCode());

    foodItemDto2 = buildFoodItemNameOutDto(id, foodItemName, price + 1);
    assertNotEquals(foodItemDto1, foodItemDto2);
    assertNotEquals(foodItemDto1.hashCode(), foodItemDto2.hashCode());

    foodItemDto1 = new FoodItemNameOutDto();
    foodItemDto2 = new FoodItemNameOutDto();
    assertEquals(foodItemDto1, foodItemDto2);
    assertEquals(foodItemDto1.hashCode(), foodItemDto2.hashCode());
  }

  private FoodItemNameOutDto buildFoodItemNameOutDto(Integer id, String foodItemName, Double price) {
    FoodItemNameOutDto foodItemDto = new FoodItemNameOutDto();
    foodItemDto.setId(id);
    foodItemDto.setFoodItemName(foodItemName);
    foodItemDto.setPrice(price);
    return foodItemDto;
  }
}
