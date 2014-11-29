package virtuozo.infra;

import virtuozo.infra.api.Format;

public class CEPFormat implements Format<String> {

  private static final CEPFormat instance = new CEPFormat();

  private CEPFormat() {
  }

  public static CEPFormat get() {
    return instance;
  }

  @Override
  public String format(String value) {
    if (value == null) {
      return null;
    }

    return new StringBuilder().append(value).insert(5, "-").toString();
  }

  @Override
  public String pattern() {
    return "99999-999";
  }

  @Override
  public String unformat(String value) {
    if (value == null) {
      return null;
    }

    return value.replace("-", "");
  }
}