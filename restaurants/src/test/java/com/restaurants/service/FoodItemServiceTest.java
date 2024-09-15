package com.restaurants.service;

import com.restaurants.dto.FoodItemInDto;
import com.restaurants.dto.FoodItemNameOutDto;
import com.restaurants.dto.FoodItemOutDto;
import com.restaurants.entities.Category;
import com.restaurants.entities.FoodItem;
import com.restaurants.entities.Restaurant;
import com.restaurants.exceptionhandler.NotFound;
import com.restaurants.repository.CategoryRepo;
import com.restaurants.repository.FoodItemRepo;
import com.restaurants.repository.RestaurantRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

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

  @Mock
  private MultipartFile multipartFile;

  private FoodItemInDto foodItemInDto;
  private FoodItem foodItem;
  private Restaurant restaurant;
  private Category category;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    // Initialize shared objects
    foodItemInDto = new FoodItemInDto();
    foodItemInDto.setFoodName("Pizza");
    foodItemInDto.setRestaurantId(1);
    foodItemInDto.setDescription("Delicious Pizza");
    foodItemInDto.setCategoryId(1);
    foodItemInDto.setIsAvailable(true);
    foodItemInDto.setPrice(9.99);

    foodItem = new FoodItem();
    foodItem.setId(1);
    foodItem.setFoodName("Pizza");
    foodItem.setRestaurantId(1);
    foodItem.setDescription("Delicious Pizza");
    foodItem.setCategoryId(1);
    foodItem.setIsAvailable(true);
    foodItem.setPrice(9.99);

    restaurant = new Restaurant();
    restaurant.setId(1);
    restaurant.setRestaurantName("Test Restaurant");

    category = new Category();
    category.setId(1);
    category.setName("Italian");
    category.setRestaurantId(1);
  }

  @Test
  void testAddFoodItem() throws IOException {
    // Arrange
    when(categoryRepo.findById(1)).thenReturn(Optional.of(category));
    when(restaurantRepo.findById(1)).thenReturn(Optional.of(restaurant));
    when(foodItemRepo.findAllByRestaurantId(1)).thenReturn(new ArrayList<>());
    when(foodItemRepo.save(any(FoodItem.class))).thenReturn(foodItem);
    when(multipartFile.getContentType()).thenReturn("image/jpeg");
    when(multipartFile.getBytes()).thenReturn(new byte[] {1, 2, 3});

    // Act
    FoodItemOutDto result = foodItemService.add(foodItemInDto, multipartFile);

    // Assert
    assertEquals("pizza", result.getFoodName());
    assertEquals("test restaurant", result.getRestaurantName());
    assertEquals("Italian", result.getCategoryName());
    assertEquals(9.99, result.getPrice(), 0.01);
  }

  @Test
  void testAddFoodItemWithInvalidImage() throws IOException {
    // Arrange
    when(categoryRepo.findById(1)).thenReturn(Optional.of(category));
    when(restaurantRepo.findById(1)).thenReturn(Optional.of(restaurant));
    when(foodItemRepo.findAllByRestaurantId(1)).thenReturn(new ArrayList<>());
    when(foodItemRepo.save(any(FoodItem.class))).thenReturn(foodItem);
    when(multipartFile.getContentType()).thenReturn("text/plain");

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> foodItemService.add(foodItemInDto, multipartFile));
  }

  @Test
  void testGetAllFoodItems() {
    // Arrange
    List<FoodItem> foodItemList = new ArrayList<>();
    foodItemList.add(foodItem);

    when(foodItemRepo.findAllByRestaurantId(1)).thenReturn(foodItemList);
    when(restaurantRepo.findById(1)).thenReturn(Optional.of(restaurant));
    when(categoryRepo.findById(1)).thenReturn(Optional.of(category));

    // Act
    List<FoodItemOutDto> result = foodItemService.getAll(1);

    // Assert
    assertEquals(1, result.size());
    assertEquals("pizza", result.get(0).getFoodName());
    assertEquals("test restaurant", result.get(0).getRestaurantName());
    assertEquals("Italian", result.get(0).getCategoryName());
    assertEquals(9.99, result.get(0).getPrice());
  }

  @Test
  void testUpdateFoodItem() throws IOException {
    // Arrange
    foodItemInDto.setPrice(10.99);
    when(foodItemRepo.findById(1)).thenReturn(Optional.of(foodItem));
    when(foodItemRepo.save(any(FoodItem.class))).thenReturn(foodItem);
    when(restaurantRepo.findById(1)).thenReturn(Optional.of(restaurant));
    when(categoryRepo.findById(1)).thenReturn(Optional.of(category));
    when(multipartFile.getContentType()).thenReturn("image/jpeg");
    when(multipartFile.getBytes()).thenReturn(new byte[] {1, 2, 3});

    // Act
    FoodItemOutDto result = foodItemService.updateFoodItem(1, foodItemInDto, multipartFile);

    // Assert
    assertEquals("pizza", result.getFoodName());
    assertEquals("test restaurant", result.getRestaurantName());
    assertEquals("Italian", result.getCategoryName());
    assertEquals(10.99, result.getPrice(), 0.01);
  }

  @Test
  void testUpdateFoodItemNotFound() {
    // Arrange
    when(foodItemRepo.findById(1)).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(NotFound.class, () -> foodItemService.updateFoodItem(1, foodItemInDto, null));
  }

  @Test
  void testGetFoodItemName() {
    // Arrange
    when(foodItemRepo.findById(1)).thenReturn(Optional.of(foodItem));

    // Act
    FoodItemNameOutDto result = foodItemService.getFoodItemName(1);

    // Assert
    assertEquals("Pizza", result.getFoodItemName());
    assertEquals(1, result.getId());
    assertEquals(9.99, result.getPrice(), 0.01);
  }

  @Test
  void testGetFoodItemNameNotFound() {
    // Arrange
    when(foodItemRepo.findById(1)).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(NotFound.class, () -> foodItemService.getFoodItemName(1));
  }
}
