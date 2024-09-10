package com.orders.dto.indto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AmountInDtoTest {

  @Test
  void testGettersAndSetters() {
    // Create an instance of AmountInDto
    AmountInDto amountInDto = new AmountInDto();

    // Set value
    amountInDto.setMoney(100.50);

    // Get value and verify
    assertEquals(100.50, amountInDto.getMoney());
  }

  @Test
  void testEqualsAndHashCode() {
    // Create two instances of AmountInDto
    AmountInDto dto1 = new AmountInDto();
    dto1.setMoney(100.50);

    AmountInDto dto2 = new AmountInDto();
    dto2.setMoney(100.50);

    AmountInDto dto3 = new AmountInDto();
    dto3.setMoney(200.75);

    // Test equals and hashCode
    assertEquals(dto1, dto2); // Same value should be equal
    assertEquals(dto1.hashCode(), dto2.hashCode()); // Same value should have the same hash code

    assertNotEquals(dto1, dto3); // Different value should not be equal
    assertNotEquals(dto1.hashCode(), dto3.hashCode()); // Different value should have different hash codes
  }

  @Test
  void testToString() {
    // Create an instance of AmountInDto
    AmountInDto amountInDto = new AmountInDto();
    amountInDto.setMoney(100.50);

    // Expected toString output
    String expectedToString = "AmountInDto(money=100.5)"; // Adjust according to actual toString format

    // Test toString
    assertEquals(expectedToString, amountInDto.toString());
  }
}
