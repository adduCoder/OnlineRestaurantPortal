package com.restaurants.controller;

import com.restaurants.dto.RestaurantInDto;
import com.restaurants.dto.RestaurantOutDto;
import com.restaurants.service.RestaurantService;
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

class RestaurantControllerTest {

  @Mock
  private RestaurantService restaurantService;

  @Mock
  private MultipartFile multipartFile; // Mock MultipartFile

  @InjectMocks
  private RestaurantController restaurantController;

  private RestaurantInDto restaurantInDto;
  private RestaurantOutDto restaurantOutDto;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    restaurantInDto = new RestaurantInDto();
    restaurantInDto.setUserId(1); // Placeholder User ID
    restaurantInDto.setRestaurantName("Test Restaurant");
    restaurantInDto.setAddress("123 Test St, Test City, TC");
    restaurantInDto.setContactNumber("0000000000");
    restaurantInDto.setDescription("Test description");


    restaurantOutDto = new RestaurantOutDto();
    restaurantOutDto.setId(1); // Placeholder Restaurant ID
    restaurantOutDto.setUserId(1); // Placeholder User ID
    restaurantOutDto.setRestaurantName("Test Restaurant");
    restaurantOutDto.setAddress("123 Test St, Test City, TC");
    restaurantOutDto.setContactNumber("0000000000");
    restaurantOutDto.setDescription("Test description");
  }

  @Test
  void testAddRestaurant() {
    when(multipartFile.getOriginalFilename()).thenReturn("test-restaurant.jpg");

    when(restaurantService.addRestaurant(any(RestaurantInDto.class), any(MultipartFile.class)))
      .thenReturn(restaurantOutDto);

    ResponseEntity<?> response = restaurantController.addRestaurant(
      restaurantInDto,
      multipartFile
    );

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(new ApiResponse(Constant.RESTAURANT_ADDED_SUCCESS), response.getBody());
  }

  @Test
  void testUpdateRestaurant() {
    when(multipartFile.getOriginalFilename()).thenReturn("updated-restaurant.jpg");

    when(restaurantService.addRestaurant(any(RestaurantInDto.class), any(MultipartFile.class)))
      .thenReturn(restaurantOutDto);

    ResponseEntity<?> response = restaurantController.updateRestaurant(
      1,
      restaurantInDto,
      multipartFile
    );

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(new ApiResponse(Constant.RESTAURANT_UPDATED_SUCCESS), response.getBody());
  }

  @Test
  void testGetRestaurantById() {
    when(restaurantService.getRestaurantById(anyInt())).thenReturn(restaurantOutDto);
    ResponseEntity<?> response = restaurantController.getRestaurantById(1); // Placeholder Restaurant ID
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(restaurantOutDto, response.getBody());
  }

  @Test
  void testGetAllRestaurant() {
    List<RestaurantOutDto> restaurantOutDtoList = Arrays.asList(restaurantOutDto);
    when(restaurantService.getAll(anyInt())).thenReturn(restaurantOutDtoList);
    ResponseEntity<?> response = restaurantController.getAllRestaurant(1); // Placeholder User ID
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(restaurantOutDtoList, response.getBody());
  }

  @Test
  void testGetAllRestaurants() {
    List<RestaurantOutDto> restaurantOutDtoList = Arrays.asList(restaurantOutDto);
    when(restaurantService.getAllRestros()).thenReturn(restaurantOutDtoList);
    ResponseEntity<?> response = restaurantController.getAllRestaurants();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(restaurantOutDtoList, response.getBody());
  }
}
