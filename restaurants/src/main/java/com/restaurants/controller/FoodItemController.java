package com.restaurants.controller;

import com.restaurants.indto.FoodItemInDto;
import com.restaurants.outdto.FoodItemOutDto;
import com.restaurants.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/foodItem")
public class FoodItemController {

  @Autowired
  private FoodItemService foodItemService;

  @GetMapping("/getAll/{restaurantId}")
  public ResponseEntity<?> getAllFoodItem(@PathVariable Integer restaurantId) {
    List<FoodItemOutDto> foodItemOutDtoList = foodItemService.getAll(restaurantId);
    return new ResponseEntity<>(foodItemOutDtoList, HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<?> addFoodItem(@RequestBody FoodItemInDto foodItemInDto) {
    FoodItemOutDto foodItemOutDto = foodItemService.add(foodItemInDto);
    return new ResponseEntity<>(foodItemOutDto, HttpStatus.CREATED);
  }
}
