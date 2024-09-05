package com.restaurants.service;

import com.restaurants.entities.Category;
import com.restaurants.entities.FoodItem;
import com.restaurants.entities.Restaurant;
import com.restaurants.exceptionhandler.FoodItemNotFound;
import com.restaurants.dto.indto.FoodItemInDto;
import com.restaurants.dto.outdto.FoodItemOutDto;
import com.restaurants.repository.CategoryRepo;
import com.restaurants.repository.FoodItemRepo;
import com.restaurants.repository.RestaurantRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FoodItemServiceTest {

  @InjectMocks
  private FoodItemService foodItemService;

  @Mock
  private FoodItemRepo foodItemRepo;

  @Mock
  private RestaurantRepo restaurantRepo;

  @Mock
  private CategoryRepo categoryRepo;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testAddFoodItem() {
    // Arrange
    FoodItemInDto foodItemInDto = new FoodItemInDto();
    foodItemInDto.setFoodName("Pizza");
    foodItemInDto.setRestaurantId(1);
    foodItemInDto.setDescription("Delicious Pizza");
    foodItemInDto.setCategoryId(1);
    foodItemInDto.setIsAvailable(true);
    foodItemInDto.setPrice(9.99);

    FoodItem foodItem = new FoodItem();
    foodItem.setId(1);
    foodItem.setFoodName("Pizza");
    foodItem.setRestaurantId(1);
    foodItem.setDescription("Delicious Pizza");
    foodItem.setCategoryId(1);
    foodItem.setIsAvailable(true);
    foodItem.setPrice(9.99);

    Restaurant restaurant = new Restaurant();
    restaurant.setRestaurantName("Test Restaurant");

    Category category = new Category();
    category.setName("Italian");

    when(foodItemRepo.save(any(FoodItem.class))).thenReturn(foodItem);
    when(restaurantRepo.findById(1)).thenReturn(Optional.of(restaurant));
    when(categoryRepo.findById(1)).thenReturn(Optional.of(category));

    // Act

  }

  @Test
  void testGetAllFoodItems() {
    // Arrange
    FoodItem foodItem = new FoodItem();
    foodItem.setId(1);
    foodItem.setFoodName("Pizza");
    foodItem.setRestaurantId(1);
    foodItem.setDescription("Delicious Pizza");
    foodItem.setCategoryId(1);
    foodItem.setIsAvailable(true);
    foodItem.setPrice(9.99);

    Restaurant restaurant = new Restaurant();
    restaurant.setRestaurantName("Test Restaurant");

    Category category = new Category();
    category.setName("Italian");

    List<FoodItem> foodItemList = new ArrayList<>();
    foodItemList.add(foodItem);

    when(foodItemRepo.findAllByRestaurantId(1)).thenReturn(foodItemList);
    when(restaurantRepo.findById(1)).thenReturn(Optional.of(restaurant));
    when(categoryRepo.findById(1)).thenReturn(Optional.of(category));

    // Act
    List<FoodItemOutDto> result = foodItemService.getAll(1);

    // Assert
    assertEquals(1, result.size());
    assertEquals("Pizza", result.get(0).getFoodName());
    assertEquals("Test Restaurant", result.get(0).getRestaurantName());
    assertEquals("Italian", result.get(0).getCategoryName());
    assertEquals(9.99, result.get(0).getPrice());
  }

  @Test
  void testUpdateFoodItem() {
    // Arrange
    FoodItemInDto foodItemInDto = new FoodItemInDto();
    foodItemInDto.setFoodName("Pizza");
    foodItemInDto.setRestaurantId(1);
    foodItemInDto.setDescription("Delicious Pizza");
    foodItemInDto.setCategoryId(1);
    foodItemInDto.setIsAvailable(true);
    foodItemInDto.setPrice(10.99);

    FoodItem foodItem = new FoodItem();
    foodItem.setId(1);
    foodItem.setFoodName("Pizza");
    foodItem.setRestaurantId(1);
    foodItem.setDescription("Delicious Pizza");
    foodItem.setCategoryId(1);
    foodItem.setIsAvailable(true);
    foodItem.setPrice(9.99);

    Restaurant restaurant = new Restaurant();
    restaurant.setRestaurantName("Test Restaurant");

    Category category = new Category();
    category.setName("Italian");

    when(foodItemRepo.findById(1)).thenReturn(Optional.of(foodItem));
    when(foodItemRepo.save(any(FoodItem.class))).thenReturn(foodItem);
    when(restaurantRepo.findById(1)).thenReturn(Optional.of(restaurant));
    when(categoryRepo.findById(1)).thenReturn(Optional.of(category));

    // Act
    FoodItemOutDto result = foodItemService.updateFoodItem(1, foodItemInDto);

    // Assert
    assertEquals("Pizza", result.getFoodName());
    assertEquals("Test Restaurant", result.getRestaurantName());
    assertEquals("Italian", result.getCategoryName());
    assertEquals(10.99, result.getPrice());
  }

  @Test
  void testUpdateFoodItemNotFound() {
    // Arrange
    FoodItemInDto foodItemInDto = new FoodItemInDto();
    foodItemInDto.setFoodName("Pizza");

    when(foodItemRepo.findById(1)).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(FoodItemNotFound.class, () -> foodItemService.updateFoodItem(1, foodItemInDto));
  }
}
