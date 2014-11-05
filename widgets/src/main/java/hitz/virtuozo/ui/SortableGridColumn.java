package hitz.virtuozo.ui;

import hitz.virtuozo.infra.HashObject;
import hitz.virtuozo.ui.Glyphicon;

import com.google.gwt.core.client.JsArray;

@SuppressWarnings("unchecked")
public abstract class SortableGridColumn<T extends SortableGridColumn<T, H>, H extends HashObject> extends GridColumn<T, H> {

  private SortDirection direction = SortDirection.NONE;

  public SortableGridColumn() {
    this.sortable();
  }

  @Override
  public T unsort() {
    this.headerCell().css().remove("sorted");//.icon(Icon.NONE);
    return (T) this;
  }

  @Override
  public T sort(JsArray<H> rows) {
    this.direction = this.direction.reverse();

    this.mark(this.direction);

    this.doSort(rows, this.getName(), this.direction);

    return (T) this;
  }

  public T mark(SortDirection direction) {
    this.headerCell().css("sorted");//.icon(this.direction.icon());
    return (T) this;
  }

  protected abstract void doSort(JsArray<H> rows, String name, SortDirection direction);

  public enum SortDirection {
    NONE {

      @Override
      public int direction() {
        return 0;
      }

      @Override
      SortDirection reverse() {
        return ASC;
      }

      @Override
      Glyphicon icon() {
        return null;
      }
    },
    DESC {

      @Override
      public int direction() {
        return -1;
      }

      @Override
      SortDirection reverse() {
        return ASC;
      }

      @Override
      Glyphicon icon() {
        return Glyphicon.CHEVRON_DOWN;
      }
    },
    ASC {

      @Override
      public int direction() {
        return 1;
      }

      @Override
      SortDirection reverse() {
        return DESC;
      }

      @Override
      Glyphicon icon() {
        return Glyphicon.CHEVRON_UP;
      }
    };

    public abstract int direction();

    abstract SortDirection reverse();

    abstract Glyphicon icon();
  }
}