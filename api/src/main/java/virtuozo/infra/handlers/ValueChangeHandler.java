package virtuozo.infra.handlers;

public interface ValueChangeHandler<T> {
  void onChange(T oldValue, T newValue);
}