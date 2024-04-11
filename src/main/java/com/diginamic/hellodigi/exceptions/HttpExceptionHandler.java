package com.diginamic.hellodigi.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Global exception handler for HTTP exceptions.
 * Annotated with {@code @ControllerAdvice} to allow centralized
 * handling of exceptions across multiple controllers.
 */
@ControllerAdvice
public class HttpExceptionHandler {

  /**
   * Handles instances of {@code HttpException} and generates a standardized response.
   *
   * @param exception The HTTP exception to be handled.
   * @return A {@link ResponseEntity} containing an {@link HttpExceptionResponse} with relevant information.
   */
  @ExceptionHandler(value = {HttpException.class})
  ResponseEntity<Object> handle(HttpException exception) {
    HttpExceptionResponse response = new HttpExceptionResponse();
    response.setStatus(exception.getStatus().value());
    response.setMessage(exception.getMessage());
    response.setTimestamp(LocalDateTime.now(ZoneOffset.UTC));

    return new ResponseEntity<>(response, exception.getStatus());
  }
}

