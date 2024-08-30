package com.restaurants.indto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FoodItemInDtoTest {

  private FoodItemInDto foodItemInDto;

  @BeforeEach
  public void setUp() {
    foodItemInDto = new FoodItemInDto();
    // Set up initial values
    foodItemInDto.setFoodName("Pizza");
    foodItemInDto.setRestaurantId(1);
    foodItemInDto.setDescription("Delicious");
    foodItemInDto.setCategoryId(10);
    foodItemInDto.setIsAvailable(true);
    foodItemInDto.setPrice(12.99);
  }

  @Test
  public void testGetFoodName() {
    assertEquals("Pizza", foodItemInDto.getFoodName());
  }

  @Test
  public void testSetFoodName() {
    foodItemInDto.setFoodName("Burger");
    assertEquals("Burger", foodItemInDto.getFoodName());
  }

  @Test
  public void testGetRestaurantId() {
    assertEquals(1, foodItemInDto.getRestaurantId());
  }

  @Test
  public void testSetRestaurantId() {
    foodItemInDto.setRestaurantId(2);
    assertEquals(2, foodItemInDto.getRestaurantId());
  }

  @Test
  public void testGetDescription() {
    assertEquals("Delicious", foodItemInDto.getDescription());
  }

  @Test
  public void testSetDescription() {
    foodItemInDto.setDescription("Tasty");
    assertEquals("Tasty", foodItemInDto.getDescription());
  }

  @Test
  public void testGetCategoryId() {
    assertEquals(10, foodItemInDto.getCategoryId());
  }

  @Test
  public void testSetCategoryId() {
    foodItemInDto.setCategoryId(20);
    assertEquals(20, foodItemInDto.getCategoryId());
  }

  @Test
  public void testIsAvailable() {
    assertEquals(true, foodItemInDto.getIsAvailable());
  }

  @Test
  public void testSetIsAvailable() {
    foodItemInDto.setIsAvailable(false);
    assertEquals(false, foodItemInDto.getIsAvailable());
  }

  @Test
  public void testGetPrice() {
    assertEquals(12.99, foodItemInDto.getPrice());
  }

  @Test
  public void testSetPrice() {
    foodItemInDto.setPrice(9.99);
    assertEquals(9.99, foodItemInDto.getPrice());
  }




  @Test
  public void testHashCode() {
    FoodItemInDto foodItemInDto1 = new FoodItemInDto();
    foodItemInDto1.setFoodName("Pizza");
    foodItemInDto1.setRestaurantId(1);
    foodItemInDto1.setDescription("Delicious");
    foodItemInDto1.setCategoryId(10);
    foodItemInDto1.setIsAvailable(true);
    foodItemInDto1.setPrice(12.99);

    FoodItemInDto foodItemInDto2 = new FoodItemInDto();
    foodItemInDto2.setFoodName("Pizza");
    foodItemInDto2.setRestaurantId(1);
    foodItemInDto2.setDescription("Delicious");
    foodItemInDto2.setCategoryId(10);
    foodItemInDto2.setIsAvailable(true);
    foodItemInDto2.setPrice(12.99);

    assertEquals(foodItemInDto1.hashCode(), foodItemInDto2.hashCode());

    foodItemInDto2.setFoodName("Burger");
    assertNotEquals(foodItemInDto1.hashCode(), foodItemInDto2.hashCode());
  }

  @Test
  public void testEquals() {
    FoodItemInDto foodItemInDto1 = new FoodItemInDto();
    foodItemInDto1.setFoodName("Pizza");
    foodItemInDto1.setRestaurantId(1);
    foodItemInDto1.setDescription("Delicious");
    foodItemInDto1.setCategoryId(10);
    foodItemInDto1.setIsAvailable(true);
    foodItemInDto1.setPrice(12.99);

    FoodItemInDto foodItemInDto2 = new FoodItemInDto();
    foodItemInDto2.setFoodName("Pizza");
    foodItemInDto2.setRestaurantId(1);
    foodItemInDto2.setDescription("Delicious");
    foodItemInDto2.setCategoryId(10);
    foodItemInDto2.setIsAvailable(true);
    foodItemInDto2.setPrice(12.99);

    assertEquals(foodItemInDto1, foodItemInDto2);

    foodItemInDto2.setFoodName("Burger");
    assertNotEquals(foodItemInDto1, foodItemInDto2);

    assertEquals(foodItemInDto1, foodItemInDto1);

    assertNotEquals(foodItemInDto1, null);

    assertNotEquals(foodItemInDto1, new Object());
  }
}
