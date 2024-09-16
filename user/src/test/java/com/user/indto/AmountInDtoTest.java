package com.user.indto;

import com.user.dto.AmountInDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AmountInDtoTest {

  private AmountInDto amountInDto;

  @BeforeEach
  public void setUp() {
    // Initialize a valid AmountInDto object
    amountInDto = new AmountInDto();
  }

  @Test
  public void testInitialState() {
    assertNull(amountInDto.getMoney(), "Initial money should be null");
  }

  @Test
  public void testSetAndGetMoney() {
    Double amount = 100.50;
    amountInDto.setMoney(amount);
    assertEquals(amount, amountInDto.getMoney(), "Money value should match the set value");
  }

  @Test
  public void testToString() {
    amountInDto.setMoney(200.75);
    String expectedToString = "AmountInDto(money=200.75)";
    assertEquals(expectedToString, amountInDto.toString());
  }

  @Test
  public void testEqualsAndHashCode() {
    AmountInDto anotherAmountInDto = new AmountInDto();
    anotherAmountInDto.setMoney(150.25);

    AmountInDto sameAmountInDto = new AmountInDto();
    sameAmountInDto.setMoney(150.25);

    // Test equality with another object having the same value
    assertEquals(sameAmountInDto, anotherAmountInDto);
    assertEquals(sameAmountInDto.hashCode(), anotherAmountInDto.hashCode());

    // Test equality with different values
    anotherAmountInDto.setMoney(100.00);
    assertNotEquals(sameAmountInDto, anotherAmountInDto);
    assertNotEquals(sameAmountInDto.hashCode(), anotherAmountInDto.hashCode());

    // Test equality with a different class
    assertNotEquals(sameAmountInDto, new Object());
  }
}
