package com.orders.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AmountInDtoTest {

  @Test
  void testGettersAndSetters() {
    AmountInDto amountInDto = new AmountInDto();

    amountInDto.setMoney(100.50);

    assertEquals(100.50, amountInDto.getMoney());
  }

  @Test
  void testEqualsAndHashCode() {
    AmountInDto dto1 = new AmountInDto();
    dto1.setMoney(100.50);

    AmountInDto dto2 = new AmountInDto();
    dto2.setMoney(100.50);

    AmountInDto dto3 = new AmountInDto();
    dto3.setMoney(200.75);

    assertEquals(dto1, dto2);
    assertEquals(dto1.hashCode(), dto2.hashCode());

    assertNotEquals(dto1, dto3);
    assertNotEquals(dto1.hashCode(), dto3.hashCode());
  }

  @Test
  void testToString() {
    AmountInDto amountInDto = new AmountInDto();
    amountInDto.setMoney(100.50);

    String expectedToString = "AmountInDto(money=100.5)";

    assertEquals(expectedToString, amountInDto.toString());
  }
}
