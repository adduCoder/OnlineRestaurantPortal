package com.restaurants.service;

import com.restaurants.dtoconversion.DtoConversion;
import com.restaurants.entities.Restaurant;
import com.restaurants.indto.RestaurantInDto;
import com.restaurants.outdto.RestaurantOutDto;
import com.restaurants.repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    Restaurant restaurant = DtoConversion.mapToRestaurant(restaurantInDto);
    restaurantRepo.save(restaurant);
    return DtoConversion.mapToRestaurantOutDto(restaurant);
  }

  /**
   * Retrieves all restaurants for a given user.
   * @param userId The ID of the user.
   * @return A list of DTOs for the user's restaurants.
   */
  public List<RestaurantOutDto> getAll(Integer userId) {
    List<Restaurant> restaurantList = restaurantRepo.findByUserId(userId);
    List<RestaurantOutDto> restaurantOutDtoList = new ArrayList<>();
    for (Restaurant restaurant:restaurantList) {
      restaurantOutDtoList.add(DtoConversion.mapToRestaurantOutDto(restaurant));
    }
    return restaurantOutDtoList;
  }
}
