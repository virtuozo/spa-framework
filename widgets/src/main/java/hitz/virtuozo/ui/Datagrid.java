/**
 * Copyright (C) 2004-2014 the original author or authors. See the notice.md file distributed with
 * this work for additional information regarding copyright ownership.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package hitz.virtuozo.ui;

import hitz.virtuozo.infra.HashObject;
import hitz.virtuozo.infra.JSArrays;
import hitz.virtuozo.ui.DrawEvent.DrawHandler;
import hitz.virtuozo.ui.SelectionEvent.SelectionHandler;
import hitz.virtuozo.ui.Table.Cell;
import hitz.virtuozo.ui.Table.Row;
import hitz.virtuozo.ui.api.Assets;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public final class Datagrid<H extends HashObject> extends Component<Datagrid<H>> {

  private java.util.List<GridFilter<H>> filters = new ArrayList<GridFilter<H>>();

  private java.util.List<GridColumn<?, H>> columns = new ArrayList<GridColumn<?, H>>();
  
  private List<Integer> filteredRows = new ArrayList<Integer>();

  private Table table = new Table().bordered();

  private GridCaption caption;

  private Row headerColumns;

  private DataGridOptions options;

  private GridFooter footer;

  private JsArray<H> rows;

  private int totalRows;
  
  public Datagrid() {
    this.incorporate(this.table);
    this.css("datagrid");
    
    this.caption = new GridCaption(this.table.header().addRow());
    this.headerColumns = this.table.header().addRow();
    this.footer = new GridFooter(this.table.footer().addRow());
  }

  public Datagrid<H> onDraw(DrawHandler handler) {
    return this.addHandler(DrawEvent.type(), handler);
  }

  public Datagrid<H> onSelection(SelectionHandler<H> handler) {
    return this.addHandler(SelectionEvent.type(), handler);
  }

  public Datagrid<H> add(final GridColumn<?, H> column) {
    column.headerCell(this.headerColumns.addCell());
    column.onClick(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        Datagrid.this.sort(column);
      }
    });

    this.columns.add(column);

    int colspan = this.columns.size();
    this.caption.colspan(colspan);
    this.footer.colspan(colspan);

    return this;
  }

  public void add(GridFilter<H>... filters) {
    for (GridFilter<H> filter : filters) {
      this.filters.add(filter);
    }
  }

  public DataGridOptions options() {
    if (this.options == null) {
      this.options = new DataGridOptions();
    }

    return this.options;
  }

  public Datagrid<H> sort(GridColumn<?, H> column) {
    for (GridColumn<?, H> gc : this.columns) {
      gc.unsort();
    }

    this.footer.pager.reset();
    column.sort(this.rows);
    this.drawPage(1);

    return this;
  }

  public Datagrid<H> filter() {
    boolean apply;
    boolean inactive = true;
    int totalRowsFiltered = 0;

    this.footer.pager.reset();
    this.filteredRows.clear();
    
    for (int i = 0; i < this.rows.length(); i++) {
      H row = this.rows.get(i);
      //row.set(DataGrid.SKIP, false);
      apply = true;

      for (GridFilter<H> filter : this.filters) {
        if (!filter.isActive()) {
          continue;
        }

        inactive = false;
        apply = filter.filter(row) && apply;
        //row.set(DataGrid.SKIP, !apply);
        if (!apply) {
          this.filteredRows.add(i);
          break;
        }
      }

      if (apply) {
        totalRowsFiltered++;
      }
    }

    this.totalRows = totalRowsFiltered;
    if (inactive) {
      this.totalRows = this.rows.length();
    }
    this.drawPage(1);

    return this;
  }

  public int indexOf(H object) {
    return JSArrays.indexOf(object, this.rows);
  }

  public Datagrid<H> remove(int rowIndex) {
    JSArrays.remove(this.rows, rowIndex);

    return this.draw(rows);
  }

  public Datagrid<H> refresh(int rowIndex, H object) {
    if (rowIndex == -1) {
      rowIndex = this.rows.length();
    }
    this.rows.set(rowIndex, object);

    return this.draw(this.rows);
  }

  public Datagrid<H> draw(JsArray<H> rows) {
    this.rows = rows;
    this.totalRows = rows.length();
    this.drawPage(1);
    
    this.fireEvent(new DrawEvent());

    return this;
  }

  void drawPage(int page) {
    int rowsPerPage = this.footer.rowsPerPage.value();

    int pages = this.totalRows / rowsPerPage;
    if (this.totalRows % rowsPerPage > 0) {
      pages++;
    }

    this.footer.pager.pages(pages);

    int numberOfRows = this.footer.rowsPerPage.value();
    int rowIndex = (page - 1) * numberOfRows;
    numberOfRows = rowIndex + numberOfRows;

    this.table.body().detachChildren();

    int index = 0;
    int objectIndex = -1;

    while (rowIndex < numberOfRows) {

      if (rowIndex == this.totalRows) {
        break;
      }

      if (this.filteredRows.contains(index)) {
        continue;
      }
      
      final H object = this.rows.get(index++);

      objectIndex++;
      if (objectIndex < rowIndex) {
        continue;
      }

      rowIndex++;

      Row row = this.table.body().addRow().css("highlight");
      row.onClick(new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
          fireEvent(new SelectionEvent<H>(object));
        }
      });

      for (GridColumn<?, H> column : this.columns) {
        column.render((index - 1), row.addCell(), object);
      }
    }

    String emptyMessage = this.options.emptyMessage;
    if (this.totalRows == 0 && emptyMessage != null) {
      Row row = this.table.body().addRow().css(Table.Color.WARNING);
      row.addCell().colspan(this.columns.size()).add(new Paragraph().text(emptyMessage));
    }
  }

  public class DataGridOptions {

    private String emptyMessage;

    public DataGridOptions emptyMessage(String emptyMessage) {
      this.emptyMessage = emptyMessage;
      return this;
    }

    public DataGridOptions title(String title) {
      Datagrid.this.caption.title.text(title);
      return this;
    }

    public DataGridOptions searchPlaceholder(String placeholder) {
      Datagrid.this.caption.search.input.placeholder(placeholder);
      return this;
    }

    public DataGridOptions searchMode(GridSearchMode mode) {
      Datagrid.this.caption.search.mode(mode);
      return this;
    }

    public DataGridOptions disableFilter() {
      Datagrid.this.caption.search.disable();
      return this;
    }

    public DataGridOptions enableFilter() {
      Datagrid.this.caption.search.enable();
      return this;
    }

    public DataGridOptions disableCaption() {
      Datagrid.this.caption.hide();
      return this;
    }

    public DataGridOptions enableCaption() {
      Datagrid.this.caption.show();
      return this;
    }

    public VerticalSpinner rowsPerPage() {
      return Datagrid.this.footer.rowsPerPage;
    }
  }

  public enum GridSearchMode {
    CONTAINS {

      @Override
      public boolean eval(String search, String text) {
        return text.contains(search);
      }
    },
    EQUALS {

      @Override
      public boolean eval(String search, String text) {
        return search.equals(text);
      }
    },
    EQUALS_IGNORE_CASE {

      @Override
      public boolean eval(String search, String text) {
        return search.equalsIgnoreCase(text);
      }
    },
    STARTS_WITH {

      @Override
      public boolean eval(String search, String text) {
        return text.startsWith(search);
      }
    },
    ENDS_WITH {

      @Override
      public boolean eval(String search, String text) {
        return text.endsWith(search);
      }
    };

    public abstract boolean eval(String search, String text);
  }

  class GridCaption {

    private Cell cell;

    private Tag<SpanElement> title = Tag.asSpan().css("datagrid-header-title");

    private Tag<DivElement> left = Tag.asDiv().css("datagrid-header-left");

    private GridSearch search = new GridSearch();

    public GridCaption(Row row) {
      super();
      this.init(row.addCell());
    }

    private void init(Cell cell) {
      this.cell = cell;
      this.cell.add(this.title).add(this.left).add(this.search);
    }

    GridCaption colspan(int colspan) {
      this.cell.colspan(colspan);
      return this;
    }

    GridCaption hide() {
      this.cell.hide();
      return this;
    }

    GridCaption show() {
      this.cell.show();
      return this;
    }

    class GridSearch extends Component<GridSearch> {
      
      private Assets assets = GWT.create(Assets.class);

      private InputGroup input = new InputGroup(new InputText()).css("datagrid-search");

      private Button searchButton = new Button();

      private Button clearButton = new Button();

      private GridFilter<H> filter = new DefaultGridFilter();

      private GridSearchMode mode = GridSearchMode.CONTAINS;

      public GridSearch() {
        super(Elements.div());
        this.searchButton.icon(this.assets.searchIcon());
        this.clearButton.icon(this.assets.clearIcon());
        this.init();
      }

      @SuppressWarnings("unchecked")
      private void init() {
        this.css("datagrid-header-right").addChild(this.input);
        this.input.append(this.searchButton).append(this.clearButton.hide());
        this.input.onChange(new ChangeHandler() {

          @Override
          public void onChange(ChangeEvent event) {
            GridSearch.this.search();
          }
        });

        this.searchButton.onClick(new ClickHandler() {

          @Override
          public void onClick(ClickEvent event) {
            GridSearch.this.search();
          }
        });

        this.clearButton.onClick(new ClickHandler() {

          @Override
          public void onClick(ClickEvent event) {
            GridSearch.this.input.value("");
            GridSearch.this.searchButton.show();
            GridSearch.this.clearButton.hide();
            GridSearch.this.filter.deactivate();
            Datagrid.this.filter();
          }
        });

        Datagrid.this.add(this.filter);
      }

      GridSearch mode(GridSearchMode mode) {
        this.mode = mode;
        return this;
      }

      GridSearch disable() {
        this.input.hide();
        this.searchButton.hide();
        this.clearButton.hide();

        return this;
      }

      GridSearch enable() {
        this.input.show();
        this.searchButton.show();
        this.clearButton.hide();

        return this;
      }

      GridSearch search() {
        this.searchButton.hide();
        this.clearButton.show();
        this.filter.activate();
        Datagrid.this.filter();

        return this;
      }
    }
  }

  class GridFooter {

    private Cell cell;

    private VerticalSpinner rowsPerPage = new VerticalSpinner().range(0, Integer.MAX_VALUE).value(5).step(5);

    private Pager pager = new Pager().css("grid-pager");

    public GridFooter(Row row) {
      super();
      this.init(row.addCell());
    }

    private void init(Cell cell) {
      this.cell = cell;

      Tag<DivElement> left = Tag.asDiv().css("datagrid-footer-left");
      Tag<DivElement> controls = Tag.asDiv().css("grid-controls");
      left.add(controls.add(this.rowsPerPage));

      Tag<DivElement> right = Tag.asDiv().css("datagrid-footer-right");
      right.add(this.pager);

      this.cell.add(left).add(right);
    }

    GridFooter colspan(int colspan) {
      this.cell.colspan(colspan);
      return this;
    }
  }

  class DefaultGridFilter extends GridFilter<H> {

    @Override
    public boolean filter(H row) {
      String search = Datagrid.this.caption.search.input.value();

      if ("".equals(search.trim())) {
        return true;
      }

      for (GridColumn<?, H> column : Datagrid.this.columns) {
        if (column.filterable() && Datagrid.this.caption.search.mode.eval(search, column.toString(row))) {
          return true;
        }
      }

      return false;
    }
  }
}