package hitz.virtuozo.ui;

import hitz.virtuozo.infra.JSArrays;
import hitz.virtuozo.infra.JSObject;
import hitz.virtuozo.infra.api.Format;

import java.util.Date;

import com.google.gwt.core.client.JsArray;

public final class DateColumn<J extends JSObject> extends TextGridColumn<DateColumn<J>, J> {

  private Format<Date> format;

  public DateColumn(Format<Date> format) {
    super();
    this.format = format;
  }

  public final String toString(J object) {
    return this.format.format(object.getDate(this.getName()));
  }

  @Override
  protected void doSort(JsArray<J> rows, String name, SortDirection direction) {
    JSArrays.sort(rows, name, direction.direction());
  }
}