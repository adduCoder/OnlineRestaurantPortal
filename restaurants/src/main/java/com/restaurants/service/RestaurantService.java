package com.restaurants.service;

import com.restaurants.dto.RestaurantInDto;
import com.restaurants.dto.RestaurantOutDto;
import com.restaurants.dto.UserOutDto;
import com.restaurants.dtoconversion.DtoConversion;
import com.restaurants.entities.Restaurant;
import com.restaurants.exceptionhandler.NotFound;
import com.restaurants.exceptionhandler.OperationNotAllowed;
import com.restaurants.repository.RestaurantRepo;
import com.restaurants.util.Constant;
import com.restaurants.util.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RestaurantService {
  @Autowired
  private RestaurantRepo restaurantRepo;

  @Autowired
  private UserFClient userFClient;


  public static String stringFormatter(String currentString) {
    if (currentString == null || currentString.isEmpty()) {
      return currentString;
    }
    currentString = currentString.trim();
    currentString = currentString.replaceAll("\\s+", " ");
    return currentString.toLowerCase();
  }

  /**
   * Adds a new restaurant.
   * @param restaurantInDto The DTO containing restaurant details.
   * @return The DTO of the added restaurant.
   */
  public RestaurantOutDto addRestaurant(RestaurantInDto restaurantInDto, MultipartFile multipartFile) {
    log.info("Adding new restaurant with name: {}", restaurantInDto.getRestaurantName());
    //check for userId
    Restaurant restaurant = DtoConversion.mapToRestaurant(restaurantInDto);
    UserOutDto userOutDto;
    try {
      userOutDto = userFClient.getUserById(restaurantInDto.getUserId());
    }
    catch (Exception e) {
      throw new NotFound(Constant.USER_NOT_FOUND);
    }
    if (userOutDto.getRole() == Role.USER) {
      throw new OperationNotAllowed();
    }
    try {
      if (multipartFile != null && !multipartFile.isEmpty()) {
        restaurant.setImageData(multipartFile.getBytes());
      }
    }
    catch (IOException e) {
      log.error("Error occurred while processing the image file");
    }
    restaurantRepo.save(restaurant);
    log.info("Restaurant added successfully ");
    return DtoConversion.mapToRestaurantOutDto(restaurant);
  }

  /**
   * Retrieves all restaurants for a given user.
   * @param userId The ID of the user.
   * @return A list of DTOs for the user's restaurants.
   */
  public List<RestaurantOutDto> getAll(Integer userId) {
    log.info("Fetching all restaurants for user ID: {}", userId);
    List<Restaurant> restaurantList = restaurantRepo.findByUserId(userId);
    List<RestaurantOutDto> restaurantOutDtoList = new ArrayList<>();
    for (Restaurant restaurant:restaurantList) {
      restaurantOutDtoList.add(DtoConversion.mapToRestaurantOutDto(restaurant));
    }
    log.info("Found {} restaurants for user ID: {}", restaurantOutDtoList.size(), userId);
    return restaurantOutDtoList;
  }

  public List<RestaurantOutDto> getAllRestros() {
    List<Restaurant> restaurantList = restaurantRepo.findAll();
    List<RestaurantOutDto> restaurantOutDtoList = new ArrayList<>();
    for (Restaurant restaurant:restaurantList) {
      restaurantOutDtoList.add(DtoConversion.mapToRestaurantOutDto(restaurant));
    }
    return restaurantOutDtoList;
  }

  public void updateRestaurant(Integer restaurantId, RestaurantInDto restaurantInDto, MultipartFile multipartFile) {
    Optional<Restaurant> optionalRestaurant = restaurantRepo.findById(restaurantId);
    if(!optionalRestaurant.isPresent()){
      throw new NotFound(Constant.RESTAURANT_NOT_FOUND);
    }
    UserOutDto userOutDto;
    try {
      userOutDto = userFClient.getUserById(restaurantInDto.getUserId());
    }
    catch (Exception e) {
      throw new NotFound(Constant.USER_NOT_FOUND);
    }
    if (userOutDto.getRole() == Role.USER) {
      throw new OperationNotAllowed();
    }

    Restaurant restaurant = optionalRestaurant.get();
    restaurant.setRestaurantName(stringFormatter(restaurantInDto.getRestaurantName()));
    restaurant.setAddress(stringFormatter(restaurantInDto.getAddress()));
    restaurant.setDescription(stringFormatter(restaurantInDto.getDescription()));
    restaurant.setContactNumber(restaurantInDto.getContactNumber());
    restaurant.setAddress(stringFormatter(restaurantInDto.getAddress()));
    try {
      if (multipartFile != null && !multipartFile.isEmpty()) {
        restaurant.setImageData(multipartFile.getBytes());
        log.info("Image uploaded successfully");
      }
      else{
        restaurant.setImageData(null);
      }
    }
    catch (IOException e) {
      log.error("Error occurred while processing the image file");
    }
    restaurantRepo.save(restaurant);
  }

  public RestaurantOutDto getRestaurantById(Integer restaurantId) {
    Optional<Restaurant> optionalRestaurant = restaurantRepo.findById(restaurantId);
    if(!optionalRestaurant.isPresent()){
      throw new NotFound(Constant.RESTAURANT_NOT_FOUND);
    }
    Restaurant restaurant = optionalRestaurant.get();
    RestaurantOutDto restaurantOutDto = DtoConversion.mapToRestaurantOutDto(restaurant);
    return restaurantOutDto;
  }
}
