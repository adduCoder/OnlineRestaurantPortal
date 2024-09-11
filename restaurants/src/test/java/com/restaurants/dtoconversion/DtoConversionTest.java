package com.restaurants.dtoconversion;

import com.restaurants.entities.Category;
import com.restaurants.entities.FoodItem;
import com.restaurants.entities.Restaurant;
import com.restaurants.dto.CategoryInDto;
import com.restaurants.dto.FoodItemInDto;
import com.restaurants.dto.RestaurantInDto;
import com.restaurants.dto.CategoryOutDto;
import com.restaurants.dto.FoodItemOutDto;
import com.restaurants.dto.RestaurantOutDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DtoConversionTest {

  private RestaurantInDto restaurantInDto;
  private Restaurant restaurant;
  private CategoryInDto categoryInDto;
  private Category category;
  private FoodItemInDto foodItemInDto;
  private FoodItem foodItem;

  @BeforeEach
  void setUp() {
    // Initialize RestaurantInDto
    restaurantInDto = new RestaurantInDto();
    restaurantInDto.setUserId(1);
    restaurantInDto.setRestaurantName("Spice Hub");
    restaurantInDto.setAddress("123 Main St");
    restaurantInDto.setContactNumber("7896541230");
    restaurantInDto.setDescription("Best Indian Cuisine");
    //restaurantInDto.setImageUrl("http://image.url/spicehub.jpg");

    // Initialize Restaurant Entity
    restaurant = new Restaurant();
    restaurant.setId(1);
    restaurant.setUserId(1);
    restaurant.setRestaurantName("Spice Hub");
    restaurant.setAddress("123 Main St");
    restaurant.setContactNumber("7896541230");
    restaurant.setDescription("Best Indian Cuisine");
    //restaurant.setImageUrl("http://image.url/spicehub.jpg");

    // Initialize CategoryInDto
    categoryInDto = new CategoryInDto();
    categoryInDto.setName("Desserts");
    categoryInDto.setRestaurantId(1);

    // Initialize Category Entity
    category = new Category();
    category.setId(1);
    category.setName("Desserts");
    category.setRestaurantId(1);

    // Initialize FoodItemInDto
    foodItemInDto = new FoodItemInDto();
    foodItemInDto.setFoodName("Gulab Jamun");
    foodItemInDto.setRestaurantId(1);
    foodItemInDto.setDescription("Sweet Indian dessert");
    foodItemInDto.setCategoryId(1);
    foodItemInDto.setIsAvailable(true);
    foodItemInDto.setPrice(2.99);

    // Initialize FoodItem Entity
    foodItem = new FoodItem();
    foodItem.setId(1);
    foodItem.setFoodName("Gulab Jamun");
    foodItem.setRestaurantId(1);
    foodItem.setDescription("Sweet Indian dessert");
    foodItem.setCategoryId(1);
    foodItem.setIsAvailable(true);
    foodItem.setPrice(2.99);
  }

  @Test
  void testMapToRestaurant() {
    Restaurant result = DtoConversion.mapToRestaurant(restaurantInDto);
    assertNotNull(result);
    assertEquals(restaurantInDto.getUserId(), result.getUserId());
    assertEquals(restaurantInDto.getRestaurantName(), result.getRestaurantName());
    assertEquals(restaurantInDto.getAddress(), result.getAddress());
    assertEquals(restaurantInDto.getContactNumber(), result.getContactNumber());
    //assertEquals(restaurantInDto.getDescription(), result.getDescription());
    //assertEquals(restaurantInDto.getImageUrl(), result.getImageUrl());
  }

  @Test
  void testMapToRestaurantOutDto() {
    RestaurantOutDto result = DtoConversion.mapToRestaurantOutDto(restaurant);
    assertNotNull(result);
    assertEquals(restaurant.getId(), result.getId());
    assertEquals(restaurant.getUserId(), result.getUserId());
    assertEquals(restaurant.getRestaurantName(), result.getRestaurantName());
    assertEquals(restaurant.getDescription(), result.getDescription());
    //assertEquals(restaurant.getImageUrl(), result.getImageUrl());
    assertEquals(restaurant.getAddress(), result.getAddress());
    assertEquals(restaurant.getContactNumber(), result.getContactNumber());
  }

  @Test
  void testMapToCategory() {
    Category result = DtoConversion.mapToCategory(categoryInDto);
    assertNotNull(result);
    assertEquals(categoryInDto.getName(), result.getName());
    assertEquals(categoryInDto.getRestaurantId(), result.getRestaurantId());
  }

  @Test
  void testMapToCategoryOutDto() {
    CategoryOutDto result = DtoConversion.mapToCategoryOutDto(category);
    assertNotNull(result);
    assertEquals(category.getId(), result.getId());
    assertEquals(category.getName(), result.getName());
    assertEquals(category.getRestaurantId(), result.getResturantId());
  }

  @Test
  void testMapToFoodItem() {
    FoodItem result = DtoConversion.mapToFoodItem(foodItemInDto);
    assertNotNull(result);
    assertEquals(foodItemInDto.getFoodName(), result.getFoodName());
    assertEquals(foodItemInDto.getPrice(), result.getPrice());
    assertEquals(foodItemInDto.getCategoryId(), result.getCategoryId());
    assertEquals(foodItemInDto.getRestaurantId(), result.getRestaurantId());
    assertEquals(foodItemInDto.getDescription(), result.getDescription());
    assertEquals(foodItemInDto.getIsAvailable(), result.getIsAvailable());
  }

  @Test
  void testMapToFoodItemOutDto() {
    FoodItemOutDto result = DtoConversion.mapToFoodItemOutDto(foodItem, restaurant.getRestaurantName(), category.getName());
    assertNotNull(result);
    assertEquals(foodItem.getId(), result.getId());
    assertEquals(foodItem.getFoodName(), result.getFoodName());
    assertEquals(foodItem.getPrice(), result.getPrice());
    assertEquals(foodItem.getIsAvailable(), result.getIsAvailable());
    assertEquals(foodItem.getDescription(), result.getDescription());
    assertEquals(restaurant.getRestaurantName(), result.getRestaurantName());
    assertEquals(category.getName(), result.getCategoryName());
  }
}
