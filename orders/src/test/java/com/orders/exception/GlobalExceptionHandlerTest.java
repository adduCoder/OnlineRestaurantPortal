package com.orders.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;


public class GlobalExceptionHandlerTest {

  private GlobalExceptionHandler globalExceptionHandler;

  @BeforeEach
  public void setUp() {
    globalExceptionHandler = new GlobalExceptionHandler();
  }

  @Test
  public void testHandleOrderNotFound() {
    OrderNotFoundException ex = new OrderNotFoundException("Order not found");
    GlobalExceptionHandler.ErrorResponse response = globalExceptionHandler.handleOrderNotFound(ex);
    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    assertEquals("Order not found", response.getMessage());
  }

  @Test
  public void testHandleSessionExpiredException() {
    SessionExpiredException ex = new SessionExpiredException("Session expired");
    GlobalExceptionHandler.ErrorResponse response = globalExceptionHandler.handleSessionExpiredException(ex);
    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    assertEquals("Session expired", response.getMessage());
  }

  @Test
  public void testHandleInsufficientBalance() {
    InsufficientBalanceException ex = new InsufficientBalanceException("Insufficient balance");
    GlobalExceptionHandler.ErrorResponse response = globalExceptionHandler.handleInsufficientBalance(ex);
    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    assertEquals("Insufficient balance", response.getMessage());
  }

  @Test
  public void testHandleInvalidOperation() {
    InvalidOperationException ex = new InvalidOperationException("Invalid operation");
    GlobalExceptionHandler.ErrorResponse response = globalExceptionHandler.handleInvalidOperation(ex);
    assertEquals(HttpStatus.FORBIDDEN.value(), response.getStatus());
    assertEquals("Invalid operation", response.getMessage());
  }

  @Test
  public void testHandleUserNotFound() {
    UserNotFoundException ex = new UserNotFoundException("User not found");
    GlobalExceptionHandler.ErrorResponse response = globalExceptionHandler.handleCategoryAlreadyExists(ex);
    assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
    assertEquals("User not found", response.getMessage());
  }


  @Test
  public void testHandleGeneralException() {
    Exception ex = new Exception("General exception");
    GlobalExceptionHandler.ErrorResponse response = globalExceptionHandler.handleGeneralException(ex);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
    assertEquals("An unexpected error occurred. Please try again later or contact support.", response.getMessage());
  }
}
