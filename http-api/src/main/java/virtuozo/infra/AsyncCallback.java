package virtuozo.infra;

public interface AsyncCallback<T> {
  void onFailure(AsyncException exception);

  void onSuccess(T response);
}