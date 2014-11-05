package hitz.virtuozo.application;

import hitz.virtuozo.infra.HashObject;
import hitz.virtuozo.infra.HttpStatusCode;

public class StatusResponseJSO extends HashObject {

  private static final String STATUS = "status";

  private static final String MESSAGECODE = "messageCode";

  protected StatusResponseJSO() {
  }

  public final HttpStatusCode status() {
    return HttpStatusCode.valueOf(this.getInt(STATUS));
  }

  public final String messageCode() {
    return this.get(MESSAGECODE);
  }
}