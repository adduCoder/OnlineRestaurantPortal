package com.restaurants.service;

import com.restaurants.dtoconversion.DtoConversion;
import com.restaurants.entities.Restaurant;
import com.restaurants.indto.RestaurantInDto;
import com.restaurants.outdto.RestaurantOutDto;
import com.restaurants.repository.RestaurantRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class RestaurantService {
  @Autowired
  private RestaurantRepo restaurantRepo;
  /**
   * Adds a new restaurant.
   * @param restaurantInDto The DTO containing restaurant details.
   * @return The DTO of the added restaurant.
   */
  public RestaurantOutDto addRestaurant(RestaurantInDto restaurantInDto) {
    log.info("Adding new restaurant with name: {}", restaurantInDto.getRestaurantName());
    Restaurant restaurant = DtoConversion.mapToRestaurant(restaurantInDto);
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
}
