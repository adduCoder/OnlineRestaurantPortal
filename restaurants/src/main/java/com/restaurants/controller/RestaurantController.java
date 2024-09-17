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


  /**
   * Service for handling restaurant operations.
   */
  @Autowired
  private RestaurantService restaurantService;

  /**
   * Adds a new restaurant.
   *
   * @param restaurantInDto the information of the restaurant to add
   * @param multipartFile an optional file associated with the restaurant
   * @return ResponseEntity containing the details of the added restaurant and HTTP status
   */
  @PostMapping("/add")
  public ResponseEntity<?> addRestaurant(
    @Valid @ModelAttribute final RestaurantInDto restaurantInDto,
    @RequestParam(value = "multipartFile", required = false) final MultipartFile multipartFile) {
    restaurantService.addRestaurant(restaurantInDto, multipartFile);
    return new ResponseEntity<>(new ApiResponse(Constant.RESTAURANT_ADDED_SUCCESS), HttpStatus.CREATED);
  }

  /**
   * Updates an existing restaurant.
   *
   * @param restaurantId the ID of the restaurant to update
   * @param restaurantInDto the updated information for the restaurant
   * @param multipartFile an optional file associated with the restaurant
   * @return ResponseEntity containing the success message and HTTP status
   */
  @PutMapping("/update/{restaurantId}")
  public ResponseEntity<?> updateRestaurant(
    @PathVariable final Integer restaurantId,
    @Valid @ModelAttribute final RestaurantInDto restaurantInDto,
    @RequestParam(value = "multipartFile", required = false) final MultipartFile multipartFile) {
    restaurantService.updateRestaurant(restaurantId, restaurantInDto, multipartFile);
    return new ResponseEntity<>(new ApiResponse(Constant.RESTAURANT_UPDATED_SUCCESS), HttpStatus.OK);
  }

  /**
   * Retrieves a restaurant by its ID.
   *
   * @param restaurantId the ID of the restaurant to retrieve
   * @return ResponseEntity containing the details of the restaurant and HTTP status
   */
  @GetMapping("/getRestaurant/{restaurantId}")
  public ResponseEntity<?> getRestaurantById(@PathVariable final Integer restaurantId) {
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
  public ResponseEntity<?> getAllRestaurant(@PathVariable final Integer userId) {
    log.info("Fetching all restaurants for user with ID: {}", userId);
    List<RestaurantOutDto> restaurantOutDtoList = restaurantService.getAll(userId);
    log.info("Retrieved restaurants: {}", restaurantOutDtoList);
    return new ResponseEntity<>(restaurantOutDtoList, HttpStatus.OK);
  }

  /**
   * Retrieves all restaurants.
   *
   * @return ResponseEntity containing a list of all restaurants and HTTP status
   */
  @Transactional
  @GetMapping("getAll")
  public ResponseEntity<?> getAllRestaurants() {
    log.info("fetching all restros");
    List<RestaurantOutDto> restaurantOutDtoList = restaurantService.getAllRestros();
    log.info("retrieved restrautants: {}", restaurantOutDtoList);
    return new ResponseEntity<>(restaurantOutDtoList, HttpStatus.OK);
  }

}
