package com.restaurants.controller;


import com.restaurants.indto.RestaurantInDto;
import com.restaurants.outdto.RestaurantOutDto;
import com.restaurants.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;


/**
 * Controller for managing restaurant operations.
 * Provides endpoints for adding and retrieving restaurant information.
 */
@Slf4j
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

  @Autowired
  private RestaurantService restaurantService;

  /**
   * Adds a new restaurant.
   *
   * @param restaurantInDto the information of the restaurant to add
   * @return ResponseEntity containing the details of the added restaurant and HTTP status
   */
  @PostMapping("/add")
  public ResponseEntity<?> addRestaurant(@Valid @RequestPart RestaurantInDto restaurantInDto,
                                         @RequestPart MultipartFile multipartFile) {
    log.info("Adding new restaurant with details: {}", restaurantInDto);
    RestaurantOutDto restaurantOutDto = restaurantService.addRestaurant(restaurantInDto, multipartFile);
    if (restaurantOutDto == null) {
      log.warn("Failed to add restaurant with details: {}", restaurantInDto);
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.info("Successfully added restaurant: {}", restaurantOutDto);
    return new ResponseEntity<>(restaurantOutDto, HttpStatus.CREATED);
  }

  /**
   * Retrieves all restaurants associated with a specific user.
   *
   * @param userId the ID of the user for which to fetch restaurants
   * @return ResponseEntity containing a list of restaurants and HTTP status
   */
  @Transactional
  @GetMapping("/get/{userId}")
  public ResponseEntity<?> getAllRestaurant(@PathVariable Integer userId) {
    log.info("Fetching all restaurants for user with ID: {}", userId);
    List<RestaurantOutDto> restaurantOutDtoList = restaurantService.getAll(userId);
    log.info("Retrieved restaurants: {}", restaurantOutDtoList);
    return new ResponseEntity<>(restaurantOutDtoList, HttpStatus.OK);
  }

}
