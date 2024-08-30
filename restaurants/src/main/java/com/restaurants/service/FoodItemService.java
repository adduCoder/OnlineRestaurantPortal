package com.restaurants.service;

import com.restaurants.dtoconversion.DtoConversion;
import com.restaurants.entities.Category;
import com.restaurants.entities.FoodItem;
import com.restaurants.entities.Restaurant;
import com.restaurants.exceptionhandler.FoodItemNotFound;
import com.restaurants.indto.FoodItemInDto;
import com.restaurants.outdto.FoodItemOutDto;
import com.restaurants.repository.CategoryRepo;
import com.restaurants.repository.FoodItemRepo;
import com.restaurants.repository.RestaurantRepo;
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
public class FoodItemService {

  @Autowired
  private FoodItemRepo foodItemRepo;

  @Autowired
  private RestaurantRepo restaurantRepo;

  @Autowired
  private CategoryRepo categoryRepo;

  /**
   * Get restaurant name by FoodItem.
   */
  public String getRestaurantName(FoodItem foodItem) {
    log.info("Fetching restaurant name for restaurant ID: {}", foodItem.getRestaurantId());
    Optional<Restaurant> optionalRestaurant = restaurantRepo.findById(foodItem.getRestaurantId());
    String result = "Not Available";
    log.info("Restaurant name found: {}", result);
    if (optionalRestaurant.isPresent()) {
      Restaurant restaurant = optionalRestaurant.get();
      result = restaurant.getRestaurantName();
    }
    else {
      log.warn("Restaurant not found for ID: {}", foodItem.getRestaurantId());
    }
    return result;
  }

  /**
   * Get category name by FoodItem.
   */
  public String getCategoryName(FoodItem foodItem) {
    log.info("Fetching category name for category ID: {}", foodItem.getCategoryId());
    Optional<Category> optionalCategory = categoryRepo.findById(foodItem.getCategoryId());
    String result = "Not Available";
    if (optionalCategory.isPresent()) {
      Category category = optionalCategory.get();
      result = category.getName();
      log.info("Category name found: {}", result);
    }
    else {
      log.warn("Category not found for ID: {}", foodItem.getCategoryId());
    }
    return result;
  }

  /**
   * Add a new food item.
   */
  public FoodItemOutDto add(FoodItemInDto foodItemInDto, MultipartFile multipartFile) {
    log.info("Adding new food item with name: {}", foodItemInDto.getFoodName());
    FoodItem foodItem = DtoConversion.mapToFoodItem(foodItemInDto);
    try {
      if (multipartFile != null && !multipartFile.isEmpty()) {
        foodItem.setImageData(multipartFile.getBytes());
        log.info("Image uploaded successfully for food item: {}", foodItemInDto.getFoodName());
      }
    }
    catch (IOException e) {
      log.error("Error occurred while processing the image file for food item: {}", foodItemInDto.getFoodName(), e);
    }
    foodItemRepo.save(foodItem);
    String restaurantName = getRestaurantName(foodItem);
    String categoryName = getCategoryName(foodItem);
    log.info("Food item added successfully");
    return DtoConversion.mapToFoodItemOutDto(foodItem, restaurantName, categoryName);
  }

  /**
   * Get all food items for a restaurant.
   */
  public List<FoodItemOutDto> getAll(Integer restaurantId) {
    log.info("Fetching all food items for restaurant ID: {}", restaurantId);
    List<FoodItem> foodItemList = foodItemRepo.findAllByRestaurantId(restaurantId);
    List<FoodItemOutDto> foodItemOutDtoList = new ArrayList<>();
    for (FoodItem foodItem : foodItemList) {
      String restaurantName = getRestaurantName(foodItem);
      String categoryName = getCategoryName(foodItem);
      foodItemOutDtoList.add(DtoConversion.mapToFoodItemOutDto(foodItem, restaurantName, categoryName));
    }
    log.info("Found {} food items for restaurant ID: {}", foodItemOutDtoList.size(), restaurantId);
    return foodItemOutDtoList;
  }

  /**
   * Update an existing food item.
   */
  public FoodItemOutDto updateFoodItem(Integer foodItemId, FoodItemInDto foodItemInDto) {
    log.info("Updating food item with ID: {}", foodItemId);
    Optional<FoodItem> optionalFoodItem = foodItemRepo.findById(foodItemId);
    if (!optionalFoodItem.isPresent()) {
      log.error("Food item not found with ID: {}", foodItemId);
      throw  new FoodItemNotFound();
    }
    FoodItem foodItem = optionalFoodItem.get();
    foodItem.setPrice(foodItemInDto.getPrice());
    foodItem.setFoodName(foodItemInDto.getFoodName());
    foodItem.setIsAvailable(foodItemInDto.getIsAvailable());
    foodItem.setDescription(foodItemInDto.getDescription());
    foodItemRepo.save(foodItem);
    String restaurantName = getRestaurantName(foodItem);
    String categoryName = getCategoryName(foodItem);
    log.info("Food item updated successfully with ID: {}", foodItemId);
    return DtoConversion.mapToFoodItemOutDto(foodItem, restaurantName, categoryName);
  }
}
