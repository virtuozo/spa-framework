package hitz.virtuozo.ui;

import hitz.virtuozo.infra.JSArrays;
import hitz.virtuozo.infra.JSObject;
import hitz.virtuozo.infra.api.Format;

import com.google.gwt.core.client.JsArray;

public final class BooleanColumn<J extends JSObject> extends TextGridColumn<BooleanColumn<J>, J> {

  private Format<Boolean> format;

  public BooleanColumn() {
    this(new Format<Boolean>() {

      @Override
      public Boolean unformat(String value) {
        return Boolean.valueOf(value);
      }

      @Override
      public String pattern() {
        return "true|false";
      }

      @Override
      public String format(Boolean value) {
        return String.valueOf(value);
      }
    });
  }

  public BooleanColumn(Format<Boolean> format) {
    this.format = format;
    this.sortable();
  }

  public String toString(J object) {
    return this.format.format(object.getBoolean(this.getName()));
  }

  @Override
  protected void doSort(JsArray<J> rows, String name, SortDirection direction) {
    JSArrays.sortBoolean(rows, name, direction.direction());
  }
}