package virtuozo.infra.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
public abstract class Property<T, P extends Property<T, P>> {

  private T value;

  private final List<ValueChangeHandler<T>> listeners = new ArrayList<ValueChangeHandler<T>>();

  private final List<Validator<?, T>> validators = new ArrayList<Validator<?, T>>();

  public Property() {
    super();
  }

  public Property(T value) {
    this.value = value;
  }

  public <V> void set(V value, Converter<V, T> converter) {
    this.set(converter.convert(value));
  }

  public P set(T value) {
    this.value = value;
    for (Validator<?, T> validator : this.validators) {
      if (!validator.validate(value)) {
        throw new IllegalArgumentException(value + " is a invalid value to this property.");
      }
    }

    for (ValueChangeHandler<T> listener : this.listeners) {
      listener.onChange(this.value, value);
    }
    return (P) this;
  }

  public T get() {
    return this.value;
  }

  public P validator(Validator<?, T>... validator) {
    this.validators.addAll(Arrays.asList(validator));
    return (P) this;
  }

  public P onChange(ValueChangeHandler<T> listener) {
    this.listeners.add(listener);
    return (P) this;
  }
}