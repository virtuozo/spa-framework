package virtuozo.infra;

import java.util.Collection;
import java.util.Map;

public class SimpleValidator {

  public static boolean isEmptyOrNull(Collection<?> collection) {
    return collection == null || collection.isEmpty();
  }

  public static boolean isEmptyOrNull(Object[] value) {
    return value == null || value.length == 0;
  }

  public static boolean isEmptyOrNull(String value) {
    return (value == null) || value.isEmpty();
  }

  public static boolean isEmptyOrNullOrZero(String value) {
    return SimpleValidator.isEmptyOrNull(value) || "0".equals(value);
  }

  public static boolean isEmptyOrNull(Object value) {
    if (value == null) {
      return true;
    }

    if (value instanceof Number) {
      return SimpleValidator.isZero((Number) value);
    }

    if (value instanceof String) {
      return SimpleValidator.isEmptyOrNull((String) value);
    }

    if (value instanceof Collection) {
      return ((Collection<?>) value).isEmpty();
    }

    if (value instanceof Map) {
      return ((Map<?, ?>) value).isEmpty();
    }

    return false;
  }

  public static boolean isTrue(Boolean value) {
    return value != null && value;
  }

  public static boolean isZero(Number number) {
    return number != null && number.doubleValue() == 0.0;
  }

  public static boolean isInt(String value) {
    try {
      Integer.parseInt(value);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static boolean isDouble(String value) {
    try {
      Double.parseDouble(value);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static boolean isZero(String value) {
    return value.equals("0");
  }

  public static boolean isZeroMod(int value, int mod) {
    return (value % mod) == 0;
  }

  public static boolean isInRange(byte value, byte min, byte max) {
    return ((value >= min) && (value <= max));
  }

  public static boolean isInRange(int value, int min, int max) {
    return ((value >= min) && (value <= max));
  }

  public static boolean isInRange(float value, float min, float max) {
    return ((value >= min) && (value <= max));
  }

  public static boolean isInRange(short value, short min, short max) {
    return ((value >= min) && (value <= max));
  }

  public static boolean isInRange(long value, long min, long max) {
    return ((value >= min) && (value <= max));
  }

  public static boolean isInRange(double value, double min, double max) {
    return ((value >= min) && (value <= max));
  }

  public static boolean maxLength(String value, int max) {
    return (value.length() <= max);
  }

  public static boolean maxLength(String value, int max, int lineEndLength) {
    int adjustAmount = adjustForLineEnding(value, lineEndLength);
    return ((value.length() + adjustAmount) <= max);
  }

  public static boolean minLength(String value, int min) {
    return (value.length() >= min);
  }

  public static boolean minLength(String value, int min, int lineEndLength) {
    int adjustAmount = adjustForLineEnding(value, lineEndLength);
    return ((value.length() + adjustAmount) >= min);
  }

  private static int adjustForLineEnding(String value, int lineEndLength) {
    int nCount = 0;
    int rCount = 0;
    for (int i = 0; i < value.length(); i++) {
      if (value.charAt(i) == '\n') {
        nCount++;
      }
      if (value.charAt(i) == '\r') {
        rCount++;
      }
    }
    return ((nCount * lineEndLength) - (rCount + nCount));
  }

  public static boolean minValue(int value, int min) {
    return (value >= min);
  }

  public static boolean minValue(long value, long min) {
    return (value >= min);
  }

  public static boolean minValue(double value, double min) {
    return (value >= min);
  }

  public static boolean minValue(float value, float min) {
    return (value >= min);
  }

  public static boolean maxValue(int value, int max) {
    return (value <= max);
  }

  public static boolean maxValue(long value, long max) {
    return (value <= max);
  }

  public static boolean maxValue(double value, double max) {
    return (value <= max);
  }

  public static boolean maxValue(float value, float max) {
    return (value <= max);
  }
}