package virtuozo.infra;

import virtuozo.infra.api.Validator;

public class NotEmptyValidator<T> extends Validator<NotEmptyValidator<T>, T> {

  public static <T> NotEmptyValidator<T> create(){
    return new NotEmptyValidator<T>();
  }
  
  private NotEmptyValidator() {
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