package com.orders.dto.outdto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AddCartResponseTest {

  @Test
  public void testCartIdGetterAndSetter() {
    // Create an instance of AddCartResponse
    AddCartResponse addCartResponse = new AddCartResponse();

    // Set the cartId
    Integer expectedCartId = 123;
    addCartResponse.setCartId(expectedCartId);

    // Get the cartId and verify it matches the expected value
    Integer actualCartId = addCartResponse.getCartId();
    assertEquals(expectedCartId, actualCartId, "The cartId should be equal to the expected value.");
  }

  @Test
  public void testEquals() {
    AddCartResponse addCartResponse1 = new AddCartResponse();
    addCartResponse1.setCartId(123);

    AddCartResponse addCartResponse2 = new AddCartResponse();
    addCartResponse2.setCartId(123);

    assertEquals(addCartResponse1, addCartResponse2, "The AddCartResponse objects should be equal.");

    addCartResponse2.setCartId(456);
    assertNotEquals(addCartResponse1, addCartResponse2, "The AddCartResponse objects should not be equal.");
  }

  @Test
  public void testHashCode() {
    AddCartResponse addCartResponse1 = new AddCartResponse();
    addCartResponse1.setCartId(123);

    AddCartResponse addCartResponse2 = new AddCartResponse();
    addCartResponse2.setCartId(123);

    assertEquals(addCartResponse1.hashCode(), addCartResponse2.hashCode(), "The hashCodes should be equal.");

    addCartResponse2.setCartId(456);
    assertNotEquals(addCartResponse1.hashCode(), addCartResponse2.hashCode(), "The hashCodes should not be equal.");
  }

  @Test
  public void testToString() {
    AddCartResponse addCartResponse = new AddCartResponse();
    addCartResponse.setCartId(123);

    String expectedToString = "AddCartResponse(cartId=123)"; // Adjust this if the format is different
    assertEquals(expectedToString, addCartResponse.toString(), "The toString method should return the expected string.");
  }

}
