package com.restaurants.controller;

import com.restaurants.indto.FoodItemInDto;
import com.restaurants.outdto.FoodItemOutDto;
import com.restaurants.service.FoodItemService;
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
  private MultipartFile multipartFile; // Mock MultipartFile

  @InjectMocks
  private FoodItemController foodItemController;

  private FoodItemInDto foodItemInDto;
  private FoodItemOutDto foodItemOutDto;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    foodItemInDto = new FoodItemInDto();
    foodItemInDto.setFoodName("Pasta");
    foodItemInDto.setRestaurantId(1);
    foodItemInDto.setDescription("Creamy Alfredo Pasta");
    foodItemInDto.setCategoryId(2);
    foodItemInDto.setIsAvailable(true);
    foodItemInDto.setPrice(250.0);

    foodItemOutDto = new FoodItemOutDto();
    foodItemOutDto.setId(1);
    foodItemOutDto.setFoodName("Pasta");
    foodItemOutDto.setRestaurantName("Restaurant A");
    foodItemOutDto.setDescription("Creamy Alfredo Pasta");
    foodItemOutDto.setCategoryName("Italian");
    foodItemOutDto.setIsAvailable(true);
    foodItemOutDto.setPrice(250.0);
  }

  @Test
  void testGetAllFoodItem() {
    List<FoodItemOutDto> foodItemOutDtoList = Arrays.asList(foodItemOutDto);
    when(foodItemService.getAll(anyInt())).thenReturn(foodItemOutDtoList);
    ResponseEntity<?> response = foodItemController.getAllFoodItem(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(foodItemOutDtoList, response.getBody());
  }

  @Test
  void testAddFoodItem() {
    // Mock behavior for MultipartFile if needed
    when(multipartFile.getOriginalFilename()).thenReturn("beverage.jpg");

    // Adjust service mock to handle MultipartFile
    when(foodItemService.add(any(FoodItemInDto.class), any(MultipartFile.class))).thenReturn(foodItemOutDto);

    ResponseEntity<?> response = foodItemController.addFoodItem(foodItemInDto, multipartFile);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(foodItemOutDto, response.getBody());
  }
}
