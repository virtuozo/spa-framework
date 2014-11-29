package virtuozo.ui;

import java.util.Date;

import virtuozo.infra.HashObject;
import virtuozo.infra.JSArrays;
import virtuozo.infra.api.Format;

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