package hitz.virtuozo.infra.api;

import hitz.virtuozo.infra.HttpStatusCode;

public class RestException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private HttpStatusCode statusCode = HttpStatusCode.NOT_MAPPED;

  public RestException(String message, HttpStatusCode statusCode, Throwable cause) {
    super(message, cause);
    this.statusCode = statusCode;
  }
  
  public RestException(String message, HttpStatusCode statusCode) {
    super(message);
    this.statusCode = statusCode;
  }
  
  public RestException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public RestException(HttpStatusCode statusCode, Throwable cause) {
    super(cause);
    this.statusCode = statusCode;
  }
  
  public RestException(HttpStatusCode statusCode) {
    super();
    this.statusCode = statusCode;
  }
  
  public RestException(Throwable cause) {
    super(cause);
  }

  public RestException(String message) {
    super(message);
  }

  public HttpStatusCode statusCode() {
    return statusCode;
  }
}