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
import com.orders.exceptionhandler.InsufficientBalance;
import com.orders.exceptionhandler.OrderNotFound;
import com.orders.exceptionhandler.SessionExpiredException;
import com.orders.repo.CartRepo;
import com.orders.repo.OrderRepo;
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
  private OrderRepo orderRepo;

  @Mock
  private CartRepo cartRepo;

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
    // Mock the userFClient to return the userOutDto
    Mockito.when(userFClient.getUserById(1)).thenReturn(userOutDto);

    // Mock cartRepo behavior
    Mockito.when(cartRepo.existsById(1)).thenReturn(true);
    Mockito.when(cartRepo.existsById(2)).thenReturn(true);
    Mockito.when(cartRepo.findById(1)).thenReturn(Optional.of(new Cart()));
    Mockito.when(cartRepo.findById(2)).thenReturn(Optional.of(new Cart()));

    // Act
    orderService.createOrder(orderInDto);

    // Assert
    Mockito.verify(orderRepo, Mockito.times(1)).save(Mockito.any(Order.class));
    Mockito.verify(cartRepo, Mockito.times(2)).deleteById(Mockito.anyInt());
  }


  @Test
  public void testCreateOrder_InsufficientBalance() {
    // Arrange
    OrderInDto orderInDto = new OrderInDto();
    orderInDto.setUserId(1);
    orderInDto.setCartIds(Arrays.asList(1, 2));
    orderInDto.setTotalAmount(200.0);

    UserOutDto userOutDto = new UserOutDto();
    userOutDto.setWalletBalance(100.0);

    Mockito.when(userFClient.getUserById(anyInt())).thenReturn(userOutDto);

    // Act & Assert
    assertThrows(InsufficientBalance.class, () -> orderService.createOrder(orderInDto));
  }

  @Test
  public void testOrderDetailsForOrderOutDto() {
    // Arrange
    FoodItemNameOutDto foodItemNameOutDto = new FoodItemNameOutDto();
    foodItemNameOutDto.setFoodItemName("Pizza");
    foodItemNameOutDto.setPrice(10.0);

    Mockito.when(restaurantFClient.getFoodItemName(anyInt()))
      .thenReturn(ResponseEntity.ok(foodItemNameOutDto));

    // Act
    List<OrderItemDetailOutDto> result = orderService.orderDetailsForOrderOutDto("1:2");

    // Assert
    assertEquals(1, result.size());
    assertEquals("Pizza", result.get(0).getFoodItemName());
    assertEquals(2, result.get(0).getQuantity());
  }

  @Test
  public void testGetOrder() {
    // Arrange
    Order order = new Order();
    order.setId(1);
    order.setOrderDetails("1:2");

    Mockito.when(orderRepo.findById(anyInt())).thenReturn(Optional.of(order));
    Mockito.when(restaurantFClient.getFoodItemName(anyInt()))
      .thenReturn(ResponseEntity.ok(new FoodItemNameOutDto()));

    // Act
    OrderOutDto result = orderService.getOrder(1);

    // Assert
    assertEquals(1, result.getId());
  }

  @Test
  public void testGetOrder_OrderNotFound() {
    // Arrange
    Mockito.when(orderRepo.findById(anyInt())).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(OrderNotFound.class, () -> orderService.getOrder(1));
  }

  @Test
  public void testUpdateStatus() {
    // Arrange
    Order order = new Order();
    order.setId(1);
    order.setOrderStatus(OrderStatus.PENDING);
    order.setUserId(1);
    order.setTotalAmount(50.0);
    order.setLastUpdatedAt(LocalDateTime.now().minusSeconds(20));

    Mockito.when(orderRepo.findById(anyInt())).thenReturn(Optional.of(order));
    Mockito.when(userFClient.getUserById(anyInt())).thenReturn(new UserOutDto());

    UpdateStatusInDto updateStatusInDto = new UpdateStatusInDto();
    updateStatusInDto.setOrderStatus(OrderStatus.CANCELLED);

    // Act
    orderService.updateStatus(1, updateStatusInDto);

  }

  @Test
  public void testUpdateStatus_SessionExpired() {
    // Arrange
    Order order = new Order();
    order.setId(1);
    order.setOrderStatus(OrderStatus.PENDING);
    order.setUserId(1);
    order.setTotalAmount(50.0);
    order.setLastUpdatedAt(LocalDateTime.now().minusSeconds(40));

    Mockito.when(orderRepo.findById(anyInt())).thenReturn(Optional.of(order));
    Mockito.when(userFClient.getUserById(anyInt())).thenReturn(new UserOutDto());

    UpdateStatusInDto updateStatusInDto = new UpdateStatusInDto();
    updateStatusInDto.setOrderStatus(OrderStatus.CANCELLED);

    // Act & Assert
    assertThrows(SessionExpiredException.class, () -> orderService.updateStatus(1, updateStatusInDto));
  }

  @Test
  public void testGetOrdersByUser() {
    // Arrange
    Order order = new Order();
    order.setId(1);
    order.setOrderDetails("1:2");

    Mockito.when(orderRepo.findByUserId(anyInt())).thenReturn(Collections.singletonList(order));
    Mockito.when(restaurantFClient.getFoodItemName(anyInt()))
      .thenReturn(ResponseEntity.ok(new FoodItemNameOutDto()));

    // Act
    List<OrderOutDto> result = orderService.getOrdersByUser(1);

    // Assert
    assertEquals(1, result.size());
  }

  @Test
  public void testGetOrdersByRestaurant() {
    // Arrange
    Order order = new Order();
    order.setId(1);
    order.setOrderDetails("1:2");

    Mockito.when(orderRepo.findByRestaurantId(anyInt())).thenReturn(Collections.singletonList(order));
    Mockito.when(restaurantFClient.getFoodItemName(anyInt()))
      .thenReturn(ResponseEntity.ok(new FoodItemNameOutDto()));

    // Act
    List<OrderOutDto> result = orderService.getOrdersByRestaurant(1);

    // Assert
    assertEquals(1, result.size());
  }
}
