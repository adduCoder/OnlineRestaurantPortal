package com.restaurants.controller;

import com.restaurants.dto.FoodItemInDto;
import com.restaurants.dto.FoodItemNameOutDto;
import com.restaurants.dto.FoodItemOutDto;
import com.restaurants.repository.RestaurantRepository;
import com.restaurants.service.FoodItemService;
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
 * Controller for managing food items in a restaurant.
 * Provides endpoints for food item management operations including retrieval, creation, and update.
 */
@Slf4j
@RestController
@RequestMapping("/foodItem")
@CrossOrigin(origins = "http://localhost:3000")
public class FoodItemController {
  /** Service for handling food item operations. */
  @Autowired
  private FoodItemService foodItemService;

  /** Repository for accessing restaurant data. */
  @Autowired
  private RestaurantRepository restaurantRepository;

  /**
   * Retrieves all food items for a specific restaurant.
   *
   * @param restaurantId the ID of the restaurant for which to fetch food items
   * @return ResponseEntity containing a list of food items and HTTP status
   */
  @Transactional
  @GetMapping("/getAll/{restaurantId}")
  public ResponseEntity<?> getAllFoodItem(@PathVariable final Integer restaurantId) {
    log.info("Fetching all food items for restaurant with ID: {}", restaurantId);
    List<FoodItemOutDto> foodItemOutDtoList = foodItemService.getAll(restaurantId);
    log.info("Retrieved food items: {}", foodItemOutDtoList);
    return new ResponseEntity<>(foodItemOutDtoList, HttpStatus.OK);
  }

  /**
   * Adds a food item.
   *
   * @param foodItemInDto the details of the food item to add
   * @param multipartFile the file associated with the food item (optional)
   * @return a response entity with the result of the operation
   */
  @Transactional
  @PostMapping("/addFoodItem")
  public ResponseEntity<?> addFoodItem(
    @ModelAttribute @Valid final FoodItemInDto foodItemInDto,
    @RequestParam(value = "multipartFile", required = false) final MultipartFile multipartFile) {
    log.info("Adding foodItem : {}", foodItemInDto);
    foodItemService.add(foodItemInDto, multipartFile);
    log.info("FoodItem added successfully");
    return new ResponseEntity<>(new ApiResponse(Constant.FOODITEM_CREATED_SUCCESS), HttpStatus.CREATED);
  }

  /**
   * Updates an existing food item.
   * @param multipartFile the new file associated with the food item (optional)
   * @param foodItemId the ID of the food item to update
   * @param foodItemInDto the new food item information
   * @return ResponseEntity containing the updated food item details and HTTP status
   */
  @Transactional
  @PutMapping("/update/{foodItemId}")
  public ResponseEntity<?> updateFoodItem(@PathVariable final Integer foodItemId,  @Valid @ModelAttribute final FoodItemInDto
    foodItemInDto, @RequestParam(value = "multipartFile", required = false) final MultipartFile multipartFile) {
    log.info("Updating food item with ID: {} with data: {}", foodItemId, foodItemInDto);
    foodItemService.updateFoodItem(foodItemId, foodItemInDto, multipartFile);
    log.info("Fooditem updated successfully");
    return new ResponseEntity<>(new ApiResponse(Constant.FOODITEM_UPDATED_SUCCESS), HttpStatus.OK);
  }

  /**
   * Retrieves the name of a food item by its ID.
   *
   * @param foodItemId the ID of the food item to retrieve
   * @return ResponseEntity containing the food item name and HTTP status
   */
  @GetMapping("/getName/{foodItemId}")
  public ResponseEntity<?> getFoodItemName(@PathVariable final Integer foodItemId) {
    log.info("Fetching foodItem with id: {}", foodItemId);
    FoodItemNameOutDto foodItemNameOutDto = foodItemService.getFoodItemName(foodItemId);
    log.info("Successfully fetched the foodItemName : {}", foodItemNameOutDto.getFoodItemName());
    return new ResponseEntity<>(foodItemNameOutDto, HttpStatus.OK);
  }

}
