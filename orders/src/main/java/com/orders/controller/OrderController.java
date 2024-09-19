package com.orders.controller;

import com.orders.dto.OrderInDto;
import com.orders.dto.UpdateStatusInDto;
import com.orders.dto.OrderOutDto;
import com.orders.service.OrderService;
import com.orders.dto.ApiResponse;
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

/**
 * Controller for handling order-related operations.
 * <p>
 * This controller provides endpoints for creating orders, updating order status, and
 * retrieving orders by user or restaurant.
 * </p>
 */
@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class OrderController {

  /**
   * Service for handling order-related business logic.
   * <p>
   * This service is used by the controller to perform operations such as creating orders,
   * updating order statuses, and retrieving orders based on user or restaurant IDs.
   * </p>
   */
  @Autowired
  private OrderService orderService;

  /**
   * Creates a new order.
   * <p>
   * This method processes a POST request to create an order based on the provided
   * {@link OrderInDto} input. The order will be created with the details specified in
   * the DTO.
   * </p>
   *
   * @param orderInDto The input DTO containing the details of the order to be created.
   * @return A {@link ResponseEntity} with a success message and HTTP status 201 (Created).
   */
  @PostMapping("/create")
  public ResponseEntity<?> createOrder(@Valid @RequestBody final OrderInDto orderInDto) {
    log.info("Received request to create an order for userId: {}", orderInDto.getUserId());
    orderService.createOrder(orderInDto);
    log.info("Order successfully created for userId: {}", orderInDto.getUserId());
    return new ResponseEntity<>(new ApiResponse(Constant.SUCCESS), HttpStatus.CREATED);
  }

  /**
   * Retrieves an order by its ID.
   * <p>
   * This method handles a GET request to retrieve the details of an order based on its ID.
   * </p>
   *
   * @param orderId The ID of the order to be retrieved.
   * @return A {@link ResponseEntity} containing the order details.
   */
  @GetMapping("/get/{orderId}")
  public ResponseEntity<?> getOrder(@PathVariable final Integer orderId) {
    log.info("Received request to get order with orderId: {}", orderId);
    return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
  }

  /**
   * Updates the status of an existing order.
   * <p>
   * This method processes a PUT request to update the status of an order based on the
   * provided {@link UpdateStatusInDto} input.
   * </p>
   *
   * @param orderId The ID of the order whose status is to be updated.
   * @param updateStatusInDto The input DTO containing the new status details for the order.
   * @return A {@link ResponseEntity} with a success message and HTTP status 200 (OK).
   */
  @PutMapping("updateStatus/{orderId}")
  public ResponseEntity<?> updateStatus(@PathVariable final Integer orderId,
                                        @RequestBody final UpdateStatusInDto updateStatusInDto) {
    log.info("Received request to update status for orderId: {}", orderId);
    orderService.updateStatus(orderId, updateStatusInDto);
    log.info("Order status updated successfully for orderId: {}", orderId);
    return new ResponseEntity<>(new ApiResponse(Constant.CART_UPDATED_SUCCESS), HttpStatus.OK);
  }


  /**
   * Retrieves all orders for a given user.
   * <p>
   * This method handles a GET request to retrieve all orders placed by a specific user.
   * </p>
   *
   * @param userId The ID of the user whose orders are to be retrieved.
   * @return A {@link ResponseEntity} containing the list of orders made by the user.
   */
  @GetMapping("/user/{userId}")
  public ResponseEntity<List<OrderOutDto>> getOrdersByUser(@PathVariable final Integer userId) {
    log.info("Received request to get all orders for userId: {}", userId);
    List<OrderOutDto> orders = orderService.getOrdersByUser(userId);
    log.info("Retrieved {} orders for userId: {}", orders.size(), userId);
    return ResponseEntity.ok(orders);
  }


  /**
   * Retrieves all orders for a given restaurant.
   * <p>
   * This method handles a GET request to retrieve all orders associated with a specific restaurant.
   * </p>
   *
   * @param restaurantId The ID of the restaurant whose orders are to be retrieved.
   * @return A {@link ResponseEntity} containing the list of orders for the restaurant.
   */
  @GetMapping("/restaurant/{restaurantId}")
  public ResponseEntity<List<OrderOutDto>> getOrdersByRestaurant(@PathVariable final Integer restaurantId) {
    log.info("Received request to get all orders for restaurantId: {}", restaurantId);
    List<OrderOutDto> orders = orderService.getOrdersByRestaurant(restaurantId);
    log.info("Retrieved {} orders for restaurantId: {}", orders.size(), restaurantId);
    return ResponseEntity.ok(orders);
  }
}
