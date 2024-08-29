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

@RestController
@RequestMapping("/category")
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @GetMapping("/get/{id}")
  public ResponseEntity<?> getCategoryById(@PathVariable Integer id) {
    CategoryOutDto categoryOutDto = categoryService.getCategory(id);
    return new ResponseEntity<>(categoryOutDto, HttpStatus.OK);
  }

  @GetMapping("/getAll/{restaurantId}")
  public ResponseEntity<?> getAllCategory(@PathVariable Integer restaurantId) {
    List<CategoryOutDto> categoryOutDtoList = categoryService.getAllCategory(restaurantId);
    return new ResponseEntity<>(categoryOutDtoList, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<?> addCategory(@RequestBody CategoryInDto categoryInDto) {
    CategoryOutDto categoryOutDto = categoryService.addCategory(categoryInDto);
    if (categoryOutDto == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(categoryOutDto, HttpStatus.CREATED);
  }

  @PutMapping("/update/{categoryId}")
  public ResponseEntity<?> updateCategory(@PathVariable Integer categoryId, @RequestBody CategoryInDto categoryInDto) {
    CategoryOutDto categoryOutDto = categoryService.updateCategory(categoryId, categoryInDto);
    return new ResponseEntity<>(categoryOutDto, HttpStatus.OK);

  }
}
