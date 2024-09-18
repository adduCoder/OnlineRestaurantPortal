package com.restaurants.service;

import com.restaurants.dto.CategoryInDto;
import com.restaurants.dto.CategoryOutDto;
import com.restaurants.dto.CategoryUpdateInDto;
import com.restaurants.conversion.DtoConversion;
import com.restaurants.entities.Category;
import com.restaurants.entities.Restaurant;
import com.restaurants.exception.AlreadyExistsException;
import com.restaurants.exception.NotFoundException;
import com.restaurants.repository.CategoryRepository;
import com.restaurants.repository.RestaurantRepository;
import com.restaurants.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing categories in the restaurant system.
 * This class handles operations related to categories, such as adding, retrieving, updating, and listing categories.
 */
@Slf4j
@Service
public class CategoryService {

  /**
   * Repository for performing CRUD operations on categories.
   */
  @Autowired
  private CategoryRepository categoryRepository;

  /**
   * Repository for performing CRUD operations on restaurants.
   */
  @Autowired
  private RestaurantRepository restaurantRepository;

  /**
   * Retrieves the name of a restaurant by its ID.
   *
   * @param restaurantId the ID of the restaurant
   * @return the name of the restaurant, or "Not Available" if the restaurant is not found
   */
  public String getRestaurantName(final Integer restaurantId) {
    String name = "Not Available";
    Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
    if (optionalRestaurant.isPresent()) {
      Restaurant restaurant = optionalRestaurant.get();
      name = restaurant.getRestaurantName();
    }
    return name;
  }


  /**
   * Adds a new category.
   *
   * @param categoryInDto the data transfer object containing the category information
   * @return the added category as a {@link CategoryOutDto}
   * @throws NotFoundException if the restaurant with the given ID does not exist
   * @throws AlreadyExistsException if a category with the same name already exists for the restaurant
   */

  public CategoryOutDto addCategory(final CategoryInDto categoryInDto) {
    log.info("Adding a new category with name: {}", categoryInDto.getName());
    //Category category = categoryRepo.save(DtoConversion.mapToCategory(categoryInDto));
    categoryInDto.setName(categoryInDto.getName().trim());
    Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(categoryInDto.getRestaurantId());
    if (!optionalRestaurant.isPresent()) {
      throw new NotFoundException(Constant.RESTAURANT_NOT_FOUND);
    }
    Optional<Category> existedOptionalCategory =
      categoryRepository.findByNameAndRestaurantId(categoryInDto.getName().toLowerCase(), categoryInDto.getRestaurantId());
    if (existedOptionalCategory.isPresent()) {
      throw new AlreadyExistsException(Constant.CATEGORY_ALREADY_EXISTS);
    }
    log.info("Category added successfully");
    categoryInDto.setName(categoryInDto.getName().toLowerCase());
    Category savedCategory = categoryRepository.save(DtoConversion.mapToCategory(categoryInDto));
    return DtoConversion.mapToCategoryOutDto(savedCategory);
  }

  /**
   * Retrieves a category by its ID.
   *
   * @param id the ID of the category
   * @return the category as a {@link CategoryOutDto}
   * @throws NotFoundException if the category with the given ID does not exist
   */
  public CategoryOutDto getCategory(final Integer id) {
    log.info("Fetching category with ID: {}", id);
    Optional<Category> optionalCategory = categoryRepository.findById(id);
    if (!optionalCategory.isPresent()) {
      log.error("Category not found with ID: {}", id);
      throw new NotFoundException(Constant.CATEGORY_NOT_FOUND);
    }
    log.info("Category fetched successfully with ID: {}", id);
    CategoryOutDto categoryOutDto = DtoConversion.mapToCategoryOutDto(optionalCategory.get());
    categoryOutDto.setRestaurantName(getRestaurantName(categoryOutDto.getResturantId()));
    return categoryOutDto;
  }

  /**
   * Retrieves all categories for a given restaurant.
   *
   * @param restaurantId the ID of the restaurant
   * @return a list of categories as {@link CategoryOutDto} objects
   * @throws NotFoundException if the restaurant with the given ID does not exist
   */
  public List<CategoryOutDto> getAllCategory(final Integer restaurantId) {
    log.info("Fetching all categories for restaurant ID: {}", restaurantId);
    Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
    if (!optionalRestaurant.isPresent()) {
      throw new NotFoundException(Constant.RESTAURANT_NOT_FOUND);
    }
    List<Category> categoryList = categoryRepository.findAllByRestaurantId(restaurantId);
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
   * Updates an existing category.
   *
   * @param categoryId the ID of the category to update
   * @param categoryUpdateInDto the data transfer object containing updated category information
   * @return the updated category as a {@link CategoryOutDto}
   * @throws NotFoundException if the category with the given ID does not exist
   * @throws AlreadyExistsException if a category with the updated name already exists for the restaurant
   */
  public CategoryOutDto updateCategory(final Integer categoryId, final CategoryUpdateInDto categoryUpdateInDto) {
    log.info("Updating category with ID: {}", categoryId);
    Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
    if (!optionalCategory.isPresent()) {
      log.error("Category not found with ID: {}", categoryId);
      throw new NotFoundException(Constant.CATEGORY_NOT_FOUND);
    }
    Category category = optionalCategory.get();
    List<Category> categoryList = categoryRepository.findAllByRestaurantId(category.getRestaurantId());
    for (Category subCategory:categoryList) {
      if (subCategory.getName().equals(categoryUpdateInDto.getName().toLowerCase())) {
        throw new AlreadyExistsException(Constant.CATEGORY_ALREADY_EXISTS);
      }
    }
    category.setName(categoryUpdateInDto.getName().toLowerCase());
    categoryRepository.save(category);
    log.info("Category updated successfully with ID: {}", categoryId);
    return DtoConversion.mapToCategoryOutDto(category);
  }

}
