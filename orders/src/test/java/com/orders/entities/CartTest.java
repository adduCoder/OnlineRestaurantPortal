package com.orders.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CartTest {

  @Test
  public void testIdGetterAndSetter() {
    Cart cart = new Cart();
    Integer expectedId = 1;
    cart.setId(expectedId);
    Integer actualId = cart.getId();
    assertEquals(expectedId, actualId, "The id should be equal to the expected value.");
  }

  @Test
  public void testUserIdGetterAndSetter() {
    Cart cart = new Cart();
    Integer expectedUserId = 2;
    cart.setUserId(expectedUserId);
    Integer actualUserId = cart.getUserId();
    assertEquals(expectedUserId, actualUserId, "The userId should be equal to the expected value.");
  }

  @Test
  public void testRestaurantIdGetterAndSetter() {
    Cart cart = new Cart();
    Integer expectedRestaurantId = 3;
    cart.setRestaurantId(expectedRestaurantId);
    Integer actualRestaurantId = cart.getRestaurantId();
    assertEquals(expectedRestaurantId, actualRestaurantId, "The restaurantId should be equal to the expected value.");
  }

  @Test
  public void testFoodItemIdGetterAndSetter() {
    Cart cart = new Cart();
    Integer expectedFoodItemId = 4;
    cart.setFoodItemId(expectedFoodItemId);
    Integer actualFoodItemId = cart.getFoodItemId();
    assertEquals(expectedFoodItemId, actualFoodItemId, "The foodItemId should be equal to the expected value.");
  }

  @Test
  public void testQuantityGetterAndSetter() {
    Cart cart = new Cart();
    Integer expectedQuantity = 5;
    cart.setQuantity(expectedQuantity);
    Integer actualQuantity = cart.getQuantity();
    assertEquals(expectedQuantity, actualQuantity, "The quantity should be equal to the expected value.");
  }

  @Test
  public void testPriceGetterAndSetter() {
    Cart cart = new Cart();
    Double expectedPrice = 99.99;
    cart.setPrice(expectedPrice);
    Double actualPrice = cart.getPrice();
    assertEquals(expectedPrice, actualPrice, "The price should be equal to the expected value.");
  }

  @Test
  public void testEquals() {
    Cart cart1 = new Cart();
    cart1.setId(1);
    cart1.setUserId(2);
    cart1.setRestaurantId(3);
    cart1.setFoodItemId(4);
    cart1.setQuantity(5);
    cart1.setPrice(99.99);

    Cart cart2 = new Cart();
    cart2.setId(1);
    cart2.setUserId(2);
    cart2.setRestaurantId(3);
    cart2.setFoodItemId(4);
    cart2.setQuantity(5);
    cart2.setPrice(99.99);

    assertEquals(cart1, cart2, "The Cart objects should be equal.");

    cart2.setPrice(100.00);
    assertNotEquals(cart1, cart2, "The Cart objects should not be equal.");
  }

  @Test
  public void testHashCode() {
    Cart cart1 = new Cart();
    cart1.setId(1);
    cart1.setUserId(2);
    cart1.setRestaurantId(3);
    cart1.setFoodItemId(4);
    cart1.setQuantity(5);
    cart1.setPrice(99.99);

    Cart cart2 = new Cart();
    cart2.setId(1);
    cart2.setUserId(2);
    cart2.setRestaurantId(3);
    cart2.setFoodItemId(4);
    cart2.setQuantity(5);
    cart2.setPrice(99.99);

    assertEquals(cart1.hashCode(), cart2.hashCode(), "The hashCodes should be equal.");

    cart2.setPrice(100.00);
    assertNotEquals(cart1.hashCode(), cart2.hashCode(), "The hashCodes should not be equal.");
  }

  @Test
  public void testToString() {
    Cart cart = new Cart();
    cart.setId(1);
    cart.setUserId(2);
    cart.setRestaurantId(3);
    cart.setFoodItemId(4);
    cart.setQuantity(5);
    cart.setPrice(99.99);

    String expectedToString = "Cart(id=1, userId=2, restaurantId=3, foodItemId=4, quantity=5, price=99.99)";
    assertEquals(expectedToString, cart.toString(), "The toString method should return the expected string.");
  }
}
