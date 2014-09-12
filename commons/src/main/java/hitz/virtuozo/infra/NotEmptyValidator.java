package hitz.virtuozo.infra;

import hitz.virtuozo.infra.api.Validator;

public class NotEmptyValidator<T> extends Validator<NotEmptyValidator<T>, T> {

  public NotEmptyValidator() {
    this.nullable(false);
  }

  @Override
  public NotEmptyValidator<T> nullable(boolean nullable) {
    return super.nullable(false);
  }

  @Override
  protected boolean delegateValidation(T value) {
    return !SimpleValidator.isEmptyOrNull(value);
  }
}