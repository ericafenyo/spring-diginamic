package com.diginamic.hellodigi.exceptions;

import java.time.LocalDateTime;

/**
 * Represents a response structure for HTTP exceptions.
 */
public class HttpExceptionResponse {

  /**
   * The HTTP status code associated with the response.
   */
  private int status;

  /**
   * A descriptive message providing additional information about the exception.
   */
  private Object message;

  /**
   * The timestamp when the exception response was created.
   */
  private LocalDateTime timestamp;

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Object getMessage() {
    return message;
  }

  public void setMessage(Object message) {
    this.message = message;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }
}
