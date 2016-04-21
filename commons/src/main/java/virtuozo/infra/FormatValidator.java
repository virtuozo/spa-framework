package virtuozo.infra;


public class FormatValidator<V> extends Validator<FormatValidator<V>, String> {

  private Format<V> format;

  public FormatValidator(Format<V> format) {
    super();
    this.format = format;
  }

  @Override
  protected boolean delegateValidation(String value) {
    try {
      this.format.unformat(value);
      return true;
    } catch (IllegalArgumentException e) {
      return false;
    }
  }
}
