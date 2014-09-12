package hitz.virtuozo.infra.api;

import hitz.virtuozo.infra.JSObject;

public interface ResponseHandler<T extends JSObject> {
  public void onResponse(T response);
}
