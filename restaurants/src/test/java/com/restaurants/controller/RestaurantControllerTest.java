package com.restaurants.controller;

import com.restaurants.indto.RestaurantInDto;
import com.restaurants.outdto.RestaurantOutDto;
import com.restaurants.service.RestaurantService;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class RestaurantControllerTest {

  @Mock
  private RestaurantService restaurantService;

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
    //restaurantInDto.setImageUrl("http://example.com/image.jpg");

    restaurantOutDto = new RestaurantOutDto();
    restaurantOutDto.setId(1);
    restaurantOutDto.setUserId(1);
    restaurantOutDto.setRestaurantName("Spicy Treats");
    restaurantOutDto.setAddress("MG Road, Indore, MP");
    restaurantOutDto.setContactNumber("9876543210");
    restaurantOutDto.setDescription("A place for spicy delights");
    //restaurantOutDto.setImageUrl("http://example.com/image.jpg");
  }

//  @Test
//  void testAddRestaurant() {
//    when(restaurantService.addRestaurant(any(RestaurantInDto.class),null)).thenReturn(restaurantOutDto);
//    ResponseEntity<?> response = restaurantController.addRestaurant(restaurantInDto,null);
//    assertEquals(HttpStatus.CREATED, response.getStatusCode());
//    assertEquals(restaurantOutDto, response.getBody());
//  }

  @Test
  void testGetAllRestaurant() {
    List<RestaurantOutDto> restaurantOutDtoList = Arrays.asList(restaurantOutDto);
    when(restaurantService.getAll(anyInt())).thenReturn(restaurantOutDtoList);
    ResponseEntity<?> response = restaurantController.getAllRestaurant(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(restaurantOutDtoList, response.getBody());
  }
}
