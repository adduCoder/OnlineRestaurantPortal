package com.restaurants.controller;

import com.restaurants.indto.CategoryInDto;
import com.restaurants.outdto.CategoryOutDto;
import com.restaurants.service.CategoryService;
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
    CategoryOutDto categoryOutDto = categoryService.getCategory(id);
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
    List<CategoryOutDto> categoryOutDtoList = categoryService.getAllCategory(restaurantId);
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
    CategoryOutDto categoryOutDto = categoryService.addCategory(categoryInDto);
    if (categoryOutDto == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
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
    CategoryOutDto categoryOutDto = categoryService.updateCategory(categoryId, categoryInDto);
    return new ResponseEntity<>(categoryOutDto, HttpStatus.OK);

  }
}
