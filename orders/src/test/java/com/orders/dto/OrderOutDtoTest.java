package com.orders.dto;

import com.orders.entities.OrderStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OrderOutDtoTest {

  @Test
  public void testIdGetterAndSetter() {
    OrderOutDto orderOutDto = new OrderOutDto();
    Integer expectedId = 1;
    orderOutDto.setId(expectedId);
    Integer actualId = orderOutDto.getId();
    assertEquals(expectedId, actualId, "The id should be equal to the expected value.");
  }

  @Test
  public void testUserIdGetterAndSetter() {
    OrderOutDto orderOutDto = new OrderOutDto();
    Integer expectedUserId = 101;
    orderOutDto.setUserId(expectedUserId);
    Integer actualUserId = orderOutDto.getUserId();
    assertEquals(expectedUserId, actualUserId, "The userId should be equal to the expected value.");
  }

  @Test
  public void testAddressIdGetterAndSetter() {
    OrderOutDto orderOutDto = new OrderOutDto();
    Integer expectedAddressId = 202;
    orderOutDto.setAddressId(expectedAddressId);
    Integer actualAddressId = orderOutDto.getAddressId();
    assertEquals(expectedAddressId, actualAddressId, "The addressId should be equal to the expected value.");
  }

  @Test
  public void testRestaurantIdGetterAndSetter() {
    OrderOutDto orderOutDto = new OrderOutDto();
    Integer expectedRestaurantId = 303;
    orderOutDto.setRestaurantId(expectedRestaurantId);
    Integer actualRestaurantId = orderOutDto.getRestaurantId();
    assertEquals(expectedRestaurantId, actualRestaurantId, "The restaurantId should be equal to the expected value.");
  }

  @Test
  public void testOrderStatusGetterAndSetter() {
    OrderOutDto orderOutDto = new OrderOutDto();
    OrderStatus expectedOrderStatus = OrderStatus.PENDING; // Assuming PENDING is a valid enum value
    orderOutDto.setOrderStatus(expectedOrderStatus);
    OrderStatus actualOrderStatus = orderOutDto.getOrderStatus();
    assertEquals(expectedOrderStatus, actualOrderStatus, "The orderStatus should be equal to the expected value.");
  }

  @Test
  public void testOrderDetailsGetterAndSetter() {
    OrderOutDto orderOutDto = new OrderOutDto();
    OrderItemDetailOutDto itemDetail1 = new OrderItemDetailOutDto();
    itemDetail1.setFoodItemId(1);
    itemDetail1.setFoodItemName("Pizza");
    itemDetail1.setQuantity(2);
    itemDetail1.setPrice(19.99);

    OrderItemDetailOutDto itemDetail2 = new OrderItemDetailOutDto();
    itemDetail2.setFoodItemId(2);
    itemDetail2.setFoodItemName("Burger");
    itemDetail2.setQuantity(1);
    itemDetail2.setPrice(9.99);

    List<OrderItemDetailOutDto> expectedOrderDetails = Arrays.asList(itemDetail1, itemDetail2);
    orderOutDto.setOrderDetails(expectedOrderDetails);
    List<OrderItemDetailOutDto> actualOrderDetails = orderOutDto.getOrderDetails();
    assertEquals(expectedOrderDetails, actualOrderDetails, "The orderDetails should be equal to the expected value.");
  }

  @Test
  public void testCreatedAtGetterAndSetter() {
    OrderOutDto orderOutDto = new OrderOutDto();
    LocalDateTime expectedCreatedAt = LocalDateTime.now();
    orderOutDto.setCreatedAt(expectedCreatedAt);
    LocalDateTime actualCreatedAt = orderOutDto.getCreatedAt();
    assertEquals(expectedCreatedAt, actualCreatedAt, "The createdAt should be equal to the expected value.");
  }

  @Test
  public void testHashCode() {
    OrderOutDto orderOutDto1 = new OrderOutDto();
    orderOutDto1.setId(1);
    orderOutDto1.setUserId(101);
    orderOutDto1.setAddressId(202);
    orderOutDto1.setRestaurantId(303);
    orderOutDto1.setOrderStatus(OrderStatus.PENDING);

    OrderOutDto orderOutDto2 = new OrderOutDto();
    orderOutDto2.setId(1);
    orderOutDto2.setUserId(101);
    orderOutDto2.setAddressId(202);
    orderOutDto2.setRestaurantId(303);
    orderOutDto2.setOrderStatus(OrderStatus.PENDING);

    assertEquals(orderOutDto1.hashCode(), orderOutDto2.hashCode(),
      "The hashCode should be equal for objects with the same field values.");
  }

  @Test
  public void testEquals() {
    OrderOutDto orderOutDto1 = new OrderOutDto();
    orderOutDto1.setId(1);
    orderOutDto1.setUserId(101);
    orderOutDto1.setAddressId(202);
    orderOutDto1.setRestaurantId(303);
    orderOutDto1.setOrderStatus(OrderStatus.PENDING);

    OrderOutDto orderOutDto2 = new OrderOutDto();
    orderOutDto2.setId(1);
    orderOutDto2.setUserId(101);
    orderOutDto2.setAddressId(202);
    orderOutDto2.setRestaurantId(303);
    orderOutDto2.setOrderStatus(OrderStatus.PENDING);

    OrderOutDto orderOutDto3 = new OrderOutDto();
    orderOutDto3.setId(2); // Different id
    orderOutDto3.setUserId(101);
    orderOutDto3.setAddressId(202);
    orderOutDto3.setRestaurantId(303);
    orderOutDto3.setOrderStatus(OrderStatus.PENDING);

    assertEquals(orderOutDto1, orderOutDto2, "The equals method should return true for objects with the same field values.");
    assertNotEquals(orderOutDto1, orderOutDto3, "The equals method should return false for objects with different field values.");
    assertNotEquals(null, orderOutDto1, "The equals method should return false when comparing with null.");
    assertNotEquals(orderOutDto1, new Object(),
      "The equals method should return false when comparing with an object of different type.");
  }
}
