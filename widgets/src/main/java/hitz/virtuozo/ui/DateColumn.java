package hitz.virtuozo.ui;

import hitz.virtuozo.infra.JSArrays;
import hitz.virtuozo.infra.HashObject;
import hitz.virtuozo.infra.api.Format;

import java.util.Date;

import com.google.gwt.core.client.JsArray;

public final class DateColumn<H extends HashObject> extends TextGridColumn<DateColumn<H>, H> {

  private Format<Date> format;

  public DateColumn(Format<Date> format) {
    super();
    this.format = format;
  }

  public final String toString(H object) {
    return this.format.format(object.getDate(this.getName()));
  }

  @Override
  protected void doSort(JsArray<H> rows, String name, SortDirection direction) {
    JSArrays.sort(rows, name, direction.direction());
  }
}