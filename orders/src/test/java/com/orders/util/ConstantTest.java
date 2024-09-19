package com.orders.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstantTest {

  @Test
  public void testUpdated() {
    assertEquals("Updated Successfull", Constant.UPDATED);
  }

  @Test
  public void testInsufficientAmount() {
    assertEquals("Insufficient amount", Constant.INSUFFICIENT_AMOUNT);
  }

  @Test
  public void testNotFound() {
    assertEquals("User Not Found !", Constant.NOT_FOUND);
  }

  @Test
  public void testOrderNotFound() {
    assertEquals("Order Not Found", Constant.ORDER_NOT_FOUND);
  }

  @Test
  public void testSessionExpired() {
    assertEquals("Session Expired (greater than 30 seconds)", Constant.SESSION_EXPIRED);
  }

  @Test
  public void testSuccess() {
    assertEquals("Successfully added", Constant.SUCCESS);
  }

}
