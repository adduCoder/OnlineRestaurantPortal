package com.restaurants.controller;

import com.restaurants.indto.CategoryInDto;
import com.restaurants.outdto.CategoryOutDto;
import com.restaurants.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * Controller for managing restaurant categories.
 * Provides endpoints for category management operations including retrieval, creation, and update.
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;


  /**
   * Retrieves a category by its ID.
   *
   * @param id the ID of the category to retrieve
   * @return ResponseEntity containing the category details and HTTP status
   */
  @GetMapping("/get/{id}")
  public ResponseEntity<?> getCategoryById(@PathVariable Integer id) {
    log.info("Fetching category with ID: {}", id);
    CategoryOutDto categoryOutDto = categoryService.getCategory(id);
    log.info("Retrieved category: {}", categoryOutDto);
    return new ResponseEntity<>(categoryOutDto, HttpStatus.OK);
  }

  /**
   * Retrieves all categories for a specific restaurant.
   *
   * @param restaurantId the ID of the restaurant for which to fetch categories
   * @return ResponseEntity containing a list of categories and HTTP status
   */
  @GetMapping("/getAll/{restaurantId}")
  public ResponseEntity<?> getAllCategory(@PathVariable Integer restaurantId) {
    log.info("Fetching all categories for restaurant with ID: {}", restaurantId);
    List<CategoryOutDto> categoryOutDtoList = categoryService.getAllCategory(restaurantId);
    log.info("Retrieved categories: {}", categoryOutDtoList);
    return new ResponseEntity<>(categoryOutDtoList, HttpStatus.OK);
  }

  /**
   * Adds a new category.
   *
   * @param categoryInDto the category information to add
   * @return ResponseEntity containing the details of the added category and HTTP status
   */
  @PostMapping("/add")
  public ResponseEntity<?> addCategory(@RequestBody CategoryInDto categoryInDto) {
    log.info("Adding new category: {}", categoryInDto);
    CategoryOutDto categoryOutDto = categoryService.addCategory(categoryInDto);
    if (categoryOutDto == null) {
      log.error("Failed to add category: {}", categoryInDto);
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.info("Added category: {}", categoryOutDto);
    return new ResponseEntity<>(categoryOutDto, HttpStatus.CREATED);
  }

  /**
   * Updates an existing category.
   *
   * @param categoryId the ID of the category to update
   * @param categoryInDto the new category information
   * @return ResponseEntity containing the updated category details and HTTP status
   */
  @PutMapping("/update/{categoryId}")
  public ResponseEntity<?> updateCategory(@PathVariable Integer categoryId, @RequestBody CategoryInDto categoryInDto) {
    log.info("Updating category with ID: {} with data: {}", categoryId, categoryInDto);
    CategoryOutDto categoryOutDto = categoryService.updateCategory(categoryId, categoryInDto);
    log.info("Updated category: {}", categoryOutDto);
    return new ResponseEntity<>(categoryOutDto, HttpStatus.OK);
  }
}
