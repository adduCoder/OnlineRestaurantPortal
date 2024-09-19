package com.restaurants.conversion;

import com.restaurants.dto.CategoryInDto;
import com.restaurants.dto.CategoryOutDto;
import com.restaurants.dto.FoodItemInDto;
import com.restaurants.dto.FoodItemOutDto;
import com.restaurants.dto.RestaurantInDto;
import com.restaurants.dto.RestaurantOutDto;
import com.restaurants.entities.Category;
import com.restaurants.entities.FoodItem;
import com.restaurants.entities.Restaurant;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;


/**
 * Utility class for converting between DTOs and entities.
 * Provides static methods to map between DTOs and entity objects for Restaurant, Category, and FoodItem.
 */
@Slf4j
public final class DtoConversion {
  // Private constructor to prevent instantiation
  private DtoConversion() {

  }

  /**
   * Formats the input string by trimming, replacing multiple spaces with single space,
   * and converting the string to lowercase.
   *
   * @param currentString the string to be formatted
   * @return the formatted string
   */
  public static String stringFormatter(final String currentString) {
    if (currentString == null || currentString.isEmpty()) {
      return currentString;
    }
    String formattedString = currentString.trim();
    formattedString = formattedString.replaceAll("\\s+", " ");
    return formattedString.toLowerCase(Locale.ENGLISH);
  }

  /**
   * Converts a {@link RestaurantInDto} to a {@link Restaurant} entity.
   *
   * @param restaurantInDto the DTO to convert
   * @return the converted Restaurant entity
   */
  public static Restaurant mapToRestaurant(final RestaurantInDto restaurantInDto) {
    Restaurant restaurant = new Restaurant();
    restaurant.setUserId(restaurantInDto.getUserId());
    restaurant.setRestaurantName(stringFormatter(restaurantInDto.getRestaurantName()));
    restaurant.setAddress(restaurantInDto.getAddress());
    restaurant.setContactNumber(restaurantInDto.getContactNumber());
   // restaurant.setImageUrl(restaurantInDto.getImageUrl());
    restaurant.setDescription(stringFormatter(restaurantInDto.getDescription()));
    return restaurant;
  }

  /**
   * Converts a {@link Restaurant} entity to a {@link RestaurantOutDto}.
   *
   * @param restaurant the entity to convert
   * @return the converted RestaurantOutDto
   */
  public static RestaurantOutDto mapToRestaurantOutDto(final Restaurant restaurant) {
    RestaurantOutDto restaurantOutDto = new RestaurantOutDto();
    restaurantOutDto.setId(restaurant.getId());
    restaurantOutDto.setUserId(restaurant.getUserId());
    restaurantOutDto.setRestaurantName(restaurant.getRestaurantName());
    restaurantOutDto.setDescription(restaurant.getDescription());
    //restaurantOutDto.setImageUrl(restaurant.getImageUrl());
    restaurantOutDto.setAddress(restaurant.getAddress());
    restaurantOutDto.setContactNumber(restaurant.getContactNumber());
    restaurantOutDto.setImageData(restaurant.getImageData());
    return restaurantOutDto;
  }

  /**
   * Converts a {@link CategoryInDto} to a {@link Category} entity.
   *
   * @param categoryInDto the DTO to convert
   * @return the converted Category entity
   */
  public static Category mapToCategory(final CategoryInDto categoryInDto) {
    Category category = new Category();
    category.setName(stringFormatter(categoryInDto.getName()));
    category.setRestaurantId(categoryInDto.getRestaurantId());
    return category;
  }


  /**
   * Converts a {@link Category} entity to a {@link CategoryOutDto}.
   *
   * @param category the entity to convert
   * @return the converted CategoryOutDto
   */
  public static CategoryOutDto mapToCategoryOutDto(final Category category) {
    CategoryOutDto categoryOutDto = new CategoryOutDto();
    categoryOutDto.setId(category.getId());
    categoryOutDto.setName(category.getName());
    categoryOutDto.setResturantId(category.getRestaurantId());
    return categoryOutDto;
  }

  /**
   * Converts a {@link FoodItem} entity to a {@link FoodItemOutDto}.
   *
   * @param foodItem        the entity to convert
   * @param restaurantName  the name of the restaurant
   * @param categoryName    the name of the category
   * @return the converted FoodItemOutDto
   */
  public static FoodItemOutDto mapToFoodItemOutDto(final FoodItem foodItem, final String restaurantName,
                                                   final String categoryName) {
    FoodItemOutDto foodItemOutDto = new FoodItemOutDto();
    foodItemOutDto.setId(foodItem.getId());
    foodItemOutDto.setFoodName(stringFormatter(foodItem.getFoodName()));
    foodItemOutDto.setPrice(foodItem.getPrice());
    foodItemOutDto.setIsAvailable(foodItem.getIsAvailable());
    foodItemOutDto.setDescription(foodItem.getDescription());
    foodItemOutDto.setRestaurantName(stringFormatter(restaurantName));
    foodItemOutDto.setCategoryName(categoryName);
    foodItemOutDto.setImageData(foodItem.getImageData());
    foodItemOutDto.setCategoryId(foodItem.getCategoryId());
    foodItemOutDto.setRestaurantId(foodItem.getRestaurantId());
    return foodItemOutDto;
  }

  /**
   * Converts a {@link FoodItemInDto} to a {@link FoodItem} entity.
   *
   * @param foodItemInDto the DTO to convert
   * @return the converted FoodItem entity
   */
  public static FoodItem mapToFoodItem(final FoodItemInDto foodItemInDto) {
    FoodItem foodItem = new FoodItem();
    foodItem.setFoodName(stringFormatter(foodItemInDto.getFoodName()));
    foodItem.setPrice(foodItemInDto.getPrice());
    foodItem.setCategoryId(foodItemInDto.getCategoryId());
    foodItem.setRestaurantId(foodItemInDto.getRestaurantId());
    foodItem.setDescription(foodItemInDto.getDescription());
    foodItem.setIsAvailable(foodItemInDto.getIsAvailable());
    return foodItem;
  }
}
