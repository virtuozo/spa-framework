package hitz.virtuozo.infra.api;

import hitz.virtuozo.infra.ResponseStatus;

public interface FailureHandler {
  void onFailure(ResponseStatus status, String messageCode);
}
