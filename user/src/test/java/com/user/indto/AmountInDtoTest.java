package com.user.indto;

import com.user.dto.AmountInDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Unit tests for {@link AmountInDto} class.
 * These tests verify the correctness of getter and setter methods,
 * the {@link AmountInDto#toString()} method, and the equals and hashCode methods.
 */
public class AmountInDtoTest {

  /** The {@link AmountInDto} instance used in the tests. */
  private final AmountInDto amountInDto = new AmountInDto();

  /**
   * Sets up a new {@link AmountInDto} instance before each test.
   */
  @BeforeEach
  public void setUp() {
    // Initialize the AmountInDto instance
    amountInDto.setMoney(null);
  }

  /**
   * Tests the initial state of the money field.
   */
  @Test
  public void testInitialState() {
    assertNull(amountInDto.getMoney(), "Initial money should be null");
  }

  /**
   * Tests the setter and getter methods for the money field.
   */
  @Test
  public void testSetAndGetMoney() {
    final Double amount = 123.45; // Placeholder value
    amountInDto.setMoney(amount);
    assertEquals(amount, amountInDto.getMoney(), "Money value should match the set value");
  }

  /**
   * Tests the {@link AmountInDto#toString()} method.
   */
  @Test
  public void testToString() {
    amountInDto.setMoney(678.90); // Placeholder value
    final String expectedToString = "AmountInDto(money=678.9)";
    assertEquals(expectedToString, amountInDto.toString());
  }

  /**
   * Tests the {@link AmountInDto#equals(Object)} and {@link AmountInDto#hashCode()} methods.
   */
  @Test
  public void testEqualsAndHashCode() {
    final AmountInDto anotherAmountInDto = new AmountInDto();
    anotherAmountInDto.setMoney(321.65); // Placeholder value

    final AmountInDto sameAmountInDto = new AmountInDto();
    sameAmountInDto.setMoney(321.65); // Placeholder value

    // Test equality with another object having the same value
    assertEquals(sameAmountInDto, anotherAmountInDto);
    assertEquals(sameAmountInDto.hashCode(), anotherAmountInDto.hashCode());

    // Test equality with different values
    anotherAmountInDto.setMoney(987.65); // Placeholder value
    assertNotEquals(sameAmountInDto, anotherAmountInDto);
    assertNotEquals(sameAmountInDto.hashCode(), anotherAmountInDto.hashCode());

    // Test equality with a different class
    assertNotEquals(sameAmountInDto, new Object());
  }
}
