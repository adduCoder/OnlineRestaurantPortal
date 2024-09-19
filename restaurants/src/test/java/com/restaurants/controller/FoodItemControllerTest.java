package com.restaurants.controller;

import com.restaurants.dto.FoodItemInDto;
import com.restaurants.dto.FoodItemNameOutDto;
import com.restaurants.dto.FoodItemOutDto;
import com.restaurants.service.FoodItemService;
import com.restaurants.util.ApiResponse;
import com.restaurants.util.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class FoodItemControllerTest {

  @Mock
  private FoodItemService foodItemService;

  @Mock
  private MultipartFile multipartFile;

  @InjectMocks
  private FoodItemController foodItemController;

  private FoodItemInDto foodItemInDto;
  private FoodItemOutDto foodItemOutDto;
  private FoodItemNameOutDto foodItemNameOutDto;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    foodItemInDto = new FoodItemInDto();
    foodItemInDto.setFoodName("TestFoodItem");
    foodItemInDto.setRestaurantId(100); // Placeholder ID
    foodItemInDto.setDescription("Test Description");
    foodItemInDto.setCategoryId(200); // Placeholder ID
    foodItemInDto.setIsAvailable(true);
    foodItemInDto.setPrice(123.45);

    foodItemOutDto = new FoodItemOutDto();
    foodItemOutDto.setId(100); // Placeholder ID
    foodItemOutDto.setFoodName("TestFoodItem");
    foodItemOutDto.setRestaurantName("TestRestaurant");
    foodItemOutDto.setDescription("Test Description");
    foodItemOutDto.setCategoryName("TestCategory");
    foodItemOutDto.setIsAvailable(true);
    foodItemOutDto.setPrice(123.45);

    foodItemNameOutDto = new FoodItemNameOutDto();
    foodItemNameOutDto.setFoodItemName("TestFoodItem");
  }

  @Test
  void testGetAllFoodItem() {
    List<FoodItemOutDto> foodItemOutDtoList = Arrays.asList(foodItemOutDto);
    when(foodItemService.getAll(anyInt())).thenReturn(foodItemOutDtoList);
    ResponseEntity<?> response = foodItemController.getAllFoodItem(100); // Placeholder ID
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(foodItemOutDtoList, response.getBody());
  }

  @Test
  void testAddFoodItem() {
    when(foodItemService.add(any(FoodItemInDto.class), any(MultipartFile.class)))
      .thenReturn(foodItemOutDto);

    ResponseEntity<?> response = foodItemController.addFoodItem(
      foodItemInDto,
      multipartFile
    );
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(new ApiResponse(Constant.FOODITEM_CREATED_SUCCESS), response.getBody());
  }

  @Test
  void testUpdateFoodItem() {
    when(foodItemService.updateFoodItem(anyInt(), any(FoodItemInDto.class), any(MultipartFile.class)))
      .thenReturn(foodItemOutDto);

    ResponseEntity<?> response = foodItemController.updateFoodItem(
      100, // Placeholder ID
      foodItemInDto,
      multipartFile
    );
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(new ApiResponse(Constant.FOODITEM_UPDATED_SUCCESS), response.getBody());
  }

  @Test
  void testGetFoodItemName() {
    when(foodItemService.getFoodItemName(anyInt())).thenReturn(foodItemNameOutDto);
    ResponseEntity<?> response = foodItemController.getFoodItemName(100); // Placeholder ID
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(foodItemNameOutDto, response.getBody());
  }
}
