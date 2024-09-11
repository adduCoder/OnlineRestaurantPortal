package com.orders.dto;

import lombok.Data;


/**
 * Data Transfer Object (DTO) for capturing the amount of money.
 */
@Data
public class AmountInDto {
  /**
   * The amount of money involved in the operation.
   */
  private Double money;
}
