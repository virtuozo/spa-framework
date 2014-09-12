package hitz.virtuozo.infra;

import hitz.virtuozo.infra.api.Validator;

public class IntegerValidator extends Validator<IntegerValidator, String> {

  private Integer min;

  private Integer max;

  public IntegerValidator() {
    this(Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  public IntegerValidator(Integer min, Integer max) {
    super();
    this.min = min;
    this.max = max;
  }

  public boolean delegateValidation(String value) {
    return Integer.valueOf(value) >= this.min && Integer.valueOf(value) <= this.max;
  }
}