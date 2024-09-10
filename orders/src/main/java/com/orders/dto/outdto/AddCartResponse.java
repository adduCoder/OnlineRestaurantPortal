package com.orders.dto.outdto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for encapsulating the response after adding or updating a cart.
 */
@Data
public class AddCartResponse {
  /**
   * The unique identifier of the cart that was added or updated.
   */
  private Integer cartId;
}
