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

public final class DtoConversion {
  private DtoConversion() {

  }
  public static Restaurant mapToRestaurant(RestaurantInDto restaurantInDto) {
    Restaurant restaurant = new Restaurant();
    restaurant.setUserId(restaurantInDto.getUserId());
    restaurant.setRestaurantName(restaurantInDto.getRestaurantName());
    restaurant.setAddress(restaurantInDto.getAddress());
    restaurant.setContactNumber(restaurantInDto.getContactNumber());
    restaurant.setImageUrl(restaurantInDto.getImageUrl());
    restaurant.setDescription(restaurantInDto.getAddress());
    return restaurant;
  }

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

  public static Category mapToCategory(CategoryInDto categoryInDto) {
    Category category = new Category();
    category.setName(categoryInDto.getName());
    category.setRestaurantId(categoryInDto.getRestaurantId());
    return category;
  }

  public static CategoryOutDto mapToCategoryOutDto(Category category) {
    CategoryOutDto categoryOutDto = new CategoryOutDto();
    categoryOutDto.setId(category.getId());
    categoryOutDto.setName(category.getName());
    categoryOutDto.setResturantId(category.getRestaurantId());
    return categoryOutDto;
  }

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
