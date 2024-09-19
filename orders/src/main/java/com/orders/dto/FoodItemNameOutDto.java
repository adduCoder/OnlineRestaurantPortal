package com.orders.dto;


import lombok.Data;

/**
 * Data Transfer Object (DTO) for representing the details of a food item in the response.
 */
@Data
public class FoodItemNameOutDto {

  /**
   * The unique identifier of the food item.
   */
  private Integer id;

  /**
   * The name of the food item.
   */
  private String foodItemName;

  /**
   * The price of the food item.
   */
  private Double price;

  public FoodItemNameOutDto(String foodItemName){
    this.foodItemName = foodItemName;
  }

  public FoodItemNameOutDto(){}
}
