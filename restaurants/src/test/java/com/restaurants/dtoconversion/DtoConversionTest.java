package com.restaurants.dtoconversion;

import com.restaurants.dto.CategoryInDto;
import com.restaurants.dto.CategoryOutDto;
import com.restaurants.dto.FoodItemInDto;
import com.restaurants.dto.FoodItemOutDto;
import com.restaurants.dto.RestaurantInDto;
import com.restaurants.dto.RestaurantOutDto;
import com.restaurants.entities.Category;
import com.restaurants.entities.FoodItem;
import com.restaurants.entities.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    restaurantInDto.setUserId(1); // Placeholder User ID
    restaurantInDto.setRestaurantName("Test Restaurant");
    restaurantInDto.setAddress("123 Test St, Test City, TC");
    restaurantInDto.setContactNumber("0000000000");
    restaurantInDto.setDescription("Test Description");

    // Initialize Restaurant Entity
    restaurant = new Restaurant();
    restaurant.setId(1); // Placeholder Restaurant ID
    restaurant.setUserId(1); // Placeholder User ID
    restaurant.setRestaurantName("test restaurant");
    restaurant.setAddress("123 Test St, Test City, TC");
    restaurant.setContactNumber("0000000000");
    restaurant.setDescription("test description");

    // Initialize CategoryInDto
    categoryInDto = new CategoryInDto();
    categoryInDto.setName("Test Category");
    categoryInDto.setRestaurantId(1); // Placeholder Restaurant ID

    // Initialize Category Entity
    category = new Category();
    category.setId(1); // Placeholder Category ID
    category.setName("test category");
    category.setRestaurantId(1); // Placeholder Restaurant ID

    // Initialize FoodItemInDto
    foodItemInDto = new FoodItemInDto();
    foodItemInDto.setFoodName("Test Food Item");
    foodItemInDto.setRestaurantId(1); // Placeholder Restaurant ID
    foodItemInDto.setDescription("Test Description");
    foodItemInDto.setCategoryId(1); // Placeholder Category ID
    foodItemInDto.setIsAvailable(true);
    foodItemInDto.setPrice(9.99);

    // Initialize FoodItem Entity
    foodItem = new FoodItem();
    foodItem.setId(1); // Placeholder Food Item ID
    foodItem.setFoodName("test food item");
    foodItem.setRestaurantId(1); // Placeholder Restaurant ID
    foodItem.setDescription("Test Description");
    foodItem.setCategoryId(1); // Placeholder Category ID
    foodItem.setIsAvailable(true);
    foodItem.setPrice(9.99);
  }

  @Test
  void testMapToRestaurant() {
    Restaurant result = DtoConversion.mapToRestaurant(restaurantInDto);
    assertNotNull(result);
    assertEquals(restaurantInDto.getUserId(), result.getUserId());
    assertEquals(restaurantInDto.getRestaurantName().toLowerCase(), result.getRestaurantName());
    assertEquals(restaurantInDto.getAddress(), result.getAddress());
    assertEquals(restaurantInDto.getContactNumber(), result.getContactNumber());
    assertEquals(restaurantInDto.getDescription().toLowerCase(), result.getDescription());
  }

  @Test
  void testMapToRestaurantOutDto() {
    RestaurantOutDto result = DtoConversion.mapToRestaurantOutDto(restaurant);
    assertNotNull(result);
    assertEquals(restaurant.getId(), result.getId());
    assertEquals(restaurant.getUserId(), result.getUserId());
    assertEquals(restaurant.getRestaurantName(), result.getRestaurantName());
    assertEquals(restaurant.getDescription(), result.getDescription());
    assertEquals(restaurant.getAddress(), result.getAddress());
    assertEquals(restaurant.getContactNumber(), result.getContactNumber());
  }

  @Test
  void testMapToCategory() {
    Category result = DtoConversion.mapToCategory(categoryInDto);
    assertNotNull(result);
    assertEquals(categoryInDto.getName().toLowerCase(), result.getName());
    assertEquals(categoryInDto.getRestaurantId(), result.getRestaurantId());
  }

  @Test
  void testMapToCategoryOutDto() {
    CategoryOutDto result = DtoConversion.mapToCategoryOutDto(category);
    assertNotNull(result);
    assertEquals(category.getId(), result.getId());
    assertEquals(category.getName(), result.getName());
    assertEquals(category.getRestaurantId(), result.getId());
  }

  @Test
  void testMapToFoodItem() {
    FoodItem result = DtoConversion.mapToFoodItem(foodItemInDto);
    assertNotNull(result);
    assertEquals(foodItemInDto.getFoodName().toLowerCase(), result.getFoodName());
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
