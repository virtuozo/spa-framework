package virtuozo.infra;

import java.math.BigInteger;

import virtuozo.infra.api.Format;

public class HexaFormat implements Format<byte[]> {

  private static final HexaFormat instance = new HexaFormat();

  public static HexaFormat get() {
    return instance;
  }

  @Override
  public String format(byte[] value) {
    if (value == null) {
      return null;
    }

    return new BigInteger(value).toString(16);
  }

  @Override
  public byte[] unformat(String value) {
    if (value == null) {
      return null;
    }

    return new BigInteger(value, 16).toByteArray();
  }

  @Override
  public String pattern() {
    return "";
  }
}