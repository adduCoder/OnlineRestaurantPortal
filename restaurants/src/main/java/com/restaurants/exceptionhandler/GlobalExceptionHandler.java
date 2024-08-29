package com.restaurants.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler for the application.
 * This class handles exceptions thrown by controllers and returns appropriate
 * HTTP responses with error messages.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles {@link RestaurantNotFound} exceptions.
   *
   * @param ex the exception to handle
   * @return an {@link ErrorResponse} with status {@link HttpStatus#CONFLICT} and the exception message
   */
  @ExceptionHandler(RestaurantNotFound.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  @ResponseBody
  public ErrorResponse handleNoCustomerFound(RestaurantNotFound ex) {
    return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
  }

  @ExceptionHandler(CategoryNotFound.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  @ResponseBody
  public ErrorResponse handleCategoryNotFound(CategoryNotFound ex) {
    return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
  }

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

