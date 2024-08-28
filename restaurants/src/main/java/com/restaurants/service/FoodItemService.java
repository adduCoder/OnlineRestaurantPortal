package com.restaurants.service;

import com.restaurants.dtoconversion.DtoConversion;
import com.restaurants.entities.FoodItem;
import com.restaurants.indto.FoodItemInDto;
import com.restaurants.outdto.FoodItemOutDto;
import com.restaurants.repository.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodItemService {

  @Autowired
  private FoodItemRepo foodItemRepo;

  public FoodItemOutDto add(FoodItemInDto foodItemInDto) {
    FoodItem foodItem= DtoConversion.mapToFoodItem(foodItemInDto);
    foodItemRepo.save(foodItem);
    return DtoConversion.mapToFoodItemOutDto(foodItem);
  }
}
