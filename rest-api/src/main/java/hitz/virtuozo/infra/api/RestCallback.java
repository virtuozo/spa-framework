package hitz.virtuozo.infra.api;

public interface RestCallback<T> {
  void onFailure(RestException exception);

  void onSuccess(T response);
}