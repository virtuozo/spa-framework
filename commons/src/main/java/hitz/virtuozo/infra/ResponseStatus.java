package hitz.virtuozo.infra;

public enum ResponseStatus {
  END_POOLING(204), FAILURE(500), FORBIDDEN(403), UNAUTHORIZED(401), BAD_REQUEST(400), TOKEN_EXPIRED(205), SUCCESS(200), UNKNOW(-1);

  private int code;

  private ResponseStatus(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public static ResponseStatus valueOf(int code) {
    for (ResponseStatus status : values()) {
      if (status.getCode() == code) {
        return status;
      }
    }

    return UNKNOW;
  }
}