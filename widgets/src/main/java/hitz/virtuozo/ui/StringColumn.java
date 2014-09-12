package hitz.virtuozo.ui;

import hitz.virtuozo.infra.JSArrays;
import hitz.virtuozo.infra.JSObject;
import hitz.virtuozo.infra.api.Format;

import com.google.gwt.core.client.JsArray;

public final class StringColumn<J extends JSObject> extends TextGridColumn<StringColumn<J>, J> {

  private Format<String> format;

  public StringColumn() {
    super();
  }

  public StringColumn(Format<String> format) {
    this.format = format;
  }

  public final String toString(J object) {
    String value = object.get(this.getName());
    if (this.format != null) {
      value = this.format.format(value);
    }

    return value;
  }

  @Override
  protected void doSort(JsArray<J> rows, String name, SortDirection direction) {
    JSArrays.sort(rows, name, direction.direction());
  }
}