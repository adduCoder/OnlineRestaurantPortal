package com.restaurants.service;

import com.restaurants.dto.FoodItemInDto;
import com.restaurants.dto.FoodItemNameOutDto;
import com.restaurants.dto.FoodItemOutDto;
import com.restaurants.entities.Category;
import com.restaurants.entities.FoodItem;
import com.restaurants.entities.Restaurant;
import com.restaurants.exception.NotFoundException;
import com.restaurants.repository.CategoryRepository;
import com.restaurants.repository.FoodItemRepository;
import com.restaurants.repository.RestaurantRepository;
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
  private FoodItemRepository foodItemRepository;

  @Mock
  private RestaurantRepository restaurantRepository;

  @Mock
  private CategoryRepository categoryRepository;

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
    when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
    when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
    when(foodItemRepository.findAllByRestaurantId(1)).thenReturn(new ArrayList<>());
    when(foodItemRepository.save(any(FoodItem.class))).thenReturn(foodItem);
    when(multipartFile.getContentType()).thenReturn("image/jpeg");
    when(multipartFile.getBytes()).thenReturn(new byte[] {1, 2, 3});

    FoodItemOutDto result = foodItemService.add(foodItemInDto, multipartFile);

    assertEquals("pizza", result.getFoodName());
    assertEquals("test restaurant", result.getRestaurantName());
    assertEquals("Italian", result.getCategoryName());
    assertEquals(9.99, result.getPrice(), 0.01);
  }

  @Test
  void testAddFoodItemWithInvalidImage() throws IOException {
    when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
    when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
    when(foodItemRepository.findAllByRestaurantId(1)).thenReturn(new ArrayList<>());
    when(foodItemRepository.save(any(FoodItem.class))).thenReturn(foodItem);
    when(multipartFile.getContentType()).thenReturn("text/plain");

    assertThrows(IllegalArgumentException.class, () -> foodItemService.add(foodItemInDto, multipartFile));
  }

  @Test
  void testGetAllFoodItems() {
    List<FoodItem> foodItemList = new ArrayList<>();
    foodItemList.add(foodItem);

    when(foodItemRepository.findAllByRestaurantId(1)).thenReturn(foodItemList);
    when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
    when(categoryRepository.findById(1)).thenReturn(Optional.of(category));


    List<FoodItemOutDto> result = foodItemService.getAll(1);


    assertEquals(1, result.size());
    assertEquals("pizza", result.get(0).getFoodName());
    assertEquals("test restaurant", result.get(0).getRestaurantName());
    assertEquals("Italian", result.get(0).getCategoryName());
    assertEquals(9.99, result.get(0).getPrice());
  }

  @Test
  void testUpdateFoodItem() throws IOException {

    foodItemInDto.setPrice(10.99);
    when(foodItemRepository.findById(1)).thenReturn(Optional.of(foodItem));
    when(foodItemRepository.save(any(FoodItem.class))).thenReturn(foodItem);
    when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
    when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
    when(multipartFile.getContentType()).thenReturn("image/jpeg");
    when(multipartFile.getBytes()).thenReturn(new byte[] {1, 2, 3});


    FoodItemOutDto result = foodItemService.updateFoodItem(1, foodItemInDto, multipartFile);


    assertEquals("pizza", result.getFoodName());
    assertEquals("test restaurant", result.getRestaurantName());
    assertEquals("Italian", result.getCategoryName());
    assertEquals(10.99, result.getPrice(), 0.01);
  }

  @Test
  void testUpdateFoodItemNotFound() {

    when(foodItemRepository.findById(1)).thenReturn(Optional.empty());


    assertThrows(NotFoundException.class, () -> foodItemService.updateFoodItem(1, foodItemInDto, null));
  }

  @Test
  void testGetFoodItemName() {

    when(foodItemRepository.findById(1)).thenReturn(Optional.of(foodItem));


    FoodItemNameOutDto result = foodItemService.getFoodItemName(1);


    assertEquals("Pizza", result.getFoodItemName());
    assertEquals(1, result.getId());
    assertEquals(9.99, result.getPrice(), 0.01);
  }

  @Test
  void testGetFoodItemNameNotFound() {

    when(foodItemRepository.findById(1)).thenReturn(Optional.empty());


    assertThrows(NotFoundException.class, () -> foodItemService.getFoodItemName(1));
  }
}
