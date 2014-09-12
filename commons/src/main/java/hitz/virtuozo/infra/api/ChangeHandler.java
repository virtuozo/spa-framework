package hitz.virtuozo.infra.api;

public interface ChangeHandler<T> {
  void onChange(T oldValue, T newValue);
}
