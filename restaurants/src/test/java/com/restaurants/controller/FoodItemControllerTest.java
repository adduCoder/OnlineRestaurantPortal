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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class FoodItemControllerTest {

  @Mock
  private FoodItemService foodItemService;

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
    foodItemInDto.setImageUrl("http://example.com/pasta.jpg");

    foodItemOutDto = new FoodItemOutDto();
    foodItemOutDto.setId(1);
    foodItemOutDto.setFoodName("Pasta");
    foodItemOutDto.setRestaurantName("Restaurant A");
    foodItemOutDto.setDescription("Creamy Alfredo Pasta");
    foodItemOutDto.setCategoryName("Italian");
    foodItemOutDto.setIsAvailable(true);
    foodItemOutDto.setPrice(250.0);
    foodItemOutDto.setImageUrl("http://example.com/pasta.jpg");
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
    when(foodItemService.add(any(FoodItemInDto.class))).thenReturn(foodItemOutDto);
    ResponseEntity<?> response = foodItemController.addFoodItem(foodItemInDto);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(foodItemOutDto, response.getBody());
  }
}
