package com.orders.dtoconversion;

import com.orders.dto.CartInDto;
import com.orders.dto.OrderInDto;
import com.orders.dto.CartOutDto;
import com.orders.dto.OrderOutDto;
import com.orders.entities.Cart;
import com.orders.entities.Order;
import com.orders.entities.OrderStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DtoConversionTest {

  @Test
  public void testMapToCart() {
    // Given
    CartInDto cartInDto = new CartInDto();
    cartInDto.setUserId(1);
    cartInDto.setRestaurantId(2);
    cartInDto.setFoodItemId(3);
    cartInDto.setQuantity(4);
    cartInDto.setPrice(5.99);

    // When
    Cart cart = DtoConversion.mapToCart(cartInDto);

    // Then
    assertEquals(cartInDto.getUserId(), cart.getUserId());
    assertEquals(cartInDto.getRestaurantId(), cart.getRestaurantId());
    assertEquals(cartInDto.getFoodItemId(), cart.getFoodItemId());
    assertEquals(cartInDto.getQuantity(), cart.getQuantity());
    assertEquals(cartInDto.getPrice(), cart.getPrice());
  }

  @Test
  public void testMapToCartOutDto() {
    // Given
    Cart cart = new Cart();
    cart.setId(1);
    cart.setUserId(2);
    cart.setRestaurantId(3);
    cart.setFoodItemId(4);
    cart.setQuantity(5);
    cart.setPrice(6.99);

    // When
    CartOutDto cartOutDto = DtoConversion.mapToCartOutDto(cart);

    // Then
    assertEquals(cart.getId(), cartOutDto.getId());
    assertEquals(cart.getUserId(), cartOutDto.getUserId());
    assertEquals(cart.getRestaurantId(), cartOutDto.getRestaurantId());
    assertEquals(cart.getFoodItemId(), cartOutDto.getFoodItemId());
    assertEquals(cart.getQuantity(), cartOutDto.getQuantity());
    assertEquals(cart.getPrice(), cartOutDto.getPrice());
  }

  @Test
  public void testMapToOrderOutDto() {
    // Given
    Order order = new Order();
    order.setId(1);
    order.setUserId(2);
    order.setRestaurantId(3);
    order.setAddressId(4);
    order.setOrderStatus(OrderStatus.PENDING);
    order.setLastUpdatedAt(LocalDateTime.now());

    // When
    OrderOutDto orderOutDto = DtoConversion.mapToOrderOutDto(order);

    // Then
    assertEquals(order.getId(), orderOutDto.getId());
    assertEquals(order.getUserId(), orderOutDto.getUserId());
    assertEquals(order.getRestaurantId(), orderOutDto.getRestaurantId());
    assertEquals(order.getAddressId(), orderOutDto.getAddressId());
    assertEquals(order.getOrderStatus(), orderOutDto.getOrderStatus());
    assertEquals(order.getLastUpdatedAt(), orderOutDto.getCreatedAt());
  }

  @Test
  public void testMapToOrder() {
    // Given
    OrderInDto orderInDto = new OrderInDto();
    orderInDto.setUserId(1);
    orderInDto.setRestaurantId(2);
    orderInDto.setAddressId(3);
    orderInDto.setTotalAmount(100.50);
    orderInDto.setOrderStatus(OrderStatus.PENDING);

    // When
    Order order = DtoConversion.mapToOrder(orderInDto);

    // Then
    assertEquals(orderInDto.getUserId(), order.getUserId());
    assertEquals(orderInDto.getRestaurantId(), order.getRestaurantId());
    assertEquals(orderInDto.getAddressId(), order.getAddressId());
    assertEquals(orderInDto.getTotalAmount(), order.getTotalAmount());
    assertEquals(orderInDto.getOrderStatus(), order.getOrderStatus());
    assertNotNull(order.getLastUpdatedAt());
    // Check if lastUpdatedAt is within a reasonable range
    assertEquals(LocalDateTime.now().getMinute(), order.getLastUpdatedAt().getMinute());
  }
}
