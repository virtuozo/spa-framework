package virtuozo.infra.api;

public interface ValueChangeHandler<T> {
  void onChange(T oldValue, T newValue);
}
