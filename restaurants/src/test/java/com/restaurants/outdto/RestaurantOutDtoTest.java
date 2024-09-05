package com.restaurants.outdto;

import com.restaurants.dto.outdto.RestaurantOutDto;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantOutDtoTest {

  @Test
  void testGettersAndSetters() {
    RestaurantOutDto dto = new RestaurantOutDto();

    // Set values
    dto.setId(1);
    dto.setUserId(101);
    dto.setRestaurantName("Bistro Cafe");
    dto.setAddress("123 Main Street");
    dto.setContactNumber("123-456-7890");
    dto.setDescription("A cozy place for coffee and snacks.");
    dto.setImageData(new byte[]{1, 2, 3, 4, 5});

    // Assert values
    assertThat(dto.getId()).isEqualTo(1);
    assertThat(dto.getUserId()).isEqualTo(101);
    assertThat(dto.getRestaurantName()).isEqualTo("Bistro Cafe");
    assertThat(dto.getAddress()).isEqualTo("123 Main Street");
    assertThat(dto.getContactNumber()).isEqualTo("123-456-7890");
    assertThat(dto.getDescription()).isEqualTo("A cozy place for coffee and snacks.");
    assertThat(dto.getImageData()).isEqualTo(new byte[]{1, 2, 3, 4, 5});
  }

  @Test
  void testToString() {
    RestaurantOutDto dto = new RestaurantOutDto();
    dto.setId(1);
    dto.setUserId(101);
    dto.setRestaurantName("Bistro Cafe");
    dto.setAddress("123 Main Street");
    dto.setContactNumber("123-456-7890");
    dto.setDescription("A cozy place for coffee and snacks.");
    dto.setImageData(new byte[]{1, 2, 3, 4, 5});

    String expectedString = "RestaurantOutDto(id=1, userId=101, restaurantName=Bistro Cafe, address=123 Main Street, " +
      "contactNumber=123-456-7890, description=A cozy place for coffee and snacks., imageData=[1, 2, 3, 4, 5])";
    assertThat(dto.toString()).isEqualTo(expectedString);
  }

  @Test
  void testEqualsAndHashCode() {
    RestaurantOutDto dto1 = new RestaurantOutDto();
    dto1.setId(1);
    dto1.setUserId(101);
    dto1.setRestaurantName("Bistro Cafe");
    dto1.setAddress("123 Main Street");
    dto1.setContactNumber("123-456-7890");
    dto1.setDescription("A cozy place for coffee and snacks.");
    dto1.setImageData(new byte[]{1, 2, 3, 4, 5});

    RestaurantOutDto dto2 = new RestaurantOutDto();
    dto2.setId(1);
    dto2.setUserId(101);
    dto2.setRestaurantName("Bistro Cafe");
    dto2.setAddress("123 Main Street");
    dto2.setContactNumber("123-456-7890");
    dto2.setDescription("A cozy place for coffee and snacks.");
    dto2.setImageData(new byte[]{1, 2, 3, 4, 5});

    // Test equality and hashCode
    assertThat(dto1).isEqualTo(dto2);
    assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());

    // Change one property and test inequality
    dto2.setRestaurantName("Cafe Bistro");
    assertThat(dto1).isNotEqualTo(dto2);
    assertThat(dto1.hashCode()).isNotEqualTo(dto2.hashCode());
  }
}
