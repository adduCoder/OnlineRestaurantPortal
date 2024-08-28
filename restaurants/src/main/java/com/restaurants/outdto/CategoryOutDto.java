package com.restaurants.outdto;

import lombok.Data;

@Data
public class CategoryOutDto {
  private Integer id;
  private Integer resturantId;
  private String name;
}
