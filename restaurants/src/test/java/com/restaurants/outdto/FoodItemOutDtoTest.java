package com.restaurants.outdto;

import com.restaurants.dto.FoodItemOutDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FoodItemOutDtoTest {

  @Test
  public void testGetterAndSetter() {
    FoodItemOutDto dto = new FoodItemOutDto();

    assertNull(dto.getId());
    Integer id = 100;
    dto.setId(id);
    assertEquals(id, dto.getId());

    assertNull(dto.getFoodName());
    String foodName = "Sample Food";
    dto.setFoodName(foodName);
    assertEquals(foodName, dto.getFoodName());

    assertNull(dto.getRestaurantName());
    String restaurantName = "Sample Restaurant";
    dto.setRestaurantName(restaurantName);
    assertEquals(restaurantName, dto.getRestaurantName());

    assertNull(dto.getDescription());
    String description = "Sample description";
    dto.setDescription(description);
    assertEquals(description, dto.getDescription());

    assertNull(dto.getCategoryName());
    String categoryName = "Sample Category";
    dto.setCategoryName(categoryName);
    assertEquals(categoryName, dto.getCategoryName());

    assertNull(dto.getIsAvailable());
    Boolean isAvailable = false;
    dto.setIsAvailable(isAvailable);
    assertEquals(isAvailable, dto.getIsAvailable());

    assertNull(dto.getPrice());
    Double price = 9.99;
    dto.setPrice(price);
    assertEquals(price, dto.getPrice());

    assertNull(dto.getImageData());
    byte[] imageData = new byte[] {9, 8, 7};
    dto.setImageData(imageData);
    assertArrayEquals(imageData, dto.getImageData());

    assertNull(dto.getCategoryId());
    Integer categoryId = 999;
    dto.setCategoryId(categoryId);
    assertEquals(categoryId, dto.getCategoryId());

    assertNull(dto.getRestaurantId());
    Integer restaurantId = 888;
    dto.setRestaurantId(restaurantId);
    assertEquals(restaurantId, dto.getRestaurantId());
  }

  @Test
  public void testEqualsAndHashCode() {
    FoodItemOutDto dto1 = buildFoodItemOutDto(100, "Sample Food", "Sample Restaurant", "Sample description",
      "Sample Category", false, 9.99, new byte[] {9, 8, 7}, 999, 888);

    assertEquals(dto1, dto1);
    assertEquals(dto1.hashCode(), dto1.hashCode());

    assertNotEquals(dto1, new Object());

    FoodItemOutDto dto2 = buildFoodItemOutDto(100, "Sample Food", "Sample Restaurant", "Sample description",
      "Sample Category", false, 9.99, new byte[] {9, 8, 7}, 999, 888);
    assertEquals(dto1, dto2);
    assertEquals(dto1.hashCode(), dto2.hashCode());

    dto2.setFoodName("Another Food");
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    dto2 = buildFoodItemOutDto(100, "Sample Food", "Sample Restaurant", "Sample description",
      "Sample Category", false, 9.99, new byte[] {9, 8, 7}, 999, 889);
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    dto2 = buildFoodItemOutDto(100, "Sample Food", "Sample Restaurant", "Sample description",
      "Sample Category", false, 9.99, new byte[] {9, 8, 8}, 999, 888);
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    dto1 = new FoodItemOutDto();
    dto2 = new FoodItemOutDto();
    assertEquals(dto1, dto2);
    assertEquals(dto1.hashCode(), dto2.hashCode());
  }

  private FoodItemOutDto buildFoodItemOutDto(Integer id, String foodName, String restaurantName,
                                             String description, String categoryName, Boolean isAvailable, Double price,
                                             byte[] imageData,
                                             Integer categoryId, Integer restaurantId) {
    FoodItemOutDto dto = new FoodItemOutDto();
    dto.setId(id);
    dto.setFoodName(foodName);
    dto.setRestaurantName(restaurantName);
    dto.setDescription(description);
    dto.setCategoryName(categoryName);
    dto.setIsAvailable(isAvailable);
    dto.setPrice(price);
    dto.setImageData(imageData);
    dto.setCategoryId(categoryId);
    dto.setRestaurantId(restaurantId);
    return dto;
  }
}
