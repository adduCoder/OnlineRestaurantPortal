package com.restaurants.indto;

import com.restaurants.dto.FoodItemInDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FoodItemInDtoTest {

  private FoodItemInDto foodItemInDto;

  @BeforeEach
  public void setUp() {
    foodItemInDto = new FoodItemInDto();
    // Set up initial values with placeholders
    foodItemInDto.setFoodName("Test Food");
    foodItemInDto.setRestaurantId(1); // Placeholder Restaurant ID
    foodItemInDto.setDescription("Test Description");
    foodItemInDto.setCategoryId(10); // Placeholder Category ID
    foodItemInDto.setIsAvailable(true);
    foodItemInDto.setPrice(9.99); // Placeholder Price
  }

  @Test
  public void testGetFoodName() {
    assertEquals("Test Food", foodItemInDto.getFoodName());
  }

  @Test
  public void testSetFoodName() {
    foodItemInDto.setFoodName("Updated Food");
    assertEquals("Updated Food", foodItemInDto.getFoodName());
  }

  @Test
  public void testGetRestaurantId() {
    assertEquals(1, foodItemInDto.getRestaurantId());
  }

  @Test
  public void testSetRestaurantId() {
    foodItemInDto.setRestaurantId(2); // Placeholder for testing
    assertEquals(2, foodItemInDto.getRestaurantId());
  }

  @Test
  public void testGetDescription() {
    assertEquals("Test Description", foodItemInDto.getDescription());
  }

  @Test
  public void testSetDescription() {
    foodItemInDto.setDescription("Updated Description");
    assertEquals("Updated Description", foodItemInDto.getDescription());
  }

  @Test
  public void testGetCategoryId() {
    assertEquals(10, foodItemInDto.getCategoryId());
  }

  @Test
  public void testSetCategoryId() {
    foodItemInDto.setCategoryId(20); // Placeholder for testing
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
    assertEquals(9.99, foodItemInDto.getPrice());
  }

  @Test
  public void testSetPrice() {
    foodItemInDto.setPrice(19.99); // Placeholder for testing
    assertEquals(19.99, foodItemInDto.getPrice());
  }

  @Test
  public void testHashCode() {
    FoodItemInDto foodItemInDto1 = new FoodItemInDto();
    foodItemInDto1.setFoodName("Test Food");
    foodItemInDto1.setRestaurantId(1);
    foodItemInDto1.setDescription("Test Description");
    foodItemInDto1.setCategoryId(10);
    foodItemInDto1.setIsAvailable(true);
    foodItemInDto1.setPrice(9.99);

    FoodItemInDto foodItemInDto2 = new FoodItemInDto();
    foodItemInDto2.setFoodName("Test Food");
    foodItemInDto2.setRestaurantId(1);
    foodItemInDto2.setDescription("Test Description");
    foodItemInDto2.setCategoryId(10);
    foodItemInDto2.setIsAvailable(true);
    foodItemInDto2.setPrice(9.99);

    assertEquals(foodItemInDto1.hashCode(), foodItemInDto2.hashCode());

    foodItemInDto2.setFoodName("Updated Food");
    assertNotEquals(foodItemInDto1.hashCode(), foodItemInDto2.hashCode());
  }

  @Test
  public void testEquals() {
    FoodItemInDto foodItemInDto1 = new FoodItemInDto();
    foodItemInDto1.setFoodName("Test Food");
    foodItemInDto1.setRestaurantId(1);
    foodItemInDto1.setDescription("Test Description");
    foodItemInDto1.setCategoryId(10);
    foodItemInDto1.setIsAvailable(true);
    foodItemInDto1.setPrice(9.99);

    FoodItemInDto foodItemInDto2 = new FoodItemInDto();
    foodItemInDto2.setFoodName("Test Food");
    foodItemInDto2.setRestaurantId(1);
    foodItemInDto2.setDescription("Test Description");
    foodItemInDto2.setCategoryId(10);
    foodItemInDto2.setIsAvailable(true);
    foodItemInDto2.setPrice(9.99);

    assertEquals(foodItemInDto1, foodItemInDto2);

    foodItemInDto2.setFoodName("Updated Food");
    assertNotEquals(foodItemInDto1, foodItemInDto2);

    assertEquals(foodItemInDto1, foodItemInDto1);

    assertNotEquals(foodItemInDto1, null);

    assertNotEquals(foodItemInDto1, new Object());
  }
}
