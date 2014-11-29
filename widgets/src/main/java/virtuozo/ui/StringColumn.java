package virtuozo.ui;

import virtuozo.infra.HashObject;
import virtuozo.infra.JSArrays;
import virtuozo.infra.api.Format;

import com.google.gwt.core.client.JsArray;

public final class StringColumn<H extends HashObject> extends TextGridColumn<StringColumn<H>, H> {

  private Format<String> format;

  public StringColumn() {
    super();
  }

  public StringColumn(Format<String> format) {
    this.format = format;
  }

  public final String toString(H object) {
    String value = object.get(this.getName());
    if (this.format != null) {
      value = this.format.format(value);
    }

    return value;
  }

  @Override
  protected void doSort(JsArray<H> rows, String name, SortDirection direction) {
    JSArrays.sort(rows, name, direction.direction());
  }
}