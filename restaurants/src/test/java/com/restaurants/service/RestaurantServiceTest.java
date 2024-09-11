package com.restaurants.service;

import com.restaurants.entities.Restaurant;
import com.restaurants.dto.RestaurantInDto;
import com.restaurants.dto.RestaurantOutDto;
import com.restaurants.repository.RestaurantRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestaurantServiceTest {

  @Mock
  private RestaurantRepo restaurantRepo;

  private MultipartFile multipartFile;

  @InjectMocks
  private RestaurantService restaurantService;

  private RestaurantInDto restaurantInDto;
  private Restaurant restaurant;
  private RestaurantOutDto restaurantOutDto;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);

    // Initialize test data
    restaurantInDto = new RestaurantInDto();
    restaurantInDto.setUserId(1);
    restaurantInDto.setRestaurantName("Test Restaurant");
    restaurantInDto.setAddress("Test Address");
    restaurantInDto.setContactNumber("9876543210");
    restaurantInDto.setDescription("Test Description");
    //restaurantInDto.setImageUrl("http://test.com/image.jpg");

    restaurant = new Restaurant();
    restaurant.setId(1);
    restaurant.setUserId(1);
    restaurant.setRestaurantName("Test Restaurant");
    restaurant.setAddress("Test Address");
    restaurant.setContactNumber("9876543210");
    restaurant.setDescription("Test Description");
    //restaurant.setImageUrl("http://test.com/image.jpg");

    restaurantOutDto = new RestaurantOutDto();
    restaurantOutDto.setId(1);
    restaurantOutDto.setUserId(1);
    restaurantOutDto.setRestaurantName("Test Restaurant");
    restaurantOutDto.setAddress("Test Address");
    restaurantOutDto.setContactNumber("9876543210");
    restaurantOutDto.setDescription("Test Description");
   // restaurantOutDto.setImageUrl("http://test.com/image.jpg");
  }

  @Test
  public void testAddRestaurant() {
    // Arrange
    when(restaurantRepo.save(any(Restaurant.class))).thenReturn(restaurant);

    // Act
    RestaurantOutDto result = restaurantService.addRestaurant(restaurantInDto, null);

    // Assert
    assertNotNull(result);
    //assertEquals(1, result.getId());
    assertEquals("Test Restaurant", result.getRestaurantName());
    verify(restaurantRepo, times(1)).save(any(Restaurant.class));
  }

  @Test
  public void testGetAllRestaurants() {
    // Arrange
    List<Restaurant> restaurantList = new ArrayList<>();
    restaurantList.add(restaurant);
    when(restaurantRepo.findByUserId(1)).thenReturn(restaurantList);

    // Act
    List<RestaurantOutDto> result = restaurantService.getAll(1);

    // Assert
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals("Test Restaurant", result.get(0).getRestaurantName());
    verify(restaurantRepo, times(1)).findByUserId(1);
  }
}
