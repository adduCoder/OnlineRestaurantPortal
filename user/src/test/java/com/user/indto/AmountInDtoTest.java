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
    amountInDto.setMoney(678.90);
    final String expectedToString = "AmountInDto(money=678.9)";
    assertEquals(expectedToString, amountInDto.toString());
  }

  /**
   * Tests the {@link AmountInDto#equals(Object)} and {@link AmountInDto#hashCode()} methods.
   */
  @Test
  public void testEqualsAndHashCode() {
    final AmountInDto anotherAmountInDto = new AmountInDto();
    anotherAmountInDto.setMoney(321.65);

    final AmountInDto sameAmountInDto = new AmountInDto();
    sameAmountInDto.setMoney(321.65);

    assertEquals(sameAmountInDto, anotherAmountInDto);
    assertEquals(sameAmountInDto.hashCode(), anotherAmountInDto.hashCode());

    anotherAmountInDto.setMoney(987.65);
    assertNotEquals(sameAmountInDto, anotherAmountInDto);
    assertNotEquals(sameAmountInDto.hashCode(), anotherAmountInDto.hashCode());

    assertNotEquals(sameAmountInDto, new Object());
  }
}
