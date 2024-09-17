package com.user.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Map;

public class ExceptionHandlerTest {

  private GlobalExceptionHandler exceptionHandler;

  @BeforeEach
  public void setUp() {
    exceptionHandler = new GlobalExceptionHandler();
  }

  @Test
  public void testHandleValidationExceptions() {
    BindingResult bindingResult = mock(BindingResult.class);

    FieldError fieldError = new FieldError("object", "field", "Error message");

    // Create a mock MethodArgumentNotValidException
    MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

    Map<String, Object> response = exceptionHandler.handleValidationExceptions(ex);

    assertEquals(HttpStatus.BAD_REQUEST.value(), response.get("status"));
    assertEquals(null, ((Map<String, String>) response.get("errors")).get("field"));
  }

  @Test
  public void testUserAlreadyExistedException() {
    UserAlreadyExisted ex = new UserAlreadyExisted("User already exists");

    GlobalExceptionHandler.ErrorResponse response = exceptionHandler.userAlreadyExisted(ex);

    assertEquals(HttpStatus.CONFLICT.value(), response.getStatus());
    assertEquals("User already exists", response.getMessage());
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
    Exception ex = new Exception("Unexpected error");

    GlobalExceptionHandler.ErrorResponse response = exceptionHandler.handleGeneralException(ex);

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
    assertEquals("An unexpected error occurred. Please try again later or contact support.", response.getMessage());
  }
}
