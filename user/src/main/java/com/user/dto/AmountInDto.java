package com.user.dto;

import lombok.Data;

/**
 * Data Transfer Object (DTO) for handling monetary transactions.
 * This class is used to encapsulate the amount of money involved in requests such as adding or deducting money.
 */
@Data
public class AmountInDto {
  /**
   * The amount of money involved in the transaction.
   * This value is expected to be a non-negative decimal.
   */
  private Double money;
}
