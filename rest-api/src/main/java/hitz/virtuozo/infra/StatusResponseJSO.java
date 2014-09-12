package hitz.virtuozo.infra;

import hitz.virtuozo.infra.JSObject;
import hitz.virtuozo.infra.ResponseStatus;

public class StatusResponseJSO extends JSObject {

  private static final String STATUS = "status";

  private static final String MESSAGECODE = "messageCode";

  protected StatusResponseJSO() {
  }

  public final ResponseStatus status() {
    return ResponseStatus.valueOf(this.getInt(STATUS));
  }

  public final String messageCode() {
    return this.get(MESSAGECODE);
  }
}