package com.restaurants.controller;


import com.restaurants.indto.RestaurantInDto;
import com.restaurants.outdto.RestaurantOutDto;
import com.restaurants.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

  @Autowired
  private RestaurantService restaurantService;

  @PostMapping("/add")
  public ResponseEntity<?> addRestaurant(@Valid @RequestBody RestaurantInDto restaurantInDto) {
    RestaurantOutDto restaurantOutDto = restaurantService.addRestaurant(restaurantInDto);
    if (restaurantOutDto == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(restaurantOutDto, HttpStatus.CREATED);
  }

  @GetMapping("/get/{userId}")
  public ResponseEntity<?> getAllRestaurant(@PathVariable Integer userId) {
    List<RestaurantOutDto> restaurantOutDtoList = restaurantService.getAll(userId);
    return new ResponseEntity<>(restaurantOutDtoList, HttpStatus.OK);
  }
}
