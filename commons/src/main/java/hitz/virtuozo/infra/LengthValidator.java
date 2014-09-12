package hitz.virtuozo.infra;

import hitz.virtuozo.infra.api.Validator;

public class LengthValidator extends Validator<LengthValidator, String> {

  private Integer min;

  private Integer max;

  public LengthValidator() {
    this(0, Integer.MAX_VALUE);
  }

  public LengthValidator(Integer min, Integer max) {
    this.min = min;
    this.max = max;
  }

  @Override
  public boolean delegateValidation(String value) {
    Integer length = value.length();

    return length >= this.min && length <= this.max;
  }
}