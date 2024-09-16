package com.restaurants.exceptionhandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GlobalExceptionHandlerTest {

  private GlobalExceptionHandler exceptionHandler;

  @BeforeEach
  public void setUp() {
    exceptionHandler = new GlobalExceptionHandler();
  }

  @Test
  public void testHandleNotFound() {
    NotFound ex = new NotFound("Resource not found");

    GlobalExceptionHandler.ErrorResponse response = exceptionHandler.handleNotFound(ex);

    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    assertEquals("Resource not found", response.getMessage());
  }

  @Test
  public void testHandleAlreadyExists() {
    AlreadyExists ex = new AlreadyExists("Resource already exists");

    GlobalExceptionHandler.ErrorResponse response = exceptionHandler.handleAlreadyExists(ex);

    assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
    assertEquals("Resource already exists", response.getMessage());
  }

  @Test
  public void testHandleOperationNotAllowed() {
    OperationNotAllowed ex = new OperationNotAllowed("Operation not allowed");

    GlobalExceptionHandler.ErrorResponse response = exceptionHandler.handleOperationNotAllowed(ex);

    assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
    assertEquals("Operation not allowed", response.getMessage());
  }

  @Test
  public void testHandleIllegalArgumentException() {
    IllegalArgumentException ex = new IllegalArgumentException("Invalid argument");

    GlobalExceptionHandler.ErrorResponse response = exceptionHandler.handleIllegalArgumentException(ex);

    assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
    assertEquals("Invalid argument", response.getMessage());
  }



  @Test
  public void testHandleMessageNotReadableException() {
    HttpMessageNotReadableException ex = new HttpMessageNotReadableException("InvalidFormatException");

    ResponseEntity<Map<String, String>> response = exceptionHandler.handleMessageNotReadable(ex);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Invalid data format provided.", response.getBody().get("error"));
  }

  @Test
  public void testHandleGeneralException() {
    Exception ex = new Exception("General exception");

    GlobalExceptionHandler.ErrorResponse response = exceptionHandler.handleGeneralException(ex);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
    assertEquals("An unexpected error occurred. Please try again later or contact support.", response.getMessage());
  }
}
