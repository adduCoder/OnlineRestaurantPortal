package com.orders.dto.indto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CartInDtoTest {

  private CartInDto cartInDto;

  @BeforeEach
  public void setUp() {
    // Initialize CartInDto object with sample values using setters
    cartInDto = new CartInDto();
    cartInDto.setUserId(1);
    cartInDto.setRestaurantId(2);
    cartInDto.setFoodItemId(3);
    cartInDto.setQuantity(4);
    cartInDto.setPrice(5.99);
  }

  @Test
  public void testGetUserId() {
    assertEquals(1, cartInDto.getUserId());
  }

  @Test
  public void testSetUserId() {
    cartInDto.setUserId(2);
    assertEquals(2, cartInDto.getUserId());
  }

  @Test
  public void testGetRestaurantId() {
    assertEquals(2, cartInDto.getRestaurantId());
  }

  @Test
  public void testSetRestaurantId() {
    cartInDto.setRestaurantId(3);
    assertEquals(3, cartInDto.getRestaurantId());
  }

  @Test
  public void testGetFoodItemId() {
    assertEquals(3, cartInDto.getFoodItemId());
  }

  @Test
  public void testSetFoodItemId() {
    cartInDto.setFoodItemId(4);
    assertEquals(4, cartInDto.getFoodItemId());
  }

  @Test
  public void testGetQuantity() {
    assertEquals(4, cartInDto.getQuantity());
  }

  @Test
  public void testSetQuantity() {
    cartInDto.setQuantity(5);
    assertEquals(5, cartInDto.getQuantity());
  }

  @Test
  public void testGetPrice() {
    assertEquals(5.99, cartInDto.getPrice());
  }

  @Test
  public void testSetPrice() {
    cartInDto.setPrice(6.99);
    assertEquals(6.99, cartInDto.getPrice());
  }

  @Test
  public void testToString() {
    // Expected toString output based on Lombok-generated method
    String expected = "CartInDto(userId=1, restaurantId=2, foodItemId=3, quantity=4, price=5.99)";
    assertEquals(expected, cartInDto.toString());
  }

  @Test
  public void testHashCode() {
    CartInDto dto1 = new CartInDto();
    dto1.setUserId(1);
    dto1.setRestaurantId(2);
    dto1.setFoodItemId(3);
    dto1.setQuantity(4);
    dto1.setPrice(5.99);

    CartInDto dto2 = new CartInDto();
    dto2.setUserId(1);
    dto2.setRestaurantId(2);
    dto2.setFoodItemId(3);
    dto2.setQuantity(4);
    dto2.setPrice(5.99);

    assertEquals(dto1.hashCode(), dto2.hashCode());

    dto2.setPrice(6.99);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());
  }

  @Test
  public void testEquals() {
    CartInDto dto1 = new CartInDto();
    dto1.setUserId(1);
    dto1.setRestaurantId(2);
    dto1.setFoodItemId(3);
    dto1.setQuantity(4);
    dto1.setPrice(5.99);

    CartInDto dto2 = new CartInDto();
    dto2.setUserId(1);
    dto2.setRestaurantId(2);
    dto2.setFoodItemId(3);
    dto2.setQuantity(4);
    dto2.setPrice(5.99);

    // Check equality of two identical objects
    assertEquals(dto1, dto2);

    // Modify one object and ensure they are no longer equal
    dto2.setPrice(6.99);
    assertNotEquals(dto1, dto2);

    // Ensure the object equals itself
    assertEquals(dto1, dto1);

    // Check that the object is not equal to null
    assertNotEquals(dto1, null);

    // Check that the object is not equal to an instance of a different class
    assertNotEquals(dto1, new Object());
  }
}
