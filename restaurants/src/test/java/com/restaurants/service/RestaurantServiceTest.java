package com.restaurants.service;

import com.restaurants.dto.RestaurantInDto;
import com.restaurants.dto.RestaurantOutDto;
import com.restaurants.dto.UserOutDto;
import com.restaurants.entities.Restaurant;
import com.restaurants.exception.NotFoundException;
import com.restaurants.exception.OperationNotAllowedException;
import com.restaurants.repository.RestaurantRepository;
import com.restaurants.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RestaurantServiceTest {

  @Mock
  private RestaurantRepository restaurantRepository;

  @Mock
  private UserFClient userFClient;

  @InjectMocks
  private RestaurantService restaurantService;

  private MultipartFile multipartFile;

  private RestaurantInDto restaurantInDto;
  private Restaurant restaurant;
  private RestaurantOutDto restaurantOutDto;
  private UserOutDto userOutDto;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);

    restaurantInDto = new RestaurantInDto();
    restaurantInDto.setUserId(1);
    restaurantInDto.setRestaurantName("Test Restaurant");
    restaurantInDto.setAddress("Test Address");
    restaurantInDto.setContactNumber("9876543210");
    restaurantInDto.setDescription("Test Description");

    restaurant = new Restaurant();
    restaurant.setId(1);
    restaurant.setUserId(1);
    restaurant.setRestaurantName("Test Restaurant");
    restaurant.setAddress("Test Address");
    restaurant.setContactNumber("9876543210");
    restaurant.setDescription("Test Description");

    restaurantOutDto = new RestaurantOutDto();
    restaurantOutDto.setId(1);
    restaurantOutDto.setUserId(1);
    restaurantOutDto.setRestaurantName("Test Restaurant");
    restaurantOutDto.setAddress("Test Address");
    restaurantOutDto.setContactNumber("9876543210");
    restaurantOutDto.setDescription("Test Description");

    userOutDto = new UserOutDto();
    userOutDto.setRole(Role.OWNER);
  }

  @Test
  public void testAddRestaurant() {

    when(userFClient.getUserById(1)).thenReturn(userOutDto);
    when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);
    multipartFile = mock(MultipartFile.class);
    when(multipartFile.getContentType()).thenReturn("image/png");
    try {
      when(multipartFile.getBytes()).thenReturn(new byte[0]);
    } catch (IOException e) {
      fail("Failed to mock MultipartFile bytes");
    }


    RestaurantOutDto result = restaurantService.addRestaurant(restaurantInDto, multipartFile);


    assertNotNull(result);
    assertEquals("test restaurant", result.getRestaurantName());
    verify(userFClient, times(1)).getUserById(1);
    verify(restaurantRepository, times(1)).save(any(Restaurant.class));
  }

  @Test
  public void testAddRestaurant_UserRoleNotAllowed() {
    // Arrange
    userOutDto.setRole(Role.USER);
    when(userFClient.getUserById(1)).thenReturn(userOutDto);


    assertThrows(OperationNotAllowedException.class, () -> {
      restaurantService.addRestaurant(restaurantInDto, null);
    });
  }

  @Test
  public void testGetAllRestaurants() {
    List<Restaurant> restaurantList = new ArrayList<>();
    restaurantList.add(restaurant);
    when(restaurantRepository.findByUserId(1)).thenReturn(restaurantList);

    List<RestaurantOutDto> result = restaurantService.getAll(1);

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals("Test Restaurant", result.get(0).getRestaurantName());
    verify(restaurantRepository, times(1)).findByUserId(1);
  }

  @Test
  public void testUpdateRestaurant() {
    when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
    when(userFClient.getUserById(1)).thenReturn(userOutDto);

    multipartFile = mock(MultipartFile.class);
    when(multipartFile.getContentType()).thenReturn("image/jpeg");
    try {
      when(multipartFile.getBytes()).thenReturn(new byte[0]);
    } catch (IOException e) {
      fail("Failed to mock MultipartFile bytes");
    }


    restaurantService.updateRestaurant(1, restaurantInDto, multipartFile);


    verify(restaurantRepository, times(1)).save(any(Restaurant.class));
  }

  @Test
  public void testUpdateRestaurant_NotFound() {

    when(restaurantRepository.findById(1)).thenReturn(Optional.empty());
    when(userFClient.getUserById(1)).thenReturn(userOutDto);


    assertThrows(NotFoundException.class, () -> {
      restaurantService.updateRestaurant(1, restaurantInDto, null);
    });
  }

  @Test
  public void testGetRestaurantById() {

    when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));


    RestaurantOutDto result = restaurantService.getRestaurantById(1);


    assertNotNull(result);
    assertEquals("Test Restaurant", result.getRestaurantName());
    verify(restaurantRepository, times(1)).findById(1);
  }

  @Test
  public void testGetRestaurantById_NotFound() {

    when(restaurantRepository.findById(1)).thenReturn(Optional.empty());


    assertThrows(NotFoundException.class, () -> {
      restaurantService.getRestaurantById(1);
    });
  }
}
