package virtuozo.infra;


public class CPFFormat implements Format<String> {

  private static final CPFFormat instance = new CPFFormat();

  private CPFFormat() {
  }

  public static CPFFormat get() {
    return instance;
  }

  @Override
  public String format(String value) {
    if (value == null) {
      return null;
    }

    return new StringBuilder().append(value).insert(3, ".").insert(7, ".").insert(11, "-").toString();
  }

  @Override
  public String pattern() {
    return "999.999.999-99";
  }

  @Override
  public String unformat(String value) {
    if (value == null) {
      return null;
    }

    return value.replace(".", "").replace("-", "");
  }
}