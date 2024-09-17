package com.orders.dto;

import com.orders.entities.OrderStatus;
import com.sun.istack.NotNull;
import lombok.Data;

import java.util.List;

/**
 * Data Transfer Object (DTO) for capturing input related to an order.
 * <p>
 * This class encapsulates all necessary details required for placing an order,
 * including user ID, restaurant ID, delivery address ID, cart IDs, total amount,
 * and the current status of the order.
 * </p>
 */
@Data
public class OrderInDto {

  /**
   * The ID of the user placing the order.
   * <p>
   * This field is required and cannot be null.
   * </p>
   */
  @NotNull
  private Integer userId;

  /**
   * The ID of the restaurant where the order is placed.
   * <p>
   * This field is required and cannot be null.
   * </p>
   */
  @NotNull
  private Integer restaurantId;

  /**
   * The ID of the address where the order will be delivered.
   * <p>
   * This field is required and cannot be null.
   * </p>
   */
  @NotNull
  private Integer addressId;

  /**
   * The list of cart IDs associated with the order.
   * <p>
   * This field is required and cannot be null.
   * </p>
   */
  @NotNull
  private List<Integer> cartIds;


  /**
   * The total amount for the order.
   * <p>
   * This field is required and cannot be null.
   * </p>
   */
  @NotNull
  private Double totalAmount;

  /**
   * The status of the order.
   * <p>
   * This field is required and cannot be null.
   * </p>
   */
  @NotNull
  private OrderStatus orderStatus;

  /**
   * Default constructor for {@link OrderInDto}.
   */
  public OrderInDto() {
  }

  /**
   * Constructs an {@link OrderInDto} with the specified details.
   *
   * @param userId The ID of the user placing the order.
   * @param restaurantId The ID of the restaurant where the order is placed.
   * @param addressId The ID of the address where the order will be delivered.
   * @param cartIds The list of cart IDs associated with the order.
   * @param totalAmount The total amount for the order.
   * @param orderStatus The status of the order.
   */
  public OrderInDto(
    final Integer userId, final Integer restaurantId,
    final Integer addressId, final List<Integer> cartIds, final Double totalAmount,
                    final OrderStatus orderStatus) {
    this.userId = userId;
    this.restaurantId = restaurantId;
    this.addressId = addressId;
    this.cartIds = cartIds;
    this.totalAmount = totalAmount;
    this.orderStatus = orderStatus;
  }
}
