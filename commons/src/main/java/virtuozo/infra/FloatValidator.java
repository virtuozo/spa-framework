package virtuozo.infra;


public class FloatValidator extends Validator<FloatValidator, String> {

  private Float min;

  private Float max;

  public FloatValidator() {
    super();
  }

  public FloatValidator(Float min, Float max) {
    super();
    this.min = Float.MIN_VALUE;
    this.max = Float.MAX_VALUE;
  }

  public boolean delegateValidation(String value) {
    return Float.valueOf(value) >= this.min && Float.valueOf(value) <= this.max;
  }
}