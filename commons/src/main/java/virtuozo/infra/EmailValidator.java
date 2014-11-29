package virtuozo.infra;

public class EmailValidator extends RegexValidator {

  private static final EmailValidator instance = new EmailValidator();

  private EmailValidator() {
    super("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+[.][A-Za-z]{2,4}");
  }

  public static EmailValidator get() {
    return instance;
  }

  @Override
  public EmailValidator message(String message) {
    return (EmailValidator) super.message(message);
  }

  @Override
  public EmailValidator nullable(boolean nullable) {
    return (EmailValidator) super.nullable(nullable);
  }
}