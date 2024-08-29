package com.restaurants.dtoconversion;

import com.restaurants.entities.Category;
import com.restaurants.entities.FoodItem;
import com.restaurants.entities.Restaurant;
import com.restaurants.indto.CategoryInDto;
import com.restaurants.indto.FoodItemInDto;
import com.restaurants.indto.RestaurantInDto;
import com.restaurants.outdto.CategoryOutDto;
import com.restaurants.outdto.FoodItemOutDto;
import com.restaurants.outdto.RestaurantOutDto;

/**
 * Utility class for converting between DTOs and entities.
 * Provides static methods to map between DTOs and entity objects for Restaurant, Category, and FoodItem.
 */
public final class DtoConversion {
  // Private constructor to prevent instantiation
  private DtoConversion() {

  }
  public static Restaurant mapToRestaurant(RestaurantInDto restaurantInDto) {
    /**
     * Converts a {@link RestaurantInDto} to a {@link Restaurant} entity.
     *
     * @param restaurantInDto the DTO to convert
     * @return the converted Restaurant entity
     */
    Restaurant restaurant = new Restaurant();
    restaurant.setUserId(restaurantInDto.getUserId());
    restaurant.setRestaurantName(restaurantInDto.getRestaurantName());
    restaurant.setAddress(restaurantInDto.getAddress());
    restaurant.setContactNumber(restaurantInDto.getContactNumber());
    restaurant.setImageUrl(restaurantInDto.getImageUrl());
    restaurant.setDescription(restaurantInDto.getAddress());
    return restaurant;
  }

  /**
   * Converts a {@link Restaurant} entity to a {@link RestaurantOutDto}.
   *
   * @param restaurant the entity to convert
   * @return the converted RestaurantOutDto
   */
  public static RestaurantOutDto mapToRestaurantOutDto(Restaurant restaurant) {
    RestaurantOutDto restaurantOutDto = new RestaurantOutDto();
    restaurantOutDto.setId(restaurant.getId());
    restaurantOutDto.setUserId(restaurant.getUserId());
    restaurantOutDto.setRestaurantName(restaurant.getRestaurantName());
    restaurantOutDto.setDescription(restaurant.getDescription());
    restaurantOutDto.setImageUrl(restaurant.getImageUrl());
    restaurantOutDto.setAddress(restaurant.getAddress());
    restaurantOutDto.setContactNumber(restaurant.getContactNumber());
    return restaurantOutDto;
  }

  /**
   * Converts a {@link CategoryInDto} to a {@link Category} entity.
   *
   * @param categoryInDto the DTO to convert
   * @return the converted Category entity
   */
  public static Category mapToCategory(CategoryInDto categoryInDto) {
    Category category = new Category();
    category.setName(categoryInDto.getName());
    category.setRestaurantId(categoryInDto.getRestaurantId());
    return category;
  }


  /**
   * Converts a {@link Category} entity to a {@link CategoryOutDto}.
   *
   * @param category the entity to convert
   * @return the converted CategoryOutDto
   */
  public static CategoryOutDto mapToCategoryOutDto(Category category) {
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
  public static FoodItemOutDto mapToFoodItemOutDto(FoodItem foodItem, String restaurantName,
                                                   String categoryName) {
    FoodItemOutDto foodItemOutDto = new FoodItemOutDto();
    foodItemOutDto.setId(foodItem.getId());
    foodItemOutDto.setFoodName(foodItem.getFoodName());
    foodItemOutDto.setPrice(foodItem.getPrice());
    foodItemOutDto.setIsAvailable(foodItem.getIsAvailable());
    foodItemOutDto.setDescription(foodItem.getDescription());
    foodItemOutDto.setRestaurantName(restaurantName);
    foodItemOutDto.setCategoryName(categoryName);
    foodItemOutDto.setImageUrl(foodItem.getImageUrl());
    return foodItemOutDto;
  }

  /**
   * Converts a {@link FoodItemInDto} to a {@link FoodItem} entity.
   *
   * @param foodItemInDto the DTO to convert
   * @return the converted FoodItem entity
   */
  public static FoodItem mapToFoodItem(FoodItemInDto foodItemInDto) {
    FoodItem foodItem = new FoodItem();
    foodItem.setFoodName(foodItemInDto.getFoodName());
    foodItem.setPrice(foodItemInDto.getPrice());
    foodItem.setCategoryId(foodItemInDto.getCategoryId());
    foodItem.setRestaurantId(foodItemInDto.getRestaurantId());
    foodItem.setDescription(foodItemInDto.getDescription());
    foodItem.setIsAvailable(foodItemInDto.getIsAvailable());
    foodItem.setImageUrl(foodItemInDto.getImageUrl());
    return foodItem;
  }

}
