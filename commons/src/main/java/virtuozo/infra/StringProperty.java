package virtuozo.infra;

import virtuozo.infra.api.Format;
import virtuozo.infra.api.Property;

public class StringProperty extends Property<String, StringProperty> {

  public StringProperty() {
    super();
  }

  public StringProperty(String value) {
    super(value);
  }

  public <V> StringProperty set(V value, Format<V> format) {
    return this.set(format.format(value));
  }

  public StringProperty capitalise() {
    char[] charArray = this.get().toCharArray();
    charArray[0] = Character.toUpperCase(charArray[0]);
    return this.set(new String(charArray));
  }

  public StringProperty uncapitalise() {
    char[] charArray = this.get().toCharArray();
    charArray[0] = Character.toLowerCase(charArray[0]);
    return this.set(new String(charArray));
  }

  public static <V> StringProperty valueOf(V value, Format<V> format) {
    return new StringProperty().set(format.format(value));
  }
}