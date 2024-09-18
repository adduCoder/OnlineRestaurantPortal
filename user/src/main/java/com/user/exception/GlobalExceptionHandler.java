package com.user.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;

import java.util.HashMap;
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
  public Map<String, Object> handleValidationExceptions(final MethodArgumentNotValidException ex) {
    Map<String, Object> errorResponse = new HashMap<>();
    Map<String, String> fieldErrors = ex.getBindingResult()
      .getFieldErrors()
      .stream()
      .collect(Collectors.toMap(
        FieldError::getField,
        FieldError::getDefaultMessage,
        (existing, replacement) -> existing
      ));

    errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
    errorResponse.put("errors", fieldErrors);

    return errorResponse;
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
  public ErrorResponse userAlreadyExisted(final UserAlreadyExisted ex) {
    return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
  }

  /**
   * Handles {@link NotFoundException} exceptions.
   * This exception is thrown when a requested resource is not found.
   *
   * @param ex the exception to handle
   * @return an {@link ErrorResponse} with status {@link HttpStatus#NOT_FOUND} and the exception message
   */
  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorResponse handleNoAddressFound(final NotFoundException ex) {
    return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
  }



  /**
   * Handles {@link HttpMessageNotReadableException} exceptions.
   * This exception is thrown when the request payload cannot be read or parsed.
   *
   * @param ex the exception to handle
   * @return a {@link ResponseEntity} containing an error message and HTTP status
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Map<String, String>> handleMessageNotReadable(final HttpMessageNotReadableException ex) {
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
   * Handles general {@link Exception} instances.
   * This method handles unexpected exceptions that are not specifically handled by other methods.
   *
   * @param ex the exception to handle
   * @return an {@link ErrorResponse} with status {@link HttpStatus#INTERNAL_SERVER_ERROR} and a general error message
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
     * Gets the HTTP status code.
     *
     * @return the HTTP status code
     */
    private int status;
    /**
     * Sets the HTTP status code.
     *
     * @param status the HTTP status code to set
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

