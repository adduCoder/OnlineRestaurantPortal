package com.orders.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FoodItemNameOutDtoTest {

  @Test
  public void testIdGetterAndSetter() {
    FoodItemNameOutDto foodItemNameOutDto = new FoodItemNameOutDto();
    Integer expectedId = 1;
    foodItemNameOutDto.setId(expectedId);
    Integer actualId = foodItemNameOutDto.getId();
    assertEquals(expectedId, actualId, "The id should be equal to the expected value.");
  }

  @Test
  public void testFoodItemNameGetterAndSetter() {
    FoodItemNameOutDto foodItemNameOutDto = new FoodItemNameOutDto();
    String expectedFoodItemName = "Burger";
    foodItemNameOutDto.setFoodItemName(expectedFoodItemName);
    String actualFoodItemName = foodItemNameOutDto.getFoodItemName();
    assertEquals(expectedFoodItemName, actualFoodItemName, "The foodItemName should be equal to the expected value.");
  }

  @Test
  public void testPriceGetterAndSetter() {
    FoodItemNameOutDto foodItemNameOutDto = new FoodItemNameOutDto();
    Double expectedPrice = 19.99;
    foodItemNameOutDto.setPrice(expectedPrice);
    Double actualPrice = foodItemNameOutDto.getPrice();
    assertEquals(expectedPrice, actualPrice, "The price should be equal to the expected value.");
  }

  @Test
  public void testEquals() {
    FoodItemNameOutDto foodItemNameOutDto1 = new FoodItemNameOutDto();
    foodItemNameOutDto1.setId(1);
    foodItemNameOutDto1.setFoodItemName("Burger");
    foodItemNameOutDto1.setPrice(19.99);

    FoodItemNameOutDto foodItemNameOutDto2 = new FoodItemNameOutDto();
    foodItemNameOutDto2.setId(1);
    foodItemNameOutDto2.setFoodItemName("Burger");
    foodItemNameOutDto2.setPrice(19.99);

    assertEquals(foodItemNameOutDto1, foodItemNameOutDto2, "The FoodItemNameOutDto objects should be equal.");

    foodItemNameOutDto2.setPrice(20.99);
    assertNotEquals(foodItemNameOutDto1, foodItemNameOutDto2, "The FoodItemNameOutDto objects should not be equal.");
  }

  @Test
  public void testHashCode() {
    FoodItemNameOutDto foodItemNameOutDto1 = new FoodItemNameOutDto();
    foodItemNameOutDto1.setId(1);
    foodItemNameOutDto1.setFoodItemName("Burger");
    foodItemNameOutDto1.setPrice(19.99);

    FoodItemNameOutDto foodItemNameOutDto2 = new FoodItemNameOutDto();
    foodItemNameOutDto2.setId(1);
    foodItemNameOutDto2.setFoodItemName("Burger");
    foodItemNameOutDto2.setPrice(19.99);

    assertEquals(foodItemNameOutDto1.hashCode(), foodItemNameOutDto2.hashCode(), "The hashCodes should be equal.");

    foodItemNameOutDto2.setPrice(20.99);
    assertNotEquals(foodItemNameOutDto1.hashCode(), foodItemNameOutDto2.hashCode(), "The hashCodes should not be equal.");
  }

  @Test
  public void testToString() {
    FoodItemNameOutDto foodItemNameOutDto = new FoodItemNameOutDto();
    foodItemNameOutDto.setId(1);
    foodItemNameOutDto.setFoodItemName("Burger");
    foodItemNameOutDto.setPrice(19.99);

    String expectedToString = "FoodItemNameOutDto(id=1, foodItemName=Burger, price=19.99)"; // Adjust this if the format is different
    assertEquals(expectedToString, foodItemNameOutDto.toString(), "The toString method should return the expected string.");
  }

}
