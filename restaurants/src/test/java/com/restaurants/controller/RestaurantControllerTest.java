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
    restaurantInDto.setUserId(1);
    restaurantInDto.setRestaurantName("Spicy Treats");
    restaurantInDto.setAddress("MG Road, Indore, MP");
    restaurantInDto.setContactNumber("9876543210");
    restaurantInDto.setDescription("A place for spicy delights");
    // Uncomment if using imageUrl
    // restaurantInDto.setImageUrl("http://example.com/image.jpg");

    restaurantOutDto = new RestaurantOutDto();
    restaurantOutDto.setId(1);
    restaurantOutDto.setUserId(1);
    restaurantOutDto.setRestaurantName("Spicy Treats");
    restaurantOutDto.setAddress("MG Road, Indore, MP");
    restaurantOutDto.setContactNumber("9876543210");
    restaurantOutDto.setDescription("A place for spicy delights");
    // Uncomment if using imageUrl
    // restaurantOutDto.setImageUrl("http://example.com/image.jpg");
  }

  @Test
  void testAddRestaurant() {
    // Mock behavior for MultipartFile if needed
    when(multipartFile.getOriginalFilename()).thenReturn("restaurant.jpg");

    // Adjust service mock to handle MultipartFile
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
    // Mock behavior for MultipartFile if needed
    when(multipartFile.getOriginalFilename()).thenReturn("updated-restaurant.jpg");

    // Adjust service mock to handle MultipartFile
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
    ResponseEntity<?> response = restaurantController.getRestaurantById(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(restaurantOutDto, response.getBody());
  }

  @Test
  void testGetAllRestaurant() {
    List<RestaurantOutDto> restaurantOutDtoList = Arrays.asList(restaurantOutDto);
    when(restaurantService.getAll(anyInt())).thenReturn(restaurantOutDtoList);
    ResponseEntity<?> response = restaurantController.getAllRestaurant(1);
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
