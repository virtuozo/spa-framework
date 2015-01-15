package virtuozo.infra;

import virtuozo.infra.api.Validator;

public class LengthValidator extends Validator<LengthValidator, String> {

  private Integer min;

  private Integer max;

  public static LengthValidator create(){
    return new LengthValidator();
  }
  
  private LengthValidator() {
    this.min = 0;
    this.max = Integer.MAX_VALUE;
  }

  public LengthValidator range(Integer min, Integer max) {
    this.min = min;
    this.max = max;
    return this;
  }

  @Override
  public boolean delegateValidation(String value) {
    Integer length = value.length();

    return length >= this.min && length <= this.max;
  }
}