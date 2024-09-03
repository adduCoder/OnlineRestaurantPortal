package com.restaurants.controller;

import com.restaurants.indto.FoodItemInDto;
import com.restaurants.outdto.FoodItemOutDto;
import com.restaurants.service.FoodItemService;
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
import org.springframework.web.bind.annotation.RequestBody;
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

  @Autowired
  private FoodItemService foodItemService;

  /**
   * Retrieves all food items for a specific restaurant.
   *
   * @param restaurantId the ID of the restaurant for which to fetch food items
   * @return ResponseEntity containing a list of food items and HTTP status
   */
  @Transactional
  @GetMapping("/getAll/{restaurantId}")
  public ResponseEntity<?> getAllFoodItem(@PathVariable Integer restaurantId) {
    log.info("Fetching all food items for restaurant with ID: {}", restaurantId);
    List<FoodItemOutDto> foodItemOutDtoList = foodItemService.getAll(restaurantId);
    log.info("Retrieved food items: {}", foodItemOutDtoList);
    return new ResponseEntity<>(foodItemOutDtoList, HttpStatus.OK);
  }

  /**
   * Adds a food item.
   *
   * @param multipartFile the file associated with the food item
   * @return a response entity with the result of the operation
   */
  @Transactional
  @PostMapping("/addFoodItem")
  public ResponseEntity<?> addFoodItem(
    @ModelAttribute @Valid FoodItemInDto foodItemInDto,
    @RequestParam(value = "multipartFile", required = false) MultipartFile multipartFile) {
    try {
      FoodItemOutDto foodItemOutDto = foodItemService.add(foodItemInDto, multipartFile);

      return new ResponseEntity<>(foodItemOutDto, HttpStatus.CREATED);
    } catch (Exception e) {
      log.error("Failed to add food item", e);

      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
  /**
   * Updates an existing food item.
   *
   * @param foodItemId the ID of the food item to update
   * @param foodItemInDto the new food item information
   * @return ResponseEntity containing the updated food item details and HTTP status
   */
  @Transactional
  @PutMapping("/update/{foodItemId}")
  public ResponseEntity<?> updateFoodItem(@PathVariable Integer foodItemId, @RequestBody FoodItemInDto foodItemInDto) {
    log.info("Updating food item with ID: {} with data: {}", foodItemId, foodItemInDto);
    FoodItemOutDto foodItemOutDto = foodItemService.updateFoodItem(foodItemId, foodItemInDto);
    log.info("Updated food item: {}", foodItemOutDto);
    return new ResponseEntity<>(foodItemOutDto, HttpStatus.OK);
  }
}
