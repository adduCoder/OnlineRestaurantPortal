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
    Integer id = 1;
    dto.setId(id);
    assertEquals(id, dto.getId());

    assertNull(dto.getFoodName());
    String foodName = "Pizza";
    dto.setFoodName(foodName);
    assertEquals(foodName, dto.getFoodName());

    assertNull(dto.getRestaurantName());
    String restaurantName = "Italian Bistro";
    dto.setRestaurantName(restaurantName);
    assertEquals(restaurantName, dto.getRestaurantName());

    assertNull(dto.getDescription());
    String description = "Delicious cheese pizza";
    dto.setDescription(description);
    assertEquals(description, dto.getDescription());

    assertNull(dto.getCategoryName());
    String categoryName = "Pizza";
    dto.setCategoryName(categoryName);
    assertEquals(categoryName, dto.getCategoryName());

    assertNull(dto.getIsAvailable());
    Boolean isAvailable = true;
    dto.setIsAvailable(isAvailable);
    assertEquals(isAvailable, dto.getIsAvailable());

    assertNull(dto.getPrice());
    Double price = 12.99;
    dto.setPrice(price);
    assertEquals(price, dto.getPrice());

    assertNull(dto.getImageData());
    byte[] imageData = new byte[] {1, 2, 3};
    dto.setImageData(imageData);
    assertArrayEquals(imageData, dto.getImageData());

    assertNull(dto.getCategoryId());
    Integer categoryId = 101;
    dto.setCategoryId(categoryId);
    assertEquals(categoryId, dto.getCategoryId());

    assertNull(dto.getRestaurantId());
    Integer restaurantId = 202;
    dto.setRestaurantId(restaurantId);
    assertEquals(restaurantId, dto.getRestaurantId());
  }
//
//  @Test
//  public void testToString() {
//    FoodItemOutDto dto = new FoodItemOutDto();
//    dto.setId(1);
//    dto.setFoodName("Pizza");
//    dto.setRestaurantName("Italian Bistro");
//    dto.setDescription("Delicious cheese pizza");
//    dto.setCategoryName("Pizza");
//    dto.setIsAvailable(true);
//    dto.setPrice(12.99);
//    dto.setImageData(new byte[]{1, 2, 3});
//    dto.setCategoryId(101);
//    dto.setRestaurantId(202);
//
//    String expectedString = "FoodItemOutDto(id=1, foodName=Pizza, restaurantName=Italian Bistro, " +
//      "description=Delicious cheese pizza, categoryName=Pizza, isAvailable=true, price=12.99, " +
//      "imageData=[1, 2, 3], categoryId=101, restaurantId=202)";
//    assertEquals(expectedString, dto.toString());
//  }

  @Test
  public void testEqualsAndHashCode() {
    FoodItemOutDto dto1 = buildFoodItemOutDto(1, "Pizza", "Italian Bistro", "Delicious cheese pizza",
      "Pizza", true, 12.99, new byte[] {1, 2, 3}, 101, 202);

    assertEquals(dto1, dto1);
    assertEquals(dto1.hashCode(), dto1.hashCode());

    assertNotEquals(dto1, new Object());

    FoodItemOutDto dto2 = buildFoodItemOutDto(1, "Pizza", "Italian Bistro", "Delicious cheese pizza",
      "Pizza", true, 12.99, new byte[] {1, 2, 3}, 101, 202);
    assertEquals(dto1, dto2);
    assertEquals(dto1.hashCode(), dto2.hashCode());

    dto2.setFoodName("Burger");
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    dto2 = buildFoodItemOutDto(1, "Pizza", "Italian Bistro", "Delicious cheese pizza",
      "Pizza", true, 12.99, new byte[] {1, 2, 3}, 101, 203);
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    dto2 = buildFoodItemOutDto(1, "Pizza", "Italian Bistro", "Delicious cheese pizza",
      "Pizza", true, 12.99, new byte[] {1, 2, 4}, 101, 202);
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
