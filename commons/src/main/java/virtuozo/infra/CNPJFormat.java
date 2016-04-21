package virtuozo.infra;


public class CNPJFormat implements Format<String> {

  private static final CNPJFormat format = new CNPJFormat();

  private CNPJFormat() {
  }

  public static CNPJFormat get() {
    return format;
  }

  @Override
  public String format(String value) {
    if (value == null) {
      return null;
    }

    return new StringBuilder().append(value.trim()).insert(2, ".").insert(6, ".").insert(10, "/").insert(15, "-").toString();
  }

  @Override
  public String pattern() {
    return "99.999.999/9999-99";
  }

  @Override
  public String unformat(String value) {
    if (value == null) {
      return null;
    }

    return value.replace(".", "").replace("-", "").replace("/", "").trim();
  }
}