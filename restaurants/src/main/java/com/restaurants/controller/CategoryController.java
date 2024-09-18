package com.restaurants.controller;

import com.restaurants.dto.CategoryInDto;
import com.restaurants.dto.CategoryOutDto;
import com.restaurants.dto.CategoryUpdateInDto;
import com.restaurants.service.CategoryService;
import com.restaurants.util.ApiResponse;
import com.restaurants.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
/**
 * Controller for managing restaurant categories.
 * Provides endpoints for category management operations including retrieval, creation, and update.
 */
@Slf4j
@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:3000") // Adjust this to your frontend URL
public class CategoryController {
  /** Service for handling category operations. */
  @Autowired
  private CategoryService categoryService;

  /**
   * Retrieves a category by its ID.
   *
   * @param id the ID of the category to retrieve
   * @return ResponseEntity containing the category details and HTTP status
   */
  @GetMapping("/get/{id}")
  public ResponseEntity<?> getCategoryById(@PathVariable final Integer id) {
    log.info("Fetching category with ID: {}", id);
    CategoryOutDto categoryOutDto = categoryService.getCategory(id);
    log.info("Retrieved category with Id: {}", id);
    return new ResponseEntity<>(categoryOutDto, HttpStatus.OK);
  }

  /**
   * Retrieves all categories for a specific restaurant.
   *
   * @param restaurantId the ID of the restaurant for which to fetch categories
   * @return ResponseEntity containing a list of categories and HTTP status
   */
  @GetMapping("/getAll/{restaurantId}")
  public ResponseEntity<?> getAllCategory(@PathVariable final Integer restaurantId) {
    log.info("Fetching all categories for restaurant with ID: {}", restaurantId);
    List<CategoryOutDto> categoryOutDtoList = categoryService.getAllCategory(restaurantId);
    log.info("Retrieved categories with restaurantId {}", restaurantId);
    return new ResponseEntity<>(categoryOutDtoList, HttpStatus.OK);
  }


  /**
   * Adds a new category.
   *
   * @param categoryInDto the category information to add
   * @return ResponseEntity containing the details of the added category and HTTP status
   */
  @PostMapping("/add")
  public ResponseEntity<?> addCategory(@Valid @RequestBody final CategoryInDto categoryInDto) {
    log.info("Adding new category with name: {}", categoryInDto.getName());
    CategoryOutDto categoryOutDto = categoryService.addCategory(categoryInDto);
    if (categoryOutDto == null) {
      log.error("Failed to add category: {}", categoryInDto);
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    log.info("Successfully added category with id: {}", categoryOutDto.getId());
    return new ResponseEntity<>(new ApiResponse(Constant.CATEGORY_CREATED_SUCCESS), HttpStatus.CREATED);
  }

  /**
   * Updates an existing category.
   *
   * @param categoryId the ID of the category to update
   * @param categoryUpdateInDto the updated category information
   * @return ResponseEntity containing a message of successful update and HTTP status
   */
  @PutMapping("/update/{categoryId}")
  public ResponseEntity<?> updateCategory(@Valid @PathVariable final Integer categoryId, @Valid @RequestBody
    final CategoryUpdateInDto categoryUpdateInDto) {
    log.info("Updating category with ID: {} with data: {}", categoryId);
    categoryService.updateCategory(categoryId, categoryUpdateInDto);
    log.info("Category successfully updated");
    return new ResponseEntity<>(new ApiResponse(Constant.CATEGORY_UPDATED_SUCCESS), HttpStatus.OK);
  }
}
