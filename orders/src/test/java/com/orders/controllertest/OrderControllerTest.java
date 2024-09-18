package com.orders.controllertest;

import com.orders.controller.OrderController;
import com.orders.dto.OrderInDto;
import com.orders.dto.UpdateStatusInDto;
import com.orders.dto.OrderItemDetailOutDto;
import com.orders.dto.OrderOutDto;
import com.orders.entities.OrderStatus;
import com.orders.service.OrderService;
import com.orders.dto.ApiResponse;
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
import static org.mockito.Mockito.doNothing;
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

    orderInDto = new OrderInDto(1, 1, 1, Arrays.asList(1, 2, 3), 100.0, OrderStatus.PENDING);

    updateStatusInDto = new UpdateStatusInDto(OrderStatus.CONFIRMED);

    OrderItemDetailOutDto itemDetail = new OrderItemDetailOutDto();
    itemDetail.setFoodItemId(1);
    itemDetail.setFoodItemName("Item");
    itemDetail.setQuantity(2);
    itemDetail.setPrice(50.0);

    orderOutDto = new OrderOutDto();
    orderOutDto.setId(1);
    orderOutDto.setUserId(1);
    orderOutDto.setAddressId(1); // Adjusted to match new field name
    orderOutDto.setRestaurantId(1);
    orderOutDto.setOrderStatus(OrderStatus.PENDING);
    orderOutDto.setCreatedAt(LocalDateTime.now());
    orderOutDto.setRestaurantName("Restaurant Name");
    orderOutDto.setAddressName("Address Name");
    orderOutDto.setUserName("User Name");
    orderOutDto.setOrderDetails(Collections.singletonList(itemDetail)); // Adjusted field name

    apiResponse = new ApiResponse(Constant.SUCCESS);
  }

  @Test
  void testCreateOrder() {
    doNothing().when(orderService).createOrder(any(OrderInDto.class));
    ResponseEntity<?> response = orderController.createOrder(orderInDto);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
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
    doNothing().when(orderService).updateStatus(anyInt(), any(UpdateStatusInDto.class));
    ResponseEntity<?> response = orderController.updateStatus(1, updateStatusInDto);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    ApiResponse actualResponse = (ApiResponse) response.getBody();
    assertEquals(Constant.CART_UPDATED_SUCCESS, actualResponse.getMessage());
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
