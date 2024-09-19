package com.orders.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AllCartInDtoTest {

  @Test
  void testGettersAndSetters() {
    AllCartInDto allCartInDto = new AllCartInDto();

    allCartInDto.setRestaurantId(1);
    allCartInDto.setUserId(2);

    assertEquals(1, allCartInDto.getRestaurantId());
    assertEquals(2, allCartInDto.getUserId());
  }

  @Test
  void testEqualsAndHashCode() {
    AllCartInDto dto1 = new AllCartInDto();
    dto1.setRestaurantId(1);
    dto1.setUserId(2);

    AllCartInDto dto2 = new AllCartInDto();
    dto2.setRestaurantId(1);
    dto2.setUserId(2);

    AllCartInDto dto3 = new AllCartInDto();
    dto3.setRestaurantId(1);
    dto3.setUserId(2);

    AllCartInDto dto4 = new AllCartInDto();
    dto4.setRestaurantId(3);
    dto4.setUserId(4);

    assertEquals(dto1, dto1);

    assertEquals(dto1, dto2);
    assertEquals(dto2, dto1);

    assertEquals(dto1, dto2);
    assertEquals(dto2, dto3);
    assertEquals(dto1, dto3);

    assertEquals(dto1, dto2);
    assertEquals(dto1, dto2);

    assertNotEquals(dto1, null);

    assertNotEquals(dto1, dto4);

    assertEquals(dto1.hashCode(), dto2.hashCode());
    assertEquals(dto2.hashCode(), dto3.hashCode());

    assertNotEquals(dto1.hashCode(), dto4.hashCode());
  }

  @Test
  void testToString() {
    AllCartInDto allCartInDto = new AllCartInDto();
    allCartInDto.setRestaurantId(1);
    allCartInDto.setUserId(2);

    String expectedToString = "AllCartInDto(restaurantId=1, userId=2)";

    assertEquals(expectedToString, allCartInDto.toString());
  }
}
