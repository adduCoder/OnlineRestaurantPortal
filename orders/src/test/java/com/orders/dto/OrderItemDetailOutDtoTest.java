package com.orders.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OrderItemDetailOutDtoTest {

  @Test
  public void testFoodItemIdGetterAndSetter() {
    OrderItemDetailOutDto orderItemDetailOutDto = new OrderItemDetailOutDto();
    Integer expectedFoodItemId = 101;
    orderItemDetailOutDto.setFoodItemId(expectedFoodItemId);
    Integer actualFoodItemId = orderItemDetailOutDto.getFoodItemId();
    assertEquals(expectedFoodItemId, actualFoodItemId, "The foodItemId should be equal to the expected value.");
  }

  @Test
  public void testFoodItemNameGetterAndSetter() {
    OrderItemDetailOutDto orderItemDetailOutDto = new OrderItemDetailOutDto();
    String expectedFoodItemName = "Pizza";
    orderItemDetailOutDto.setFoodItemName(expectedFoodItemName);
    String actualFoodItemName = orderItemDetailOutDto.getFoodItemName();
    assertEquals(expectedFoodItemName, actualFoodItemName, "The foodItemName should be equal to the expected value.");
  }

  @Test
  public void testQuantityGetterAndSetter() {
    OrderItemDetailOutDto orderItemDetailOutDto = new OrderItemDetailOutDto();
    Integer expectedQuantity = 2;
    orderItemDetailOutDto.setQuantity(expectedQuantity);
    Integer actualQuantity = orderItemDetailOutDto.getQuantity();
    assertEquals(expectedQuantity, actualQuantity, "The quantity should be equal to the expected value.");
  }

  @Test
  public void testPriceGetterAndSetter() {
    OrderItemDetailOutDto orderItemDetailOutDto = new OrderItemDetailOutDto();
    Double expectedPrice = 29.99;
    orderItemDetailOutDto.setPrice(expectedPrice);
    Double actualPrice = orderItemDetailOutDto.getPrice();
    assertEquals(expectedPrice, actualPrice, "The price should be equal to the expected value.");
  }

  @Test
  public void testEquals() {
    OrderItemDetailOutDto orderItemDetailOutDto1 = new OrderItemDetailOutDto();
    orderItemDetailOutDto1.setFoodItemId(101);
    orderItemDetailOutDto1.setFoodItemName("Pizza");
    orderItemDetailOutDto1.setQuantity(2);
    orderItemDetailOutDto1.setPrice(29.99);

    OrderItemDetailOutDto orderItemDetailOutDto2 = new OrderItemDetailOutDto();
    orderItemDetailOutDto2.setFoodItemId(101);
    orderItemDetailOutDto2.setFoodItemName("Pizza");
    orderItemDetailOutDto2.setQuantity(2);
    orderItemDetailOutDto2.setPrice(29.99);

    assertEquals(orderItemDetailOutDto1, orderItemDetailOutDto2, "The OrderItemDetailOutDto objects should be equal.");

    orderItemDetailOutDto2.setPrice(19.99);
    assertNotEquals(orderItemDetailOutDto1, orderItemDetailOutDto2, "The OrderItemDetailOutDto objects should not be equal.");
  }

  @Test
  public void testHashCode() {
    OrderItemDetailOutDto orderItemDetailOutDto1 = new OrderItemDetailOutDto();
    orderItemDetailOutDto1.setFoodItemId(101);
    orderItemDetailOutDto1.setFoodItemName("Pizza");
    orderItemDetailOutDto1.setQuantity(2);
    orderItemDetailOutDto1.setPrice(29.99);

    OrderItemDetailOutDto orderItemDetailOutDto2 = new OrderItemDetailOutDto();
    orderItemDetailOutDto2.setFoodItemId(101);
    orderItemDetailOutDto2.setFoodItemName("Pizza");
    orderItemDetailOutDto2.setQuantity(2);
    orderItemDetailOutDto2.setPrice(29.99);

    assertEquals(orderItemDetailOutDto1.hashCode(), orderItemDetailOutDto2.hashCode(), "The hashCodes should be equal.");

    orderItemDetailOutDto2.setPrice(19.99);
    assertNotEquals(orderItemDetailOutDto1.hashCode(), orderItemDetailOutDto2.hashCode(), "The hashCodes should not be equal.");
  }

  @Test
  public void testToString() {
    OrderItemDetailOutDto orderItemDetailOutDto = new OrderItemDetailOutDto();
    orderItemDetailOutDto.setFoodItemId(101);
    orderItemDetailOutDto.setFoodItemName("Pizza");
    orderItemDetailOutDto.setQuantity(2);
    orderItemDetailOutDto.setPrice(29.99);

    String expectedToString = "OrderItemDetailOutDto(foodItemId=101, foodItemName=Pizza, quantity=2, price=29.99)";
    assertEquals(expectedToString, orderItemDetailOutDto.toString(), "The toString method should return the expected string.");
  }

}
