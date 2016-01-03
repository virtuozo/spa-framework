package virtuozo.ui;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import virtuozo.infra.Async;
import virtuozo.infra.DataBinding.Attribute;
import virtuozo.infra.DataField;
import virtuozo.infra.Dataset;
import virtuozo.infra.Dataset.IterableCallback;
import virtuozo.infra.JSObject;
import virtuozo.infra.Sort;
import virtuozo.infra.api.ValueChangeHandler;
import virtuozo.ui.Table.Body;
import virtuozo.ui.Table.Cell;
import virtuozo.ui.Table.Row;
import virtuozo.ui.events.PageChangeEvent;
import virtuozo.ui.events.PageChangeEvent.PageChangeHandler;
import virtuozo.ui.events.SelectionEvent;
import virtuozo.ui.events.SelectionEvent.SelectionHandler;
import virtuozo.ui.interfaces.Assets;
import virtuozo.ui.interfaces.AttachHandler;
import virtuozo.ui.interfaces.HasIcon;
import virtuozo.ui.interfaces.HasText;
import virtuozo.ui.interfaces.Icon;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.AttachEvent;

public class DataGrid<J extends JavaScriptObject> extends Component<DataGrid<J>> {
  private Dataset<J> dataset;

  private Table table = Table.create().stripped().hover();

  private Map<Attribute, Column> columns = new HashMap<Attribute, Column>();

  private Header header = new Header();

  private Tag<DivElement> viewPort = Tag.asDiv().css("datagrid-viewport");

  private Tag<DivElement> loader = Tag.asDiv().css("datagrid-loader").hide();

  private Footer footer = new Footer();

  private Renderer renderer = new Renderer();

  DataGrid(Dataset<J> dataset) {
    super(Elements.div());
    this.dataset = dataset;
    this.init();
  }

  public static <J extends JavaScriptObject> DataGrid<J> create(Dataset<J> dataset) {
    return new DataGrid<J>(dataset);
  }

  private void init() {
    this.css("datagrid").attribute("data-viewtype", "list");
    this.addChild(this.header).addChild(this.viewPort).addChild(this.footer);

    Tag<DivElement> canvas = Tag.asDiv().css("datagrid-canvas", "scrolling").attachTo(this.viewPort);
    Tag<DivElement> list = Tag.asDiv().css("datagrid-list").attachTo(canvas);
    Tag<DivElement> wrapper = Tag.asDiv().css("datagrid-list-wrapper").attachTo(list);
    this.table.attachTo(wrapper).role("grid");
    this.loader.attachTo(this.viewPort);

    List<DataField> fields = this.dataset.fields();
    Row headerRow = table.header().addRow();

    for (DataField field : fields) {
      this.columns.put(field.attribute(), new Column(headerRow.addCell(), field));
    }

    this.footer.rowsPerPage.onChange(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        for (Column column : columns.values()) {
          column.width(0).fit();
        }
        dataset.pagination().rowsPerPage().set(footer.rowsPerPage.value());
      }
    });

    this.footer.pager.onPageChange(new PageChangeHandler() {
      @Override
      public void onPageChange(PageChangeEvent event) {
        dataset.pagination().goTo(event.page());
      }
    });

    dataset.pagination().onIteration(new IterableCallback<J>() {
      @Override
      public void before() {
        DataGrid.this.renderer.body.detachChildren();
      }
      
      @Override
      public void onNext(J next) {
        renderer.render(next);
      }
      
      @Override
      public void after() {
        
      }
    }).numberOfPages().onChange(new ValueChangeHandler<Integer>() {
      
      @Override
      public void onChange(Integer oldValue, Integer newValue) {
        footer.pager.pages(newValue);
      }
    });
  }

  public Header header() {
    return this.header;
  }

  public Footer footer() {
    return this.footer;
  }

  public Iterable<Column> columns() {
    return this.columns.values();
  }

  public Column column(Attribute attribute) {
    return this.columns.get(attribute);
  }

  @Override
  public Style style() {
    return this.viewPort.style();
  }

  public void sort(Column target) {
    Collection<Column> columns = this.columns.values();
    for (Column column : columns) {
      column.unsort();
    }
    target.sort();
    this.dataset.sort(target.field, target.sort);
  }

  public void onSelect(SelectionHandler<J> handler) {
    this.addHandler(SelectionEvent.TYPE, handler);
  }

  class Renderer {
    private Body body;

    public Renderer() {
      this.body = DataGrid.this.table.body();
    }

    void render(final J object) {
      // this.body.detachChildren();

      final Row row = this.body.addRow();
      row.onClick(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          for (Row child : body.children()) {
            child.css().remove(Table.Color.WARNING);
          }
          row.css(Table.Color.WARNING);
          fireEvent(new SelectionEvent<J>(object));
        }
      });

      Collection<Column> columns = DataGrid.this.columns.values();
      for (Column column : columns) {
        column.render(row.addCell(), object);
      }

      // String emptyMessage = this.options.emptyMessage;
      // if (this.totalRows == 0 && emptyMessage != null){
      // Row row = this.body.row().warning();
      // row.cell().colspan(this.columns.size()).add(new Paragraph().text(emptyMessage));
      // }
    }
  }

  public class Header extends Section<Header> implements HasText<Header> {
    private Heading title = Heading.three();

    Header() {
      this.init();
    }

    private void init() {
      this.css("datagrid-header");
      this.title.css("datagrid-title");

      this.leftFacet().css("datagrid-header-left").add(this.title);
      this.rightFacet().css("datagrid-header-right");
    }

    @Override
    public Header text(String text) {
      this.title.text(text);
      return this;
    }

    @Override
    public String text() {
      return this.title.text();
    }
  }

  public class Footer extends Section<Header> {
    private Tag<DivElement> itemization = Tag.asDiv().css("datagrid-itemization");

    private Spinner rowsPerPage = Spinner.create().step(5);

    private Tag<DivElement> pagination = Tag.asDiv().css("datagrid-pagination");

    private Pager pager = Pager.create();

    Footer() {
      this.init();
    }

    private void init() {
      this.css("datagrid-footer");

      this.itemization.add(this.rowsPerPage);
      this.pagination.add(this.pager);

      this.leftFacet().css("datagrid-footer-left").add(this.itemization);
      this.rightFacet().css("datagrid-footer-right").add(this.pagination);

      this.rowsPerPage(5, 100);
    }

    public Footer rowsPerPage(int minValue, int maxValue) {
      this.rowsPerPage.range(minValue, maxValue);
      return this;
    }
  }

  abstract class Section<S extends Section<S>> extends Component<S> {
    private Tag<DivElement> left = Tag.asDiv();

    private Tag<DivElement> right = Tag.asDiv();

    public Section() {
      super(Elements.div());
      this.init();
    }

    private void init() {
      this.addChild(this.left);
      this.addChild(this.right);
    }

    public Tag<DivElement> leftFacet() {
      return this.left;
    }

    public Tag<DivElement> rightFacet() {
      return right;
    }
  }

  public class Column implements HasText<Column> {
    private Cell header;

    private SortableColumn container = new SortableColumn();

    private Text text = Text.create();

    private Tag<SpanElement> icon = Tag.asSpan();

    private DataField field;

    private Sort sort = Sort.NONE;

    private double width;

    Column(Cell header, DataField field) {
      this.header = header;
      this.field = field;
      this.init();
    }

    void init() {
      this.header.add(this.text.show()).add(this.icon);

      this.header.css("sortable").add(container);
      this.header.onClick(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          DataGrid.this.sort(Column.this);
        }
      });

      this.header.onAttach(new AttachHandler() {
        @Override
        protected void onAttach(AttachEvent event) {
          fit();
        }
      });

      this.text("Column");
    }

    private void fit() {
      Async.get().execute(new Runnable() {
        @Override
        public void run() {
          if (width == 0) {
            width = header.measurement().rectangle().width();
          }
          header.style().width(width, Unit.PX);
          container.style().width(width, Unit.PX);
        }
      });
    }

    public Column width(double width) {
      this.width = width;
      return this;
    }

    public double width() {
      return width;
    }

    public void colspan(int colspan) {
      this.header.colspan(colspan);
    }

    @Override
    public Column text(String text) {
      this.text.text(text);
      this.container.text(text);
      return this;
    }

    @Override
    public String text() {
      return this.text.text();
    }

    void render(Cell cell, J object) {
      cell.text(((JSObject)object).bindAsString(this.field.attribute()).get());
    }

    void sort() {
      this.container.css("sorted");
      this.sort = sort.reverse();
      this.toIcon(this.sort).update(this.container.icon);
    }
    
    private Assets assets = GWT.create(Assets.class);
    
    private Icon toIcon(Sort sort){
      if(sort.equals(Sort.DESC)){
        return this.assets.downIcon();
      }
      
      return this.assets.upIcon();
    }

    void unsort() {
      this.header.css().remove("sorted");
      this.container.css().remove("sorted");
    }

    class SortableColumn extends Component<SortableColumn> implements HasText<SortableColumn>, HasIcon<SortableColumn> {
      private Text text = Text.create();

      private Tag<SpanElement> icon = Tag.asSpan();

      public SortableColumn() {
        super(Elements.div());
        this.css("datagrid-list-heading", "sortable").addChild(this.text).addChild(this.icon);
      }

      @Override
      public SortableColumn icon(Icon icon) {
        icon.update(this.icon);
        return this;
      }

      @Override
      public SortableColumn text(String text) {
        this.text.text(text);
        return this;
      }

      @Override
      public String text() {
        return this.text.text();
      }
    }
  }
}