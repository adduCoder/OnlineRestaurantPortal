package com.orders.controller;

import com.orders.dto.indto.OrderInDto;
import com.orders.dto.indto.UpdateStatusInDto;
import com.orders.dto.outdto.OrderOutDto;
import com.orders.service.OrderService;
import com.orders.util.ApiResponse;
import com.orders.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class OrderController {

  @Autowired
  private OrderService orderService;

  /**
   * Creates a new order.
   *
   * @param orderInDto The input DTO containing order details.
   * @return A response entity with a success message.
   */
  @PostMapping("/create")
  public ResponseEntity<?> createOrder(@Valid @RequestBody OrderInDto orderInDto) {
    log.info("Received request to create an order for userId: {}", orderInDto.getUserId());
    orderService.createOrder(orderInDto);
    ApiResponse apiResponse = new ApiResponse();
    apiResponse.setMessage(Constant.SUCCESS);
    log.debug("Order successfully created for userId: {}", orderInDto.getUserId());
    return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
  }


  /**
   * Retrieves an order by its ID.
   *
   * @param orderId The ID of the order to be retrieved.
   * @return A response entity containing the order details.
   */
  @GetMapping("/get/{orderId}")
  public ResponseEntity<?> getOrder(@PathVariable Integer orderId) {
    log.info("Received request to get order with orderId: {}", orderId);
    return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
  }

  /**
   * Updates the status of an existing order.
   *
   * @param orderId The ID of the order to update.
   * @param updateStatusInDto The input DTO containing the new status details.
   * @return A response entity with an API response containing the status of the update.
   */
  @PutMapping("updateStatus/{orderId}")
  public ResponseEntity<?> updateStatus(@PathVariable Integer orderId, @RequestBody UpdateStatusInDto updateStatusInDto) {
    log.info("Received request to update status for orderId: {}", orderId);
    ApiResponse apiResponse = orderService.updateStatus(orderId, updateStatusInDto);
    log.debug("Order status updated successfully for orderId: {}", orderId);
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  /**
   * Retrieves all orders for a given user.
   *
   * @param userId The ID of the user.
   * @return A response entity containing the list of orders made by the user.
   */
  @GetMapping("/user/{userId}")
  public ResponseEntity<List<OrderOutDto>> getOrdersByUser(@PathVariable Integer userId) {
    log.info("Received request to get all orders for userId: {}", userId);
    List<OrderOutDto> orders = orderService.getOrdersByUser(userId);
    log.debug("Retrieved {} orders for userId: {}", orders.size(), userId);
    return ResponseEntity.ok(orders);
  }

  /**
   * Retrieves all orders for a given restaurant.
   *
   * @param restaurantId The ID of the restaurant.
   * @return A response entity containing the list of orders for the restaurant.
   */
  @GetMapping("/restaurant/{restaurantId}")
  public ResponseEntity<List<OrderOutDto>> getOrdersByRestaurant(@PathVariable Integer restaurantId) {
    log.info("Received request to get all orders for restaurantId: {}", restaurantId);
    List<OrderOutDto> orders = orderService.getOrdersByRestaurant(restaurantId);
    log.debug("Retrieved {} orders for restaurantId: {}", orders.size(), restaurantId);
    return ResponseEntity.ok(orders);
  }
}
