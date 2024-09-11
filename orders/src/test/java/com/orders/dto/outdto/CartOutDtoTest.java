package com.orders.dto.outdto;

import com.orders.dto.CartOutDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CartOutDtoTest {

  @Test
  public void testIdGetterAndSetter() {
    CartOutDto cartOutDto = new CartOutDto();
    Integer expectedId = 1;
    cartOutDto.setId(expectedId);
    Integer actualId = cartOutDto.getId();
    assertEquals(expectedId, actualId, "The id should be equal to the expected value.");
  }

  @Test
  public void testUserIdGetterAndSetter() {
    CartOutDto cartOutDto = new CartOutDto();
    Integer expectedUserId = 2;
    cartOutDto.setUserId(expectedUserId);
    Integer actualUserId = cartOutDto.getUserId();
    assertEquals(expectedUserId, actualUserId, "The userId should be equal to the expected value.");
  }

  @Test
  public void testRestaurantIdGetterAndSetter() {
    CartOutDto cartOutDto = new CartOutDto();
    Integer expectedRestaurantId = 3;
    cartOutDto.setRestaurantId(expectedRestaurantId);
    Integer actualRestaurantId = cartOutDto.getRestaurantId();
    assertEquals(expectedRestaurantId, actualRestaurantId, "The restaurantId should be equal to the expected value.");
  }

  @Test
  public void testFoodItemIdGetterAndSetter() {
    CartOutDto cartOutDto = new CartOutDto();
    Integer expectedFoodItemId = 4;
    cartOutDto.setFoodItemId(expectedFoodItemId);
    Integer actualFoodItemId = cartOutDto.getFoodItemId();
    assertEquals(expectedFoodItemId, actualFoodItemId, "The foodItemId should be equal to the expected value.");
  }

  @Test
  public void testQuantityGetterAndSetter() {
    CartOutDto cartOutDto = new CartOutDto();
    Integer expectedQuantity = 5;
    cartOutDto.setQuantity(expectedQuantity);
    Integer actualQuantity = cartOutDto.getQuantity();
    assertEquals(expectedQuantity, actualQuantity, "The quantity should be equal to the expected value.");
  }

  @Test
  public void testPriceGetterAndSetter() {
    CartOutDto cartOutDto = new CartOutDto();
    Double expectedPrice = 99.99;
    cartOutDto.setPrice(expectedPrice);
    Double actualPrice = cartOutDto.getPrice();
    assertEquals(expectedPrice, actualPrice, "The price should be equal to the expected value.");
  }

  @Test
  public void testEquals() {
    CartOutDto cartOutDto1 = new CartOutDto();
    cartOutDto1.setId(1);
    cartOutDto1.setUserId(2);
    cartOutDto1.setRestaurantId(3);
    cartOutDto1.setFoodItemId(4);
    cartOutDto1.setQuantity(5);
    cartOutDto1.setPrice(99.99);

    CartOutDto cartOutDto2 = new CartOutDto();
    cartOutDto2.setId(1);
    cartOutDto2.setUserId(2);
    cartOutDto2.setRestaurantId(3);
    cartOutDto2.setFoodItemId(4);
    cartOutDto2.setQuantity(5);
    cartOutDto2.setPrice(99.99);

    assertEquals(cartOutDto1, cartOutDto2, "The CartOutDto objects should be equal.");

    cartOutDto2.setPrice(89.99);
    assertNotEquals(cartOutDto1, cartOutDto2, "The CartOutDto objects should not be equal.");
  }

  @Test
  public void testToString() {
    CartOutDto cartOutDto = new CartOutDto();
    cartOutDto.setId(1);
    cartOutDto.setUserId(2);
    cartOutDto.setRestaurantId(3);
    cartOutDto.setFoodItemId(4);
    cartOutDto.setQuantity(5);
    cartOutDto.setPrice(99.99);

    String expectedToString = "CartOutDto(id=1, userId=2, restaurantId=3, foodItemId=4, quantity=5, price=99.99)";
    assertEquals(expectedToString, cartOutDto.toString(), "The toString method should return the expected string.");
  }

  @Test
  public void testHashCode() {
    CartOutDto cartOutDto1 = new CartOutDto();
    cartOutDto1.setId(1);
    cartOutDto1.setUserId(2);
    cartOutDto1.setRestaurantId(3);
    cartOutDto1.setFoodItemId(4);
    cartOutDto1.setQuantity(5);
    cartOutDto1.setPrice(99.99);

    CartOutDto cartOutDto2 = new CartOutDto();
    cartOutDto2.setId(1);
    cartOutDto2.setUserId(2);
    cartOutDto2.setRestaurantId(3);
    cartOutDto2.setFoodItemId(4);
    cartOutDto2.setQuantity(5);
    cartOutDto2.setPrice(99.99);

    assertEquals(cartOutDto1.hashCode(), cartOutDto2.hashCode(), "The hashCodes should be equal.");

    cartOutDto2.setPrice(89.99);
    assertNotEquals(cartOutDto1.hashCode(), cartOutDto2.hashCode(), "The hashCodes should not be equal.");
  }

}
