package com.restaurants.outdto;
import com.restaurants.dto.outdto.FoodItemOutDto;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FoodItemOutDtoTest {

  @Test
  void testGettersAndSetters() {
    FoodItemOutDto dto = new FoodItemOutDto();
    dto.setId(1);
    dto.setFoodName("Pizza");
    dto.setRestaurantName("Italian Bistro");
    dto.setDescription("Delicious cheese pizza");
    dto.setCategoryName("Pizza");
    dto.setIsAvailable(true);
    dto.setPrice(12.99);
    dto.setImageData(new byte[]{1, 2, 3});

    assertThat(dto.getId()).isEqualTo(1);
    assertThat(dto.getFoodName()).isEqualTo("Pizza");
    assertThat(dto.getRestaurantName()).isEqualTo("Italian Bistro");
    assertThat(dto.getDescription()).isEqualTo("Delicious cheese pizza");
    assertThat(dto.getCategoryName()).isEqualTo("Pizza");
    assertThat(dto.getIsAvailable()).isTrue();
    assertThat(dto.getPrice()).isEqualTo(12.99);
    assertThat(dto.getImageData()).isEqualTo(new byte[]{1, 2, 3});
  }

  @Test
  void testToString() {
    FoodItemOutDto dto = new FoodItemOutDto();
    dto.setId(1);
    dto.setFoodName("Pizza");
    dto.setRestaurantName("Italian Bistro");
    dto.setDescription("Delicious cheese pizza");
    dto.setCategoryName("Pizza");
    dto.setIsAvailable(true);
    dto.setPrice(12.99);
    dto.setImageData(new byte[]{1, 2, 3});

    String expectedString = "FoodItemOutDto(id=1, foodName=Pizza, restaurantName=Italian Bistro, " +
      "description=Delicious cheese pizza, categoryName=Pizza, isAvailable=true, price=12.99, imageData=[1, 2, 3])";
    assertThat(dto.toString()).isEqualTo(expectedString);
  }

  @Test
  void testEqualsAndHashCode() {
    FoodItemOutDto dto1 = new FoodItemOutDto();
    dto1.setId(1);
    dto1.setFoodName("Pizza");
    dto1.setRestaurantName("Italian Bistro");
    dto1.setDescription("Delicious cheese pizza");
    dto1.setCategoryName("Pizza");
    dto1.setIsAvailable(true);
    dto1.setPrice(12.99);
    dto1.setImageData(new byte[]{1, 2, 3});

    FoodItemOutDto dto2 = new FoodItemOutDto();
    dto2.setId(1);
    dto2.setFoodName("Pizza");
    dto2.setRestaurantName("Italian Bistro");
    dto2.setDescription("Delicious cheese pizza");
    dto2.setCategoryName("Pizza");
    dto2.setIsAvailable(true);
    dto2.setPrice(12.99);
    dto2.setImageData(new byte[]{1, 2, 3});

    assertThat(dto1).isEqualTo(dto2);
    assertThat(dto1.hashCode()).isEqualTo(dto2.hashCode());

    dto2.setFoodName("Burger");
    assertThat(dto1).isNotEqualTo(dto2);
    assertThat(dto1.hashCode()).isNotEqualTo(dto2.hashCode());
  }
}
