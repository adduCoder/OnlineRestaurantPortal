package com.orders.dto;

import com.orders.entities.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class OrderInDtoTest {

  private OrderInDto orderInDto;

  @BeforeEach
  public void setUp() {
    orderInDto = new OrderInDto();
  }

  @Test
  public void testGetUserId() {
    orderInDto.setUserId(1);
    assertEquals(1, orderInDto.getUserId());
  }

  @Test
  public void testSetUserId() {
    orderInDto.setUserId(2);
    assertEquals(2, orderInDto.getUserId());
  }

  @Test
  public void testGetRestaurantId() {
    orderInDto.setRestaurantId(1);
    assertEquals(1, orderInDto.getRestaurantId());
  }

  @Test
  public void testSetRestaurantId() {
    orderInDto.setRestaurantId(2);
    assertEquals(2, orderInDto.getRestaurantId());
  }

  @Test
  public void testGetAddressId() {
    orderInDto.setAddressId(1);
    assertEquals(1, orderInDto.getAddressId());
  }

  @Test
  public void testSetAddressId() {
    orderInDto.setAddressId(2);
    assertEquals(2, orderInDto.getAddressId());
  }

  @Test
  public void testGetCartIds() {
    orderInDto.setCartIds(Arrays.asList(1, 2, 3));
    assertEquals(Arrays.asList(1, 2, 3), orderInDto.getCartIds());
  }

  @Test
  public void testSetCartIds() {
    orderInDto.setCartIds(Arrays.asList(4, 5, 6));
    assertEquals(Arrays.asList(4, 5, 6), orderInDto.getCartIds());
  }

  @Test
  public void testGetTotalAmount() {
    orderInDto.setTotalAmount(100.50);
    assertEquals(100.50, orderInDto.getTotalAmount(), 0.01); // Using epsilon
  }

  @Test
  public void testSetTotalAmount() {
    orderInDto.setTotalAmount(200.75);
    assertEquals(200.75, orderInDto.getTotalAmount(), 0.01); // Using epsilon
  }

  @Test
  public void testGetOrderStatus() {
    orderInDto.setOrderStatus(OrderStatus.PENDING);
    assertEquals(OrderStatus.PENDING, orderInDto.getOrderStatus());
  }

  @Test
  public void testSetOrderStatus() {
    orderInDto.setOrderStatus(OrderStatus.CONFIRMED);
    assertEquals(OrderStatus.CONFIRMED, orderInDto.getOrderStatus());
  }

  @Test
  public void testHashCode() {
    OrderInDto dto1 = new OrderInDto();
    dto1.setUserId(1);
    dto1.setRestaurantId(2);
    dto1.setAddressId(3);
    dto1.setCartIds(Arrays.asList(1, 2, 3));
    dto1.setTotalAmount(100.50);
    dto1.setOrderStatus(OrderStatus.PENDING);

    OrderInDto dto2 = new OrderInDto();
    dto2.setUserId(1);
    dto2.setRestaurantId(2);
    dto2.setAddressId(3);
    dto2.setCartIds(Arrays.asList(1, 2, 3));
    dto2.setTotalAmount(100.50);
    dto2.setOrderStatus(OrderStatus.PENDING);

    assertEquals(dto1.hashCode(), dto2.hashCode());

    dto2.setTotalAmount(200.75);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());
  }

  @Test
  public void testEquals() {
    OrderInDto dto1 = new OrderInDto();
    dto1.setUserId(1);
    dto1.setRestaurantId(2);
    dto1.setAddressId(3);
    dto1.setCartIds(Arrays.asList(1, 2, 3));
    dto1.setTotalAmount(100.50);
    dto1.setOrderStatus(OrderStatus.PENDING);

    OrderInDto dto2 = new OrderInDto();
    dto2.setUserId(1);
    dto2.setRestaurantId(2);
    dto2.setAddressId(3);
    dto2.setCartIds(Arrays.asList(1, 2, 3));
    dto2.setTotalAmount(100.50);
    dto2.setOrderStatus(OrderStatus.PENDING);

    assertEquals(dto1, dto2);

    dto2.setTotalAmount(200.75);
    assertNotEquals(dto1, dto2);

    assertEquals(dto1, dto1);

    assertNotEquals(dto1, null);

    assertNotEquals(dto1, new Object());
  }

  @Test
  public void testToString() {
    orderInDto.setUserId(1);
    orderInDto.setRestaurantId(2);
    orderInDto.setAddressId(3);
    orderInDto.setCartIds(Arrays.asList(1, 2, 3));
    orderInDto.setTotalAmount(100.50);
    orderInDto.setOrderStatus(OrderStatus.PENDING);

    String expectedString = "OrderInDto(userId=1, restaurantId=2, addressId=3, " +
      "cartIds=[1, 2, 3], totalAmount=100.5, orderStatus=PENDING)";

    assertEquals(expectedString, orderInDto.toString());
  }
}
