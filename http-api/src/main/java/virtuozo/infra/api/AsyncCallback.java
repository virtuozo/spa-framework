package virtuozo.infra.api;

public interface AsyncCallback<T> {
  void onFailure(AsyncException exception);

  void onSuccess(T response);
}