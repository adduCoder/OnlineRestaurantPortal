package com.restaurants.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Restaurant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "restaurant_id")
  private Integer id;

  @Column(name = "user_id", nullable = false)
  private Integer userId;

  @Column(name = "rest_name", nullable = false)
  private String restaurantName;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "contact_number", nullable = false)
  private String contactNumber;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "image_url")
  private String imageUrl;
}
