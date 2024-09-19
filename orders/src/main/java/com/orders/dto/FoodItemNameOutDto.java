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

  /**
   * Constructs a new {@code FoodItemNameOutDto} with the specified food item name.
   * <p>
   * This constructor is used to create a DTO with the given food item name. The ID and price fields
   * will be initialized to {@code null} or their default values.
   * </p>
   *
   * @param foodItemName the name of the food item to be set.
   */
  public FoodItemNameOutDto(final String foodItemName) {
    this.foodItemName = foodItemName;
  }

  /**
   * Default constructor for creating a new {@code FoodItemNameOutDto}.
   * <p>
   * This constructor initializes a new instance of the DTO with all fields set to their default values
   * (e.g., {@code null} for objects, {@code 0} for numeric types).
   * </p>
   */
  public FoodItemNameOutDto() { }
}
