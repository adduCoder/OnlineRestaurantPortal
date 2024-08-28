package com.restaurants.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class FoodItem
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "food_name" , nullable = false)
  private String foodName;

  @Column(name = "restaurant_id" , nullable = false)
  private Integer restaurantId;

  @Column(name = "description" , nullable = false)
  private String description;

  @Column(name = "category_id", nullable = false)
  private Integer categoryId;

  @Column(name = "is_available" , nullable = false)
  private Boolean isAvailable;

  @Column(name = "price" , nullable = false)
  private Double price;
}
