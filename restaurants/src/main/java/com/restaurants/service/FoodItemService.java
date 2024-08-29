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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    Optional<Restaurant> optionalRestaurant = restaurantRepo.findById(foodItem.getRestaurantId());
    String result = "Not Available";
    if (optionalRestaurant.isPresent()) {
      Restaurant restaurant = optionalRestaurant.get();
      result = restaurant.getRestaurantName();
    }
    return result;
  }


  /**
   * Get category name by FoodItem.
   */
  public String getCategoryName(FoodItem foodItem) {
    Optional<Category> optionalCategory = categoryRepo.findById(foodItem.getCategoryId());
    String result = "Not Available";
    if (optionalCategory.isPresent()) {
      Category category = optionalCategory.get();
      result = category.getName();
    }
    return result;
  }

  /**
   * Add a new food item.
   */
  public FoodItemOutDto add(FoodItemInDto foodItemInDto) {
    FoodItem foodItem = DtoConversion.mapToFoodItem(foodItemInDto);
    foodItemRepo.save(foodItem);
    String restaurantName = getRestaurantName(foodItem);
    String categoryName = getCategoryName(foodItem);
    return DtoConversion.mapToFoodItemOutDto(foodItem, restaurantName, categoryName);
  }

  /**
   * Get all food items for a restaurant.
   */
  public List<FoodItemOutDto> getAll(Integer restaurantId) {
    List<FoodItem> foodItemList = foodItemRepo.findAllByRestaurantId(restaurantId);
    List<FoodItemOutDto> foodItemOutDtoList = new ArrayList<>();
    for (FoodItem foodItem : foodItemList) {
      String restaurantName = getRestaurantName(foodItem);
      String categoryName = getCategoryName(foodItem);
      foodItemOutDtoList.add(DtoConversion.mapToFoodItemOutDto(foodItem, restaurantName, categoryName));
    }
    return foodItemOutDtoList;
  }

  /**
   * Update an existing food item.
   */
  public FoodItemOutDto updateFoodItem(Integer foodItemId, FoodItemInDto foodItemInDto) {
    Optional<FoodItem> optionalFoodItem = foodItemRepo.findById(foodItemId);
    if (!optionalFoodItem.isPresent()) {
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
    return DtoConversion.mapToFoodItemOutDto(foodItem, restaurantName, categoryName);
  }
}
