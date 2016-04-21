package virtuozo.infra;


public class DoubleValidator extends Validator<DoubleValidator, String> {

  private Double min;

  private Double max;

  public DoubleValidator() {
    super();
  }

  public DoubleValidator(Double min, Double max) {
    super();
    this.min = Double.MIN_VALUE;
    this.max = Double.MAX_VALUE;
  }

  public boolean delegateValidation(String value) {
    return Double.valueOf(value) >= this.min && Double.valueOf(value) <= this.max;
  }
}