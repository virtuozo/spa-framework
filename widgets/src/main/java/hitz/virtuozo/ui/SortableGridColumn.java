package hitz.virtuozo.ui;

import hitz.virtuozo.infra.HashObject;
import hitz.virtuozo.ui.Table.Cell;
import hitz.virtuozo.ui.api.Assets;
import hitz.virtuozo.ui.api.Icon;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.shared.GWT;

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
    Cell cell = this.headerCell().css("sorted");
    direction.icon().appendTo(cell);
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
      Icon icon() {
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
      Icon icon() {
        return assets.downIcon();
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
      Icon icon() {
        return assets.upIcon();
      }
    };
    
    private static Assets assets = GWT.create(Assets.class);

    public abstract int direction();

    abstract SortDirection reverse();

    abstract Icon icon();
  }
}