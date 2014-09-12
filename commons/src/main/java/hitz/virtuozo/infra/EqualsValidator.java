package hitz.virtuozo.infra;

import hitz.virtuozo.infra.api.Validator;

public class EqualsValidator<T> extends Validator<EqualsValidator<T>, T> {

  private T value;

  public EqualsValidator(T value) {
    this.value = value;
  }

  @Override
  public boolean delegateValidation(T value) {
    if (this.value != null) {
      return this.value.equals(value);
    }

    return this.value == null && value == null;
  }
}