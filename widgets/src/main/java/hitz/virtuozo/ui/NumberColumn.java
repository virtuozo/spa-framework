package hitz.virtuozo.ui;

import hitz.virtuozo.infra.JSArrays;
import hitz.virtuozo.infra.HashObject;
import hitz.virtuozo.infra.api.Format;

import com.google.gwt.core.client.JsArray;

public final class NumberColumn<H extends HashObject> extends TextGridColumn<NumberColumn<H>, H> {

  private Format<Number> format;

  public NumberColumn() {
    this(new DefaultNumber());
  }

  public NumberColumn(Format<Number> format) {
    super();
    this.format = format;
  }

  public final String toString(H object) {
    return this.format.format(object.getDouble(this.getName()));
  }

  @Override
  protected void doSort(JsArray<H> rows, String name, SortDirection direction) {
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