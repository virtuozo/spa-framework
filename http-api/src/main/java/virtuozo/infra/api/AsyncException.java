package virtuozo.infra.api;

import virtuozo.infra.HttpStatusCode;

public class AsyncException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private HttpStatusCode statusCode = HttpStatusCode.NOT_MAPPED;

  public AsyncException(String message, HttpStatusCode statusCode, Throwable cause) {
    super(message, cause);
    this.statusCode = statusCode;
  }
  
  public AsyncException(String message, HttpStatusCode statusCode) {
    super(message);
    this.statusCode = statusCode;
  }
  
  public AsyncException(String message, Throwable cause) {
    super(message, cause);
  }
  
  public AsyncException(HttpStatusCode statusCode, Throwable cause) {
    super(cause);
    this.statusCode = statusCode;
  }
  
  public AsyncException(HttpStatusCode statusCode) {
    super();
    this.statusCode = statusCode;
  }
  
  public AsyncException(Throwable cause) {
    super(cause);
  }

  public AsyncException(String message) {
    super(message);
  }

  public HttpStatusCode statusCode() {
    return statusCode;
  }
}