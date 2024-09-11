package com.orders.dto.indto;

import com.orders.dto.AllCartInDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AllCartInDtoTest {

  @Test
  void testGettersAndSetters() {
    // Create an instance of AllCartInDto
    AllCartInDto allCartInDto = new AllCartInDto();

    // Set values
    allCartInDto.setRestaurantId(1);
    allCartInDto.setUserId(2);

    // Get values and verify
    assertEquals(1, allCartInDto.getRestaurantId());
    assertEquals(2, allCartInDto.getUserId());
  }

  @Test
  void testEqualsAndHashCode() {
    // Create multiple instances of AllCartInDto
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

    // Reflexive property: object must be equal to itself
    assertEquals(dto1, dto1);

    // Symmetric property: if dto1 equals dto2, then dto2 must equal dto1
    assertEquals(dto1, dto2);
    assertEquals(dto2, dto1);

    // Transitive property: if dto1 equals dto2 and dto2 equals dto3, then dto1 must equal dto3
    assertEquals(dto1, dto2);
    assertEquals(dto2, dto3);
    assertEquals(dto1, dto3);

    // Consistent: calling equals multiple times should return the same result
    assertEquals(dto1, dto2);
    assertEquals(dto1, dto2);

    // Null comparison: object should not be equal to null
    assertNotEquals(dto1, null);

    // Different values: dto1 and dto4 are not equal
    assertNotEquals(dto1, dto4);

    // Test hashCode: equal objects must have the same hashCode
    assertEquals(dto1.hashCode(), dto2.hashCode());
    assertEquals(dto2.hashCode(), dto3.hashCode());

    // Different values should have different hash codes
    assertNotEquals(dto1.hashCode(), dto4.hashCode());
  }

  @Test
  void testToString() {
    // Create an instance of AllCartInDto
    AllCartInDto allCartInDto = new AllCartInDto();
    allCartInDto.setRestaurantId(1);
    allCartInDto.setUserId(2);

    // Expected toString output based on Lombok-generated format
    String expectedToString = "AllCartInDto(restaurantId=1, userId=2)";

    // Test toString
    assertEquals(expectedToString, allCartInDto.toString());
  }
}
