package com.orders.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OrderTest {

  @Test
  public void testIdGetterAndSetter() {
    Order order = new Order();
    Integer expectedId = 1;
    order.setId(expectedId);
    Integer actualId = order.getId();
    assertEquals(expectedId, actualId, "The id should be equal to the expected value.");
  }

  @Test
  public void testUserIdGetterAndSetter() {
    Order order = new Order();
    Integer expectedUserId = 2;
    order.setUserId(expectedUserId);
    Integer actualUserId = order.getUserId();
    assertEquals(expectedUserId, actualUserId, "The userId should be equal to the expected value.");
  }

  @Test
  public void testRestaurantIdGetterAndSetter() {
    Order order = new Order();
    Integer expectedRestaurantId = 3;
    order.setRestaurantId(expectedRestaurantId);
    Integer actualRestaurantId = order.getRestaurantId();
    assertEquals(expectedRestaurantId, actualRestaurantId, "The restaurantId should be equal to the expected value.");
  }

  @Test
  public void testAddressIdGetterAndSetter() {
    Order order = new Order();
    Integer expectedAddressId = 4;
    order.setAddressId(expectedAddressId);
    Integer actualAddressId = order.getAddressId();
    assertEquals(expectedAddressId, actualAddressId, "The addressId should be equal to the expected value.");
  }

  @Test
  public void testOrderDetailsGetterAndSetter() {
    Order order = new Order();
    String expectedOrderDetails = "2 Burgers, 1 Pizza";
    order.setOrderDetails(expectedOrderDetails);
    String actualOrderDetails = order.getOrderDetails();
    assertEquals(expectedOrderDetails, actualOrderDetails, "The orderDetails should be equal to the expected value.");
  }

  @Test
  public void testLastUpdatedAtGetterAndSetter() {
    Order order = new Order();
    LocalDateTime expectedLastUpdatedAt = LocalDateTime.now();
    order.setLastUpdatedAt(expectedLastUpdatedAt);
    LocalDateTime actualLastUpdatedAt = order.getLastUpdatedAt();
    assertEquals(expectedLastUpdatedAt, actualLastUpdatedAt, "The lastUpdatedAt should be equal to the expected value.");
  }

  @Test
  public void testTotalAmountGetterAndSetter() {
    Order order = new Order();
    Double expectedTotalAmount = 59.99;
    order.setTotalAmount(expectedTotalAmount);
    Double actualTotalAmount = order.getTotalAmount();
    assertEquals(expectedTotalAmount, actualTotalAmount, "The totalAmount should be equal to the expected value.");
  }

  @Test
  public void testOrderStatusGetterAndSetter() {
    Order order = new Order();
    OrderStatus expectedOrderStatus = OrderStatus.PENDING;
    order.setOrderStatus(expectedOrderStatus);
    OrderStatus actualOrderStatus = order.getOrderStatus();
    assertEquals(expectedOrderStatus, actualOrderStatus, "The orderStatus should be equal to the expected value.");
  }

  @Test
  public void testEquals() {
    Order order1 = new Order();
    order1.setId(1);
    order1.setUserId(2);
    order1.setRestaurantId(3);
    order1.setAddressId(4);
    order1.setOrderDetails("2 Burgers, 1 Pizza");
    order1.setLastUpdatedAt(LocalDateTime.now());
    order1.setTotalAmount(59.99);
    order1.setOrderStatus(OrderStatus.PENDING);

    Order order2 = new Order();
    order2.setId(1);
    order2.setUserId(2);
    order2.setRestaurantId(3);
    order2.setAddressId(4);
    order2.setOrderDetails("2 Burgers, 1 Pizza");
    order2.setLastUpdatedAt(LocalDateTime.now());
    order2.setTotalAmount(59.99);
    order2.setOrderStatus(OrderStatus.PENDING);

    assertEquals(order1, order2, "The Order objects should be equal.");

    order2.setTotalAmount(60.00);
    assertNotEquals(order1, order2, "The Order objects should not be equal.");
  }

  @Test
  public void testHashCode() {
    Order order1 = new Order();
    order1.setId(1);
    order1.setUserId(2);
    order1.setRestaurantId(3);
    order1.setAddressId(4);
    order1.setOrderDetails("2 Burgers, 1 Pizza");
    order1.setLastUpdatedAt(LocalDateTime.now());
    order1.setTotalAmount(59.99);
    order1.setOrderStatus(OrderStatus.PENDING);

    Order order2 = new Order();
    order2.setId(1);
    order2.setUserId(2);
    order2.setRestaurantId(3);
    order2.setAddressId(4);
    order2.setOrderDetails("2 Burgers, 1 Pizza");
    order2.setLastUpdatedAt(LocalDateTime.now());
    order2.setTotalAmount(59.99);
    order2.setOrderStatus(OrderStatus.PENDING);

    assertEquals(order1.hashCode(), order2.hashCode(), "The hashCodes should be equal.");

    order2.setTotalAmount(60.00);
    assertNotEquals(order1.hashCode(), order2.hashCode(), "The hashCodes should not be equal.");
  }

  @Test
  public void testToString() {
    Order order = new Order();
    order.setId(1);
    order.setUserId(2);
    order.setRestaurantId(3);
    order.setAddressId(4);
    order.setOrderDetails("2 Burgers, 1 Pizza");
    order.setLastUpdatedAt(LocalDateTime.now());
    order.setTotalAmount(59.99);
    order.setOrderStatus(OrderStatus.PENDING);

    String expectedToString =
      "Order(id=1, userId=2, restaurantId=3, addressId=4, orderDetails=2 Burgers, 1 Pizza, lastUpdatedAt=" + order.getLastUpdatedAt() +
        ", totalAmount=59.99, orderStatus=PENDING)";
    assertEquals(expectedToString, order.toString(), "The toString method should return the expected string.");
  }
}
