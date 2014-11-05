package hitz.virtuozo.ui;

import hitz.virtuozo.infra.JSArrays;
import hitz.virtuozo.infra.HashObject;
import hitz.virtuozo.infra.api.Format;

import com.google.gwt.core.client.JsArray;

public final class BooleanColumn<H extends HashObject> extends TextGridColumn<BooleanColumn<H>, H> {

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

  public String toString(H object) {
    return this.format.format(object.getBoolean(this.getName()));
  }

  @Override
  protected void doSort(JsArray<H> rows, String name, SortDirection direction) {
    JSArrays.sortBoolean(rows, name, direction.direction());
  }
}