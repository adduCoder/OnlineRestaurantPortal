package com.orders.dto.indto;

import com.orders.entities.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class UpdateStatusInDtoTest {

  private UpdateStatusInDto updateStatusInDto;

  @BeforeEach
  public void setUp() {
    updateStatusInDto = new UpdateStatusInDto();
    updateStatusInDto.setOrderStatus(OrderStatus.PENDING); // Use an existing value
  }

  @Test
  public void testGetOrderStatus() {
    assertEquals(OrderStatus.PENDING, updateStatusInDto.getOrderStatus());
  }

  @Test
  public void testSetOrderStatus() {
    updateStatusInDto.setOrderStatus(OrderStatus.CONFIRMED); // Use an existing value
    assertEquals(OrderStatus.CONFIRMED, updateStatusInDto.getOrderStatus());
  }

  @Test
  public void testToString() {
    String expected = "UpdateStatusInDto(orderStatus=PENDING)";
    assertEquals(expected, updateStatusInDto.toString());
  }

  @Test
  public void testHashCode() {
    UpdateStatusInDto dto1 = new UpdateStatusInDto();
    dto1.setOrderStatus(OrderStatus.PENDING); // Use an existing value

    UpdateStatusInDto dto2 = new UpdateStatusInDto();
    dto2.setOrderStatus(OrderStatus.PENDING); // Use an existing value

    assertEquals(dto1.hashCode(), dto2.hashCode());

    dto2.setOrderStatus(OrderStatus.CONFIRMED); // Use an existing value
    assertNotEquals(dto1.hashCode(), dto2.hashCode());
  }

  @Test
  public void testEquals() {
    UpdateStatusInDto dto1 = new UpdateStatusInDto();
    dto1.setOrderStatus(OrderStatus.PENDING); // Use an existing value

    UpdateStatusInDto dto2 = new UpdateStatusInDto();
    dto2.setOrderStatus(OrderStatus.PENDING); // Use an existing value

    assertEquals(dto1, dto2);

    dto2.setOrderStatus(OrderStatus.CONFIRMED); // Use an existing value
    assertNotEquals(dto1, dto2);

    assertEquals(dto1, dto1);

    assertNotEquals(dto1, null);

    assertNotEquals(dto1, new Object());
  }
}
