package com.restaurants.service;

import com.restaurants.dto.FoodItemInDto;
import com.restaurants.dto.FoodItemNameOutDto;
import com.restaurants.dto.FoodItemOutDto;
import com.restaurants.dtoconversion.DtoConversion;
import com.restaurants.entities.Category;
import com.restaurants.entities.FoodItem;
import com.restaurants.entities.Restaurant;
import com.restaurants.exceptionhandler.AlreadyExists;
import com.restaurants.exceptionhandler.NotFound;
import com.restaurants.repository.CategoryRepo;
import com.restaurants.repository.FoodItemRepo;
import com.restaurants.repository.RestaurantRepo;
import com.restaurants.util.Constant;
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

  private boolean isValidImageFormat(String contentType) {
    return contentType != null &&
      (contentType.equals("image/png") ||
        contentType.equals("image/jpeg") ||
        contentType.equals("image/jpg"));
  }

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
    foodItemInDto.setFoodName(foodItemInDto.getFoodName().trim());

    // Mapping DTO to FoodItem entity
    FoodItem foodItem = DtoConversion.mapToFoodItem(foodItemInDto);

    // Check if the restaurant exists
    Optional<Restaurant> optionalRestaurant = restaurantRepo.findById(foodItemInDto.getRestaurantId());
    if (!optionalRestaurant.isPresent()) {
      throw new NotFound(Constant.RESTAURANT_NOT_FOUND);
    }

    // Check if the category exists and belongs to the restaurant
    Optional<Category> optionalCategory = categoryRepo.findById(foodItemInDto.getCategoryId());
    if (!optionalCategory.isPresent()) {
      throw new NotFound(Constant.CATEGORY_NOT_FOUND);
    } else {
      Category category = optionalCategory.get();
      if (category.getRestaurantId() != foodItemInDto.getRestaurantId()) {
        throw new NotFound(Constant.CATEGORY_NOT_FOUND);
      }
    }

    // Check for existing food items with the same name
    List<FoodItem> foodItemList = foodItemRepo.findAllByRestaurantId(foodItemInDto.getRestaurantId());
    for (FoodItem subFoodItem : foodItemList) {
      if (subFoodItem.getFoodName().equals(foodItemInDto.getFoodName().toLowerCase())) {
        throw new AlreadyExists(Constant.FOODITEM_ALREADY_EXISTS);
      }
    }

    // Validate and process image if uploaded
    try {
      if (multipartFile != null && !multipartFile.isEmpty()) {
        String contentType = multipartFile.getContentType();

        // Check if the image format is valid (png, jpg, jpeg)
        if (!isValidImageFormat(contentType)) {
          throw new IllegalArgumentException(Constant.BAD_IMAGE_EXTENSION);
        }

        foodItem.setImageData(multipartFile.getBytes());
        log.info("Image uploaded successfully for food item: {}", foodItemInDto.getFoodName());
      }
    } catch (IOException e) {
      log.error("Error occurred while processing the image file for food item: {}", foodItemInDto.getFoodName(), e);
    }

    // Convert the food name to lowercase for consistency and save the food item
    foodItem.setFoodName(foodItem.getFoodName().toLowerCase());
    foodItemRepo.save(foodItem);

    // Get restaurant and category names for response
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
    Optional<Restaurant> optionalRestaurant = restaurantRepo.findById(restaurantId);
    if (!optionalRestaurant.isPresent()) {
      throw new NotFound(Constant.RESTAURANT_NOT_FOUND);
    }
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

  public FoodItemOutDto updateFoodItem(Integer foodItemId, FoodItemInDto foodItemInDto, MultipartFile multipartFile) {
    log.info("Updating food item with ID: {}", foodItemId);

    // Retrieve the existing food item
    Optional<FoodItem> optionalFoodItem = foodItemRepo.findById(foodItemId);
    if (!optionalFoodItem.isPresent()) {
      log.error("Food item not found with ID: {}", foodItemId);
      throw new NotFound(Constant.FOODITEM_NOT_FOUND);
    }

    // Validate the existence of the restaurant and category
    Optional<Restaurant> optionalRestaurant = restaurantRepo.findById(foodItemInDto.getRestaurantId());
    if (!optionalRestaurant.isPresent()) {
      throw new NotFound(Constant.RESTAURANT_NOT_FOUND);
    }
    Optional<Category> optionalCategory = categoryRepo.findById(foodItemInDto.getCategoryId());
    if (!optionalCategory.isPresent()) {
      throw new NotFound(Constant.CATEGORY_NOT_FOUND);
    }

    // Update food item details
    FoodItem foodItem = optionalFoodItem.get();
    foodItem.setPrice(foodItemInDto.getPrice());
    foodItem.setFoodName(foodItemInDto.getFoodName().toLowerCase());
    foodItem.setIsAvailable(foodItemInDto.getIsAvailable());
    foodItem.setDescription(foodItemInDto.getDescription());

    try {
      if (multipartFile != null && !multipartFile.isEmpty()) {
        String contentType = multipartFile.getContentType();

        // Validate the image format
        if (!isValidImageFormat(contentType)) {
          throw new IllegalArgumentException(Constant.BAD_IMAGE_EXTENSION);
        }

        foodItem.setImageData(multipartFile.getBytes());
        log.info("Image uploaded successfully for food item: {}", foodItemInDto.getFoodName());
      } else {
        // Optional: Handle cases where no file is uploaded by keeping the current image data
        // foodItem.setImageData(null); // Uncomment if you want to clear the image data when no file is provided
      }
    } catch (IOException e) {
      log.error("Error occurred while processing the image file for food item: {}", foodItemInDto.getFoodName(), e);
    }

    // Save the updated food item
    foodItemRepo.save(foodItem);

    // Retrieve restaurant and category names
    String restaurantName = getRestaurantName(foodItem);
    String categoryName = getCategoryName(foodItem);

    log.info("Food item updated successfully with ID: {}", foodItemId);
    return DtoConversion.mapToFoodItemOutDto(foodItem, restaurantName, categoryName);
  }

  public FoodItemNameOutDto getFoodItemName(Integer foodItemId) {
    Optional<FoodItem> optionalFoodItem = foodItemRepo.findById(foodItemId);
    if (!optionalFoodItem.isPresent()) {
      throw new NotFound(Constant.FOODITEM_NOT_FOUND);
    }
    FoodItem foodItem = optionalFoodItem.get();
    FoodItemNameOutDto foodItemNameOutDto = new FoodItemNameOutDto();
    foodItemNameOutDto.setFoodItemName(foodItem.getFoodName());
    foodItemNameOutDto.setId(foodItemId);
    foodItemNameOutDto.setPrice(foodItem.getPrice());
    return foodItemNameOutDto;
  }
}
