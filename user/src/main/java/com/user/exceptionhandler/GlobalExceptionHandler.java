package com.user.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler for the application.
 * This class handles exceptions thrown by controllers and returns appropriate
 * HTTP responses with error messages.
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
  /**

   /**
   * Handles {@link MethodArgumentNotValidException} exceptions.
   * This exception is typically thrown when validation of method arguments fails.
   *
   * @param ex the exception to handle
   * @return an {@link ErrorResponse} with status {@link HttpStatus#BAD_REQUEST} and a list of validation error messages
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException ex) {
    List<String> errorMessages = ex.getBindingResult()
      .getFieldErrors() // Get the field errors directly
      .stream()
      .map(error -> error.getDefaultMessage()) // Get the default message for each error
      .collect(Collectors.toList());

    String errorMessage = "Validation failed: " + String.join(", ", errorMessages);
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
  }

  /**
   * Handles {@link UserAlreadyExisted} exceptions.
   *
   * @param ex the exception to handle
   * @return an {@link ErrorResponse} with status {@link HttpStatus#CONFLICT} and the exception message
   */
  @ExceptionHandler(UserAlreadyExisted.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  @ResponseBody
  public ErrorResponse userAlreadyExisted(UserAlreadyExisted ex) {
    return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
  }

  @ExceptionHandler(NotFound.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorResponse handleNoAddressFound(NotFound ex) {
    return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Map<String, String>> handleMessageNotReadable(HttpMessageNotReadableException ex) {
    Map<String, String> errors = new HashMap<>();
    String message = ex.getMessage();

    if (message.contains("InvalidFormatException")) {
      errors.put("error", "Invalid data format provided.");
    } else if (message.contains("NumberFormatException")) {
      errors.put("error", "Invalid pin code format. Pin code must be numeric.");
    } else {
      errors.put("error", "Invalid request payload.");
    }

    log.error("Request payload error: {}", message);  // Log the exception message for debugging

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }


  /**
   * Class representing the error response returned by the exception handlers.
   */
  public static class ErrorResponse {
    private int status;
    private String message;

    /**
     * Constructs an {@link ErrorResponse} with the specified status and message.
     *
     * @param status  the HTTP status code
     * @param message the error message
     */
    public ErrorResponse(int status, String message) {
      this.status = status;
      this.message = message;
    }

    // Getters and setters
    /**
     * Gets the HTTP status code.
     *
     * @return the HTTP status code
     */
    public int getStatus() {
      return status;
    }

    /**
     * Sets the HTTP status code.
     *
     * @param status the HTTP status code
     */
    public void setStatus(int status) {
      this.status = status;
    }

    /**
     * Gets the error message.
     *
     * @return the error message
     */
    public String getMessage() {
      return message;
    }


    /**
     * Sets the error message.
     *
     * @param message the error message
     */
    public void setMessage(String message) {
      this.message = message;
    }
  }
}

