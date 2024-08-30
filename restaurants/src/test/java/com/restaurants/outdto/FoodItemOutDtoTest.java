package com.restaurants.outdto;

import com.restaurants.indto.FoodItemInDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodItemOutDtoTest {

  private FoodItemInDto foodItemInDto;
  private FoodItemOutDto foodItemOutDto;

  @BeforeEach
  public void setUp() {
    // Initialize the FoodItemInDto object
    foodItemInDto = new FoodItemInDto();
    foodItemInDto.setFoodName("Pasta");
    foodItemInDto.setRestaurantId(1);
    foodItemInDto.setDescription("Delicious Italian pasta with creamy sauce");
    foodItemInDto.setCategoryId(2);
    foodItemInDto.setIsAvailable(true);
    foodItemInDto.setPrice(12.99);

    // Initialize the FoodItemOutDto object
    foodItemOutDto = new FoodItemOutDto();
    foodItemOutDto.setId(1);
    foodItemOutDto.setFoodName("Pasta");
    foodItemOutDto.setRestaurantName("Italian Bistro");
    foodItemOutDto.setDescription("Delicious Italian pasta with creamy sauce");
    foodItemOutDto.setCategoryName("Main Course");
    foodItemOutDto.setIsAvailable(true);
    foodItemOutDto.setPrice(12.99);
  }

  @Test
  public void testFoodItemInDtoFields() {
    // Test the fields of FoodItemInDto
    assertEquals("Pasta", foodItemInDto.getFoodName());
    assertEquals(1, foodItemInDto.getRestaurantId());
    assertEquals("Delicious Italian pasta with creamy sauce", foodItemInDto.getDescription());
    assertEquals(2, foodItemInDto.getCategoryId());
    assertTrue(foodItemInDto.getIsAvailable());
    assertEquals(12.99, foodItemInDto.getPrice());
  }

  @Test
  public void testFoodItemOutDtoFields() {
    // Test the fields of FoodItemOutDto
    assertEquals(1, foodItemOutDto.getId());
    assertEquals("Pasta", foodItemOutDto.getFoodName());
    assertEquals("Italian Bistro", foodItemOutDto.getRestaurantName());
    assertEquals("Delicious Italian pasta with creamy sauce", foodItemOutDto.getDescription());
    assertEquals("Main Course", foodItemOutDto.getCategoryName());
    assertTrue(foodItemOutDto.getIsAvailable());
    assertEquals(12.99, foodItemOutDto.getPrice());
  }

  @Test
  public void testFoodItemInDtoMutations() {
    // Test setting new values for FoodItemInDto
    foodItemInDto.setFoodName("Pizza");
    assertEquals("Pizza", foodItemInDto.getFoodName());

    foodItemInDto.setPrice(15.99);
    assertEquals(15.99, foodItemInDto.getPrice());
  }

  @Test
  public void testFoodItemOutDtoMutations() {
    // Test setting new values for FoodItemOutDto
    foodItemOutDto.setFoodName("Pizza");
    assertEquals("Pizza", foodItemOutDto.getFoodName());

    foodItemOutDto.setRestaurantName("Pizza Palace");
    assertEquals("Pizza Palace", foodItemOutDto.getRestaurantName());
  }
}
