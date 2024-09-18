package com.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
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
 * <p>
 * This class handles various exceptions thrown by controllers and returns appropriate
 * HTTP responses with error messages. It includes specific handlers for different types
 * of exceptions and a general handler for unforeseen errors.
 * </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {



  /**
   * Handles {@link OrderNotFoundException} exceptions.
   * <p>
   * This exception is thrown when an order is not found in the system.
   * </p>
   *
   * @param ex the {@link OrderNotFoundException} exception to handle
   * @return an {@link ErrorResponse} with status {@link HttpStatus#NOT_FOUND}
   */
  @ExceptionHandler(OrderNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorResponse handleOrderNotFound(final OrderNotFoundException ex) {
    return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
  }

  /**
   * Handles {@link SessionExpiredException} exceptions.
   * <p>
   * This exception is thrown when a user session has expired.
   * </p>
   *
   * @param ex the {@link SessionExpiredException} exception to handle
   * @return an {@link ErrorResponse} with status {@link HttpStatus#NOT_FOUND}
   */
  @ExceptionHandler(SessionExpiredException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorResponse handleSessionExpiredException(final SessionExpiredException ex) {
    return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
  }

  /**
   * Handles {@link InsufficientBalanceException} exceptions.
   * <p>
   * This exception is thrown when a user has insufficient balance.
   * </p>
   *
   * @param ex the {@link InsufficientBalanceException} exception to handle
   * @return an {@link ErrorResponse} with status {@link HttpStatus#NOT_FOUND}
   */
  @ExceptionHandler(InsufficientBalanceException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorResponse handleInsufficientBalance(final InsufficientBalanceException ex) {
    return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
  }

  /**
   * Handles {@link InvalidOperationException} exceptions.
   * <p>
   * This exception is thrown when an invalid operation is attempted.
   * </p>
   *
   * @param ex the {@link InvalidOperationException} exception to handle
   * @return an {@link ErrorResponse} with status {@link HttpStatus#FORBIDDEN}
   */

  @ExceptionHandler(InvalidOperationException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ResponseBody
  public ErrorResponse handleInvalidOperation(final InvalidOperationException ex) {
    return new ErrorResponse(HttpStatus.FORBIDDEN.value(), ex.getMessage());
  }

  /**
   * Handles {@link UserNotFoundException} exceptions.
   * <p>
   * This exception is thrown when a user is not found in the system.
   * </p>
   *
   * @param ex the {@link UserNotFoundException} exception to handle
   * @return an {@link ErrorResponse} with status {@link HttpStatus#CONFLICT}
   */
  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  @ResponseBody
  public ErrorResponse handleCategoryAlreadyExists(final UserNotFoundException ex) {
    return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
  }

  /**
   * Handles {@link CartNotFoundException} exceptions.
   * <p>
   * This exception is thrown when a cart is not found in the system.
   * </p>
   *
   * @param ex the {@link CartNotFoundException} exception to handle
   * @return an {@link ErrorResponse} with status {@link HttpStatus#NOT_FOUND}
   */
  @ExceptionHandler(CartNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorResponse handleCartNotFoundException(final CartNotFoundException ex) {
    return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
  }

  /**
   * Handles {@link MethodArgumentNotValidException} and {@link BindException} exceptions.
   * <p>
   * These exceptions are typically thrown when validation of method arguments fails.
   * </p>
   *
   * @param ex the {@link Exception} to handle (either {@link MethodArgumentNotValidException} or {@link BindException})
   * @return an {@link ErrorResponse} with status {@link HttpStatus#BAD_REQUEST} and a list of validation error messages
   */
  @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse handleValidationExceptions(final Exception ex) {
    List<String> errorMessages = null;

    if (ex instanceof MethodArgumentNotValidException) {
      errorMessages = ((MethodArgumentNotValidException) ex)
        .getBindingResult()
        .getFieldErrors()
        .stream()
        .map(FieldError::getDefaultMessage)
        .collect(Collectors.toList());
    } else if (ex instanceof BindException) {
      errorMessages = ((BindException) ex)
        .getBindingResult()
        .getFieldErrors()
        .stream()
        .map(FieldError::getDefaultMessage)
        .collect(Collectors.toList());
    }

    String errorMessage = errorMessages != null ? String.join(", ", errorMessages) : "Validation error";
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
  }

  /**
   * Handles {@link MethodArgumentNotValidException} and {@link BindException} exceptions.
   * <p>
   * These exceptions are typically thrown when validation of method arguments fails.
   * </p>
   *
   * @param ex the {@link Exception} to handle (either {@link MethodArgumentNotValidException} or {@link BindException})
   * @return an {@link ErrorResponse} with status {@link HttpStatus#BAD_REQUEST} and a list of validation error messages
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Map<String, String>> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex) {
    Map<String, String> errors = new HashMap<>();
    String message = ex.getMessage();

    if (message.contains("InvalidFormatException")) {
      errors.put("error", "Invalid data format provided.");
    } else {
      errors.put("error", "Required request body is missing or malformed.");
    }

    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles general exceptions.
   * <p>
   * This exception handler is used for unforeseen errors that do not fall into other specific categories.
   * </p>
   *
   * @param ex the general {@link Exception} to handle
   * @return an {@link ErrorResponse} with status {@link HttpStatus#INTERNAL_SERVER_ERROR}
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public ErrorResponse handleGeneralException(final Exception ex) {
    String message = "An unexpected error occurred. Please try again later or contact support.";
    return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
  }


  /**
   * Class representing the error response returned by the exception handlers.
   */
  public static class ErrorResponse {
    /**
     * The HTTP status code of the error response.
     */
    private int status;

    /**
     * The error message to be included in the response.
     */
    private String message;


    /**
     * Constructs an {@link ErrorResponse} with the specified status and message.
     *
     * @param status  the HTTP status code
     * @param message the error message
     */
    public ErrorResponse(final int status, final String message) {
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
    public void setStatus(final int status) {
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
    public void setMessage(final String message) {
      this.message = message;
    }
  }

}

