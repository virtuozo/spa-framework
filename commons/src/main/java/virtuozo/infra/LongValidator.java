package virtuozo.infra;


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