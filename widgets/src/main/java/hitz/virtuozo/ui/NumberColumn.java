package hitz.virtuozo.ui;

import hitz.virtuozo.infra.JSArrays;
import hitz.virtuozo.infra.JSObject;
import hitz.virtuozo.infra.api.Format;

import com.google.gwt.core.client.JsArray;

public final class NumberColumn<J extends JSObject> extends TextGridColumn<NumberColumn<J>, J> {

  private Format<Number> format;

  public NumberColumn() {
    this(new DefaultNumber());
  }

  public NumberColumn(Format<Number> format) {
    super();
    this.format = format;
  }

  public final String toString(J object) {
    return this.format.format(object.getDouble(this.getName()));
  }

  @Override
  protected void doSort(JsArray<J> rows, String name, SortDirection direction) {
    JSArrays.sortNumber(rows, name, direction.direction());
  }

  static class DefaultNumber implements Format<Number> {

    @Override
    public String format(Number value) {
      if (value == null) {
        return null;
      }

      return String.valueOf(value.intValue());
    }

    @Override
    public Number unformat(String value) {
      if (value == null) {
        return null;
      }

      return Double.valueOf(value);
    }

    @Override
    public String pattern() {
      return "toString()";
    }
  }
}