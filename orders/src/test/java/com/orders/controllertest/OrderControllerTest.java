package com.orders.controllertest;

import com.orders.controller.OrderController;
import com.orders.dto.indto.OrderInDto;
import com.orders.dto.indto.UpdateStatusInDto;
import com.orders.dto.outdto.OrderItemDetailOutDto;
import com.orders.dto.outdto.OrderOutDto;
import com.orders.entities.OrderStatus;
import com.orders.service.OrderService;
import com.orders.util.ApiResponse;
import com.orders.util.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderControllerTest {

  @Mock
  private OrderService orderService;

  @InjectMocks
  private OrderController orderController;

  private OrderInDto orderInDto;
  private UpdateStatusInDto updateStatusInDto;
  private OrderOutDto orderOutDto;
  private ApiResponse apiResponse;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    // Mocking OrderInDto
    orderInDto = mock(OrderInDto.class);
    when(orderInDto.getUserId()).thenReturn(1);
    when(orderInDto.getRestaurantId()).thenReturn(1);
    when(orderInDto.getAddressId()).thenReturn(1);
    when(orderInDto.getCartIds()).thenReturn(Arrays.asList(1, 2, 3));
    when(orderInDto.getTotalAmount()).thenReturn(100.0);
    when(orderInDto.getOrderStatus()).thenReturn(OrderStatus.PENDING);

    // Mocking UpdateStatusInDto
    updateStatusInDto = mock(UpdateStatusInDto.class);
    when(updateStatusInDto.getOrderStatus()).thenReturn(OrderStatus.CONFIRMED);

    // Mocking OrderOutDto
    orderOutDto = mock(OrderOutDto.class);
    when(orderOutDto.getId()).thenReturn(1);
    when(orderOutDto.getUserId()).thenReturn(1);
    when(orderOutDto.getAddressId()).thenReturn(1);
    when(orderOutDto.getRestaurantId()).thenReturn(1);
    when(orderOutDto.getOrderStatus()).thenReturn(OrderStatus.PENDING);
    when(orderOutDto.getOrderDetails()).thenReturn(Collections.singletonList(mock(OrderItemDetailOutDto.class)));
    when(orderOutDto.getCreatedAt()).thenReturn(LocalDateTime.now());

    // Mocking ApiResponse
    apiResponse = mock(ApiResponse.class);
    when(apiResponse.getMessage()).thenReturn(Constant.SUCCESS);
  }

  @Test
  void testCreateOrder() {
    // Execute the controller method
    ResponseEntity<?> response = orderController.createOrder(orderInDto);

    // Verify the response status code
    assertEquals(HttpStatus.CREATED, response.getStatusCode());

    // Verify the interactions with orderService
    verify(orderService).createOrder(any(OrderInDto.class));
  }


  @Test
  void testGetOrder() {
    when(orderService.getOrder(anyInt())).thenReturn(orderOutDto);
    ResponseEntity<?> response = orderController.getOrder(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(orderOutDto, response.getBody());
  }

  @Test
  void testUpdateStatus() {
    when(orderService.updateStatus(anyInt(), any(UpdateStatusInDto.class))).thenReturn(apiResponse);
    ResponseEntity<?> response = orderController.updateStatus(1, updateStatusInDto);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(apiResponse, response.getBody());
  }

  @Test
  void testGetOrdersByUser() {
    List<OrderOutDto> orderOutDtoList = Collections.singletonList(orderOutDto);
    when(orderService.getOrdersByUser(anyInt())).thenReturn(orderOutDtoList);
    ResponseEntity<List<OrderOutDto>> response = orderController.getOrdersByUser(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(orderOutDtoList, response.getBody());
  }

  @Test
  void testGetOrdersByRestaurant() {
    List<OrderOutDto> orderOutDtoList = Collections.singletonList(orderOutDto);
    when(orderService.getOrdersByRestaurant(anyInt())).thenReturn(orderOutDtoList);
    ResponseEntity<List<OrderOutDto>> response = orderController.getOrdersByRestaurant(1);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(orderOutDtoList, response.getBody());
  }
}
