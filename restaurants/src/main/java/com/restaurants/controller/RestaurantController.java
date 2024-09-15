package com.restaurants.controller;

import com.restaurants.dto.RestaurantInDto;
import com.restaurants.dto.RestaurantOutDto;
import com.restaurants.service.RestaurantService;
import com.restaurants.util.ApiResponse;
import com.restaurants.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@CrossOrigin(origins = "http://localhost:3000")
public class RestaurantController {

  @Autowired
  private RestaurantService restaurantService;

  /**
   * Adds a new restaurant.
   *
   * @return ResponseEntity containing the details of the added restaurant and HTTP status
   */
  @PostMapping("/add")
  public ResponseEntity<?> addRestaurant(
    @Valid @ModelAttribute RestaurantInDto restaurantInDto,
    @RequestParam(value = "multipartFile", required = false) MultipartFile multipartFile) {
    restaurantService.addRestaurant(restaurantInDto, multipartFile);
    return new ResponseEntity<>(new ApiResponse(Constant.RESTAURANT_ADDED_SUCCESS), HttpStatus.CREATED);
  }

  @PutMapping("/update/{restaurantId}")
  public ResponseEntity<?> updateRestaurant(
    @PathVariable Integer restaurantId,
    @Valid @ModelAttribute RestaurantInDto restaurantInDto,
    @RequestParam(value = "multipartFile", required = false) MultipartFile multipartFile) {
    restaurantService.updateRestaurant(restaurantId, restaurantInDto, multipartFile);
    return new ResponseEntity<>(new ApiResponse(Constant.RESTAURANT_UPDATED_SUCCESS), HttpStatus.OK);
  }

  @GetMapping("/getRestaurant/{restaurantId}")
  public ResponseEntity<?> getRestaurantById(@PathVariable Integer restaurantId) {
    RestaurantOutDto restaurantOutDto = restaurantService.getRestaurantById(restaurantId);
    return new ResponseEntity<>(restaurantOutDto, HttpStatus.OK);
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

  @Transactional
  @GetMapping("getAll")
  public ResponseEntity<?> getAllRestaurants() {
    log.info("fetching all restros");
    List<RestaurantOutDto> restaurantOutDtoList = restaurantService.getAllRestros();
    log.info("retrieved restrautants: {}", restaurantOutDtoList);
    return new ResponseEntity<>(restaurantOutDtoList, HttpStatus.OK);
  }

}
