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
    Integer id = 1;
    dto.setId(id);
    assertEquals(id, dto.getId());

    // Test userId
    assertNull(dto.getUserId());
    Integer userId = 2;
    dto.setUserId(userId);
    assertEquals(userId, dto.getUserId());

    // Test restaurantName
    assertNull(dto.getRestaurantName());
    String name = "Rajasthan Delight";
    dto.setRestaurantName(name);
    assertEquals(name, dto.getRestaurantName());

    // Test address
    assertNull(dto.getAddress());
    String address = "123 Jaipur Road";
    dto.setAddress(address);
    assertEquals(address, dto.getAddress());

    // Test contactNumber
    assertNull(dto.getContactNumber());
    String contact = "9876543210";
    dto.setContactNumber(contact);
    assertEquals(contact, dto.getContactNumber());

    // Test description
    assertNull(dto.getDescription());
    String description = "Authentic Rajasthani cuisine";
    dto.setDescription(description);
    assertEquals(description, dto.getDescription());

    // Test imageData
    assertNull(dto.getImageData());
    byte[] image = {1, 2, 3};
    dto.setImageData(image);
    assertArrayEquals(image, dto.getImageData());
  }

  @Test
  public void testToString() {
    RestaurantOutDto dto = new RestaurantOutDto();
    dto.setId(1);
    dto.setUserId(2);
    dto.setRestaurantName("Rajasthan Delight");
    dto.setAddress("123 Jaipur Road");
    dto.setContactNumber("9876543210");
    dto.setDescription("Authentic Rajasthani cuisine");
    dto.setImageData(new byte[]{1, 2, 3});

    String expectedToString = "RestaurantOutDto(id=1, userId=2, restaurantName=Rajasthan Delight, " +
      "address=123 Jaipur Road, contactNumber=9876543210, " +
      "description=Authentic Rajasthani cuisine, imageData=" + Arrays.toString(new byte[]{1, 2, 3}) + ")";
    assertEquals(expectedToString, dto.toString());
  }

  @Test
  public void testEqualsAndHashCode() {
    RestaurantOutDto dto1 = buildRestaurantOutDto(1, 2,
      "Rajasthan Delight", "123 Jaipur Road", "9876543210", "Authentic Rajasthani cuisine", new byte[]{1, 2, 3});
    RestaurantOutDto dto2 = buildRestaurantOutDto(1, 2,
      "Rajasthan Delight", "123 Jaipur Road", "9876543210", "Authentic Rajasthani cuisine", new byte[]{1, 2, 3});

    assertEquals(dto1, dto2);
    assertEquals(dto1.hashCode(), dto2.hashCode());

    dto2.setId(2);
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    dto2 = buildRestaurantOutDto(1, 2,
      "Rajasthan Delight", "123 Jaipur Road",
      "9876543210", "Different description", new byte[]{1, 2, 3});
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    dto2 = buildRestaurantOutDto(1, 2,
      "Rajasthan Delight", "123 Jaipur Road",
      "9876543210", "Authentic Rajasthani cuisine", new byte[]{4, 5, 6});
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
