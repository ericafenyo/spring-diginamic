
package com.diginamic.hellodigi.exceptions;


import org.springframework.http.HttpStatus;

/**
 * Exception class representing HTTP-related errors.
 */
public class HttpException extends Exception {

  /**
   * The HTTP status associated with the exception.
   */
  private final HttpStatus status;

  /**
   * Constructs a new {@code HttpException} with the specified HTTP status, error message, and code.
   *
   * @param status  The HTTP status code representing the error.
   * @param message A descriptive error message.
   */
  public HttpException(HttpStatus status, String message) {
    super(message);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}

