package com.restaurants.service;

import com.restaurants.dto.CategoryInDto;
import com.restaurants.dto.CategoryOutDto;
import com.restaurants.dto.CategoryUpdateInDto;
import com.restaurants.dtoconversion.DtoConversion;
import com.restaurants.entities.Category;
import com.restaurants.entities.Restaurant;
import com.restaurants.exceptionhandler.AlreadyExists;
import com.restaurants.exceptionhandler.NotFound;
import com.restaurants.repository.CategoryRepo;
import com.restaurants.repository.RestaurantRepo;
import com.restaurants.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CategoryService {
  @Autowired
  private CategoryRepo categoryRepo;

  @Autowired
  private RestaurantRepo restaurantRepo;

  public String getRestaurantName(Integer restaurantId) {
    String name = "Not Available";
    Optional<Restaurant> optionalRestaurant = restaurantRepo.findById(restaurantId);
    if (optionalRestaurant.isPresent()) {
      Restaurant restaurant = optionalRestaurant.get();
      name = restaurant.getRestaurantName();
    }
    return name;
  }
  /**
   * Add a new category.
   */
  public CategoryOutDto addCategory(CategoryInDto categoryInDto) {
    log.info("Adding a new category with name: {}", categoryInDto.getName());
    //Category category = categoryRepo.save(DtoConversion.mapToCategory(categoryInDto));
    categoryInDto.setName(categoryInDto.getName().trim());
    Optional<Restaurant> optionalRestaurant = restaurantRepo.findById(categoryInDto.getRestaurantId());
    if (!optionalRestaurant.isPresent()) {
      throw new NotFound(Constant.RESTAURANT_NOT_FOUND);
    }
    Optional<Category> existedOptionalCategory =
      categoryRepo.findByNameAndRestaurantId(categoryInDto.getName().toLowerCase(), categoryInDto.getRestaurantId());
    if (existedOptionalCategory.isPresent()) {
      throw new AlreadyExists(Constant.CATEGORY_ALREADY_EXISTS);
    }
    log.info("Category added successfully");
    categoryInDto.setName(categoryInDto.getName().toLowerCase());
    Category savedCategory = categoryRepo.save(DtoConversion.mapToCategory(categoryInDto));
    return DtoConversion.mapToCategoryOutDto(savedCategory);
  }

  /**
   * Get a category by ID.
   */
  public CategoryOutDto getCategory(Integer id) {
    log.info("Fetching category with ID: {}", id);
    Optional<Category> optionalCategory = categoryRepo.findById(id);
    if (!optionalCategory.isPresent()) {
      log.error("Category not found with ID: {}", id);
      throw new NotFound(Constant.CATEGORY_NOT_FOUND);
    }
    log.info("Category fetched successfully with ID: {}", id);
    CategoryOutDto categoryOutDto = DtoConversion.mapToCategoryOutDto(optionalCategory.get());
    categoryOutDto.setRestaurantName(getRestaurantName(categoryOutDto.getResturantId()));
    return categoryOutDto;
  }

  /**
   * Get all categories for a restaurant.
   */
  public List<CategoryOutDto> getAllCategory(Integer restaurantId) {
    log.info("Fetching all categories for restaurant ID: {}", restaurantId);
    Optional<Restaurant> optionalRestaurant = restaurantRepo.findById(restaurantId);
    if (!optionalRestaurant.isPresent()) {
      throw new NotFound(Constant.RESTAURANT_NOT_FOUND);
    }
    List<Category> categoryList = categoryRepo.findAllByRestaurantId(restaurantId);
    List<CategoryOutDto> categoryOutDtoList = new ArrayList<>();
    for (Category category:categoryList) {
      CategoryOutDto categoryOutDto = DtoConversion.mapToCategoryOutDto(category);
      categoryOutDto.setRestaurantName(getRestaurantName(categoryOutDto.getResturantId()));
      categoryOutDtoList.add(categoryOutDto);
    }
    log.info("Found {} categories for restaurant ID: {}", categoryOutDtoList.size(), restaurantId);
    return categoryOutDtoList;
  }

  /**
   * Update a category.
   */
  public CategoryOutDto updateCategory(Integer categoryId, CategoryUpdateInDto categoryUpdateInDto) {
    log.info("Updating category with ID: {}", categoryId);
    Optional<Category> optionalCategory = categoryRepo.findById(categoryId);
    if (!optionalCategory.isPresent()) {
      log.error("Category not found with ID: {}", categoryId);
      throw new NotFound(Constant.CATEGORY_NOT_FOUND);
    }
    Category category = optionalCategory.get();
    List<Category> categoryList = categoryRepo.findAllByRestaurantId(category.getRestaurantId());
    for (Category subCategory:categoryList) {
      if (subCategory.getName().equals(categoryUpdateInDto.getName().toLowerCase())) {
        throw new AlreadyExists(Constant.CATEGORY_ALREADY_EXISTS);
      }
    }
    category.setName(categoryUpdateInDto.getName().toLowerCase());
    categoryRepo.save(category);
    log.info("Category updated successfully with ID: {}", categoryId);
    return DtoConversion.mapToCategoryOutDto(category);
  }

}
