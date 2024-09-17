package com.orders.dto.outdto;

import com.orders.dto.RestaurantOutDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantOutDtoTest {

  @Test
  public void testGetterAndSetter() {
    RestaurantOutDto dto = new RestaurantOutDto();

    // Test id
    assertNull(dto.getId());
    Integer id = 123;
    dto.setId(id);
    assertEquals(id, dto.getId());

    // Test userId
    assertNull(dto.getUserId());
    Integer userId = 456;
    dto.setUserId(userId);
    assertEquals(userId, dto.getUserId());

    // Test restaurantName
    assertNull(dto.getRestaurantName());
    String name = "Test Restaurant";
    dto.setRestaurantName(name);
    assertEquals(name, dto.getRestaurantName());

    // Test address
    assertNull(dto.getAddress());
    String address = "456 Test Road";
    dto.setAddress(address);
    assertEquals(address, dto.getAddress());

    // Test contactNumber
    assertNull(dto.getContactNumber());
    String contact = "1234567890";
    dto.setContactNumber(contact);
    assertEquals(contact, dto.getContactNumber());

    // Test description
    assertNull(dto.getDescription());
    String description = "Test description";
    dto.setDescription(description);
    assertEquals(description, dto.getDescription());

    // Test imageData
    assertNull(dto.getImageData());
    byte[] image = {9, 8, 7};
    dto.setImageData(image);
    assertArrayEquals(image, dto.getImageData());
  }

  @Test
  public void testToString() {
    RestaurantOutDto dto = new RestaurantOutDto();
    dto.setId(123);
    dto.setUserId(456);
    dto.setRestaurantName("Test Restaurant");
    dto.setAddress("456 Test Road");
    dto.setContactNumber("1234567890");
    dto.setDescription("Test description");
    dto.setImageData(new byte[]{9, 8, 7});

    String expectedToString = "RestaurantOutDto(id=123, userId=456, restaurantName=Test Restaurant, " +
      "address=456 Test Road, contactNumber=1234567890, " +
      "description=Test description, imageData=" + Arrays.toString(new byte[]{9, 8, 7}) + ")";
    assertEquals(expectedToString, dto.toString());
  }

  @Test
  public void testEqualsAndHashCode() {
    RestaurantOutDto dto1 = buildRestaurantOutDto(123, 456,
      "Test Restaurant", "456 Test Road", "1234567890", "Test description", new byte[]{9, 8, 7});
    RestaurantOutDto dto2 = buildRestaurantOutDto(123, 456,
      "Test Restaurant", "456 Test Road", "1234567890", "Test description", new byte[]{9, 8, 7});

    assertEquals(dto1, dto2);
    assertEquals(dto1.hashCode(), dto2.hashCode());

    dto2.setId(789);
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    dto2 = buildRestaurantOutDto(123, 456,
      "Test Restaurant", "456 Test Road",
      "1234567890", "Different description", new byte[]{9, 8, 7});
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    dto2 = buildRestaurantOutDto(123, 456,
      "Test Restaurant", "456 Test Road",
      "1234567890", "Test description", new byte[]{6, 5, 4});
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    dto1 = new RestaurantOutDto();
    dto2 = new RestaurantOutDto();
    assertEquals(dto1, dto2);
    assertEquals(dto1.hashCode(), dto2.hashCode());
  }

  private RestaurantOutDto buildRestaurantOutDto(Integer id, Integer userId, String name,
                                                 String address, String contact, String description, byte[] image) {
    RestaurantOutDto dto = new RestaurantOutDto();
    dto.setId(id);
    dto.setUserId(userId);
    dto.setRestaurantName(name);
    dto.setAddress(address);
    dto.setContactNumber(contact);
    dto.setDescription(description);
    dto.setImageData(image);
    return dto;
  }
}
