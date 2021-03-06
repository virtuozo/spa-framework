package virtuozo.infra;


public class RegexValidator extends Validator<RegexValidator, String> {

  private String regex;

  public RegexValidator(String regex) {
    this.regex = regex;
  }

  @Override
  public boolean delegateValidation(String value) {
    return value.matches(this.regex);
  }
}