package com.orders.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OrderStatusTest {

  @Test
  public void testEnumValues() {
    // Check if the enum values are correct
    assertEquals(OrderStatus.CONFIRMED, OrderStatus.valueOf("CONFIRMED"));
    assertEquals(OrderStatus.CANCELLED, OrderStatus.valueOf("CANCELLED"));
    assertEquals(OrderStatus.PENDING, OrderStatus.valueOf("PENDING"));
  }

  @Test
  public void testEnumNames() {
    // Check if the names of the enum values are correct
    assertEquals("CONFIRMED", OrderStatus.CONFIRMED.name());
    assertEquals("CANCELLED", OrderStatus.CANCELLED.name());
    assertEquals("PENDING", OrderStatus.PENDING.name());
  }

  @Test
  public void testEnumValuesAreUnique() {
    // Ensure that enum values are unique
    assertNotEquals(OrderStatus.CONFIRMED, OrderStatus.CANCELLED);
    assertNotEquals(OrderStatus.CANCELLED, OrderStatus.PENDING);
    assertNotEquals(OrderStatus.PENDING, OrderStatus.CONFIRMED);
  }

  @Test
  public void testToString() {
    // Check if toString() method returns the expected string
    assertEquals("CONFIRMED", OrderStatus.CONFIRMED.toString());
    assertEquals("CANCELLED", OrderStatus.CANCELLED.toString());
    assertEquals("PENDING", OrderStatus.PENDING.toString());
  }
}
