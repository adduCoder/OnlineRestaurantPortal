package com.restaurants.service;

import com.restaurants.dto.indto.CategoryUpdateInDto;
import com.restaurants.dtoconversion.DtoConversion;
import com.restaurants.entities.Category;
import com.restaurants.entities.Restaurant;
import com.restaurants.exceptionhandler.CategoryAlreadyExists;
import com.restaurants.exceptionhandler.CategoryNotFound;
import com.restaurants.exceptionhandler.RestaurantNotFound;
import com.restaurants.dto.indto.CategoryInDto;
import com.restaurants.dto.outdto.CategoryOutDto;
import com.restaurants.repository.CategoryRepo;
import com.restaurants.repository.RestaurantRepo;
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
  /**
   * Add a new category.
   */
  public CategoryOutDto addCategory(CategoryInDto categoryInDto) {
    log.info("Adding a new category with name: {}", categoryInDto.getName());
    //Category category = categoryRepo.save(DtoConversion.mapToCategory(categoryInDto));
    categoryInDto.setName(categoryInDto.getName().trim());
    Optional<Restaurant> optionalRestaurant = restaurantRepo.findById(categoryInDto.getRestaurantId());
    if (!optionalRestaurant.isPresent()) {
      throw new RestaurantNotFound();
    }
    Optional<Category> existedOptionalCategory =
      categoryRepo.findByNameAndRestaurantId(categoryInDto.getName().toLowerCase(), categoryInDto.getRestaurantId());
    if (existedOptionalCategory.isPresent()) {
      throw new CategoryAlreadyExists();
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
      throw new CategoryNotFound();
    }
    log.info("Category fetched successfully with ID: {}", id);
    return DtoConversion.mapToCategoryOutDto(optionalCategory.get());
  }

  /**
   * Get all categories for a restaurant.
   */
  public List<CategoryOutDto> getAllCategory(Integer restaurantId) {
    log.info("Fetching all categories for restaurant ID: {}", restaurantId);
    Optional<Restaurant> optionalRestaurant = restaurantRepo.findById(restaurantId);
    if (!optionalRestaurant.isPresent()) {
      throw new RestaurantNotFound();
    }
    List<Category> categoryList = categoryRepo.findAllByRestaurantId(restaurantId);
    List<CategoryOutDto> categoryOutDtoList = new ArrayList<>();
    for (Category category:categoryList) {
      categoryOutDtoList.add(DtoConversion.mapToCategoryOutDto(category));
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
      throw new CategoryNotFound();
    }
    Category category = optionalCategory.get();
    List<Category> categoryList = categoryRepo.findAllByRestaurantId(category.getRestaurantId());
    for (Category subCategory:categoryList) {
      if (subCategory.getName().equals(categoryUpdateInDto.getName().toLowerCase())) {
        throw new CategoryAlreadyExists();
      }
    }
    category.setName(categoryUpdateInDto.getName().toLowerCase());
    categoryRepo.save(category);
    log.info("Category updated successfully with ID: {}", categoryId);
    return DtoConversion.mapToCategoryOutDto(category);
  }

}
