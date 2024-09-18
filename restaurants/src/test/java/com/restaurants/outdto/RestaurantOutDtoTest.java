package com.restaurants.outdto;

import com.restaurants.dto.RestaurantOutDto;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantOutDtoTest {

  @Test
  void testGettersAndSetters() {
    RestaurantOutDto dto = new RestaurantOutDto();


    dto.setId(1);
    dto.setUserId(1001);
    dto.setRestaurantName("Sample Restaurant");
    dto.setAddress("456 Elm Street");
    dto.setContactNumber("987-654-3210");
    dto.setDescription("Sample description of the restaurant.");
    dto.setImageData(new byte[]{10, 20, 30, 40, 50});


    assertThat(dto.getId()).isEqualTo(1);
    assertThat(dto.getUserId()).isEqualTo(1001);
    assertThat(dto.getRestaurantName()).isEqualTo("Sample Restaurant");
    assertThat(dto.getAddress()).isEqualTo("456 Elm Street");
    assertThat(dto.getContactNumber()).isEqualTo("987-654-3210");
    assertThat(dto.getDescription()).isEqualTo("Sample description of the restaurant.");
    assertThat(dto.getImageData()).isEqualTo(new byte[]{10, 20, 30, 40, 50});
  }

  @Test
  void testToString() {
    RestaurantOutDto dto = new RestaurantOutDto();
    dto.setId(1);
    dto.setUserId(1001);
    dto.setRestaurantName("Sample Restaurant");
    dto.setAddress("456 Elm Street");
    dto.setContactNumber("987-654-3210");
    dto.setDescription("Sample description of the restaurant.");
    dto.setImageData(new byte[]{10, 20, 30, 40, 50});

    String expectedString = "RestaurantOutDto(id=1, userId=1001, restaurantName=Sample Restaurant, address=456 Elm Street, " +
      "contactNumber=987-654-3210, description=Sample description of the restaurant., imageData=[10, 20, 30, 40, 50])";
    assertThat(dto.toString()).isEqualTo(expectedString);
  }

  @Test
  void testEqualsAndHashCode() {
    RestaurantOutDto dto1 = new RestaurantOutDto();
    dto1.setId(1);
    dto1.setUserId(1001);
    dto1.setRestaurantName("Sample Restaurant");
    dto1.setAddress("456 Elm Street");
    dto1.setContactNumber("987-654-3210");
    dto1.setDescription("Sample description of the restaurant.");
    dto1.setImageData(new byte[]{10, 20, 30, 40, 50});

    RestaurantOutDto dto2 = new RestaurantOutDto();
    dto2.setId(1);
    dto2.setUserId(1001);
    dto2.setRestaurantName("Sample Restaurant");
    dto2.setAddress("456 Elm Street");
    dto2.setContactNumber("987-654-3210");
    dto2.setDescription("Sample description of the restaurant.");
    dto2.setImageData(new byte[]{10, 20, 30, 40, 50});

    assertThat(dto1).isEqualTo(dto2);
    assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());

    dto2.setRestaurantName("Another Restaurant");
    assertThat(dto1).isNotEqualTo(dto2);
    assertThat(dto1.hashCode()).isNotEqualTo(dto2.hashCode());
  }
}
