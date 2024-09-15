package com.restaurants.controller;

import com.restaurants.dto.CategoryInDto;
import com.restaurants.dto.CategoryOutDto;
import com.restaurants.dto.CategoryUpdateInDto;
import com.restaurants.service.CategoryService;
import com.restaurants.util.ApiResponse;
import com.restaurants.util.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class CategoryControllerTest {

  @Mock
  private CategoryService categoryService;

  @InjectMocks
  private CategoryController categoryController;

  private CategoryInDto categoryInDto;
  private CategoryOutDto categoryOutDto;
  private CategoryUpdateInDto categoryUpdateInDto;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    categoryInDto = new CategoryInDto();
    categoryInDto.setRestaurantId(1);
    categoryInDto.setName("Italian");

    categoryOutDto = new CategoryOutDto();
    categoryOutDto.setId(1);
    categoryOutDto.setName("Italian");

    categoryUpdateInDto = new CategoryUpdateInDto();
    categoryUpdateInDto.setName("Updated Italian");
  }

  @Test
  void testGetCategoryById() {
    // Mock the service call
    when(categoryService.getCategory(anyInt())).thenReturn(categoryOutDto);
    ResponseEntity<?> response = categoryController.getCategoryById(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(categoryOutDto, response.getBody());
  }

  @Test
  void testGetAllCategory() {
    List<CategoryOutDto> categoryOutDtoList = Arrays.asList(categoryOutDto);
    when(categoryService.getAllCategory(anyInt())).thenReturn(categoryOutDtoList);
    ResponseEntity<?> response = categoryController.getAllCategory(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(categoryOutDtoList, response.getBody());
  }

  @Test
  void testAddCategory() {
    when(categoryService.addCategory(any(CategoryInDto.class))).thenReturn(categoryOutDto);
    ResponseEntity<?> response = categoryController.addCategory(categoryInDto);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(new ApiResponse(Constant.CATEGORY_CREATED_SUCCESS), response.getBody());
  }

  @Test
  void testAddCategoryBadRequest() {
    when(categoryService.addCategory(any(CategoryInDto.class))).thenReturn(null);
    ResponseEntity<?> response = categoryController.addCategory(categoryInDto);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  void testUpdateCategory() {
    when(categoryService.updateCategory(anyInt(), any(CategoryUpdateInDto.class)))
      .thenReturn(categoryOutDto); // Ensure this matches your actual service implementation
    ResponseEntity<?> response = categoryController.updateCategory(1, categoryUpdateInDto);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(new ApiResponse(Constant.CATEGORY_UPDATED_SUCCESS), response.getBody());
  }
}
