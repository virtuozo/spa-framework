package hitz.virtuozo.infra;

import hitz.virtuozo.infra.api.Validator;

public class LongValidator extends Validator<LongValidator, String> {

  private Long min;

  private Long max;

  public LongValidator() {
    super();
  }

  public LongValidator(Long min, Long max) {
    super();
    this.min = Long.MIN_VALUE;
    this.max = Long.MAX_VALUE;
  }

  protected boolean delegateValidation(String value) {
    return Long.valueOf(value) >= this.min && Long.valueOf(value) <= this.max;
  }
}