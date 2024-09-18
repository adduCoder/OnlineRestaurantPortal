package com.orders.servicetest;

import com.orders.dto.OrderInDto;
import com.orders.dto.UpdateStatusInDto;
import com.orders.dto.FoodItemNameOutDto;
import com.orders.dto.OrderItemDetailOutDto;
import com.orders.dto.OrderOutDto;
import com.orders.dto.UserOutDto;
import com.orders.entities.Cart;
import com.orders.entities.Order;
import com.orders.entities.OrderStatus;
import com.orders.exception.InsufficientBalanceException;
import com.orders.exception.OrderNotFoundException;
import com.orders.exception.SessionExpiredException;
import com.orders.repository.CartRepository;
import com.orders.repository.OrderRepository;
import com.orders.service.OrderService;
import com.orders.service.RestaurantFClient;
import com.orders.service.UserFClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;

public class OrderServiceTest {

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private CartRepository cartRepository;

  @Mock
  private UserFClient userFClient;

  @Mock
  private RestaurantFClient restaurantFClient;

  @InjectMocks
  private OrderService orderService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCreateOrder() {
    // Arrange
    OrderInDto orderInDto = new OrderInDto();
    orderInDto.setUserId(1);
    orderInDto.setCartIds(Arrays.asList(1, 2));
    orderInDto.setTotalAmount(50.0);

    UserOutDto userOutDto = new UserOutDto();
    userOutDto.setWalletBalance(100.0);
    Mockito.when(userFClient.getUserById(1)).thenReturn(userOutDto);

    Mockito.when(cartRepository.existsById(1)).thenReturn(true);
    Mockito.when(cartRepository.existsById(2)).thenReturn(true);
    Mockito.when(cartRepository.findById(1)).thenReturn(Optional.of(new Cart()));
    Mockito.when(cartRepository.findById(2)).thenReturn(Optional.of(new Cart()));

    orderService.createOrder(orderInDto);


    Mockito.verify(orderRepository, Mockito.times(1)).save(Mockito.any(Order.class));
    Mockito.verify(cartRepository, Mockito.times(2)).deleteById(Mockito.anyInt());
  }


  @Test
  public void testCreateOrder_InsufficientBalance() {
    OrderInDto orderInDto = new OrderInDto();
    orderInDto.setUserId(1);
    orderInDto.setCartIds(Arrays.asList(1, 2));
    orderInDto.setTotalAmount(200.0);

    UserOutDto userOutDto = new UserOutDto();
    userOutDto.setWalletBalance(100.0);

    Mockito.when(userFClient.getUserById(anyInt())).thenReturn(userOutDto);

    assertThrows(InsufficientBalanceException.class, () -> orderService.createOrder(orderInDto));
  }

  @Test
  public void testOrderDetailsForOrderOutDto() {
    FoodItemNameOutDto foodItemNameOutDto = new FoodItemNameOutDto();
    foodItemNameOutDto.setFoodItemName("Pizza");
    foodItemNameOutDto.setPrice(10.0);

    Mockito.when(restaurantFClient.getFoodItemName(anyInt()))
      .thenReturn(ResponseEntity.ok(foodItemNameOutDto));


    List<OrderItemDetailOutDto> result = orderService.orderDetailsForOrderOutDto("1:2");

    assertEquals(1, result.size());
    assertEquals("Pizza", result.get(0).getFoodItemName());
    assertEquals(2, result.get(0).getQuantity());
  }

  @Test
  public void testGetOrder() {
    Order order = new Order();
    order.setId(1);
    order.setOrderDetails("1:2");

    Mockito.when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
    Mockito.when(restaurantFClient.getFoodItemName(anyInt()))
      .thenReturn(ResponseEntity.ok(new FoodItemNameOutDto()));

    OrderOutDto result = orderService.getOrder(1);

    assertEquals(1, result.getId());
  }

  @Test
  public void testGetOrder_OrderNotFound() {
    Mockito.when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());

    assertThrows(OrderNotFoundException.class, () -> orderService.getOrder(1));
  }

  @Test
  public void testUpdateStatus() {
    Order order = new Order();
    order.setId(1);
    order.setOrderStatus(OrderStatus.PENDING);
    order.setUserId(1);
    order.setTotalAmount(50.0);
    order.setLastUpdatedAt(LocalDateTime.now().minusSeconds(20));

    Mockito.when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
    Mockito.when(userFClient.getUserById(anyInt())).thenReturn(new UserOutDto());

    UpdateStatusInDto updateStatusInDto = new UpdateStatusInDto();
    updateStatusInDto.setOrderStatus(OrderStatus.CANCELLED);

    orderService.updateStatus(1, updateStatusInDto);

  }

  @Test
  public void testUpdateStatus_SessionExpired() {
    Order order = new Order();
    order.setId(1);
    order.setOrderStatus(OrderStatus.PENDING);
    order.setUserId(1);
    order.setTotalAmount(50.0);
    order.setLastUpdatedAt(LocalDateTime.now().minusSeconds(40));

    Mockito.when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
    Mockito.when(userFClient.getUserById(anyInt())).thenReturn(new UserOutDto());

    UpdateStatusInDto updateStatusInDto = new UpdateStatusInDto();
    updateStatusInDto.setOrderStatus(OrderStatus.CANCELLED);

    assertThrows(SessionExpiredException.class, () -> orderService.updateStatus(1, updateStatusInDto));
  }

  @Test
  public void testGetOrdersByUser() {

    Order order = new Order();
    order.setId(1);
    order.setOrderDetails("1:2");

    Mockito.when(orderRepository.findByUserId(anyInt())).thenReturn(Collections.singletonList(order));
    Mockito.when(restaurantFClient.getFoodItemName(anyInt()))
      .thenReturn(ResponseEntity.ok(new FoodItemNameOutDto()));


    List<OrderOutDto> result = orderService.getOrdersByUser(1);


    assertEquals(1, result.size());
  }

  @Test
  public void testGetOrdersByRestaurant() {

    Order order = new Order();
    order.setId(1);
    order.setOrderDetails("1:2");

    Mockito.when(orderRepository.findByRestaurantId(anyInt())).thenReturn(Collections.singletonList(order));
    Mockito.when(restaurantFClient.getFoodItemName(anyInt()))
      .thenReturn(ResponseEntity.ok(new FoodItemNameOutDto()));


    List<OrderOutDto> result = orderService.getOrdersByRestaurant(1);


    assertEquals(1, result.size());
  }
}
