package virtuozo.infra;

import java.util.ArrayList;
import java.util.List;

import virtuozo.infra.Dataset.DataEvent.DataHandler;
import virtuozo.infra.api.ValueChangeHandler;
import virtuozo.ui.EventManager;
import virtuozo.ui.events.ValueEvent;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.shared.EventHandler;

public class Dataset<J extends JavaScriptObject> {
  private JsArray<J> data;

  private final List<DataField> fields;

  private EventManager bus = EventManager.create();
  
  private Pagination pagination = new Pagination();

  Dataset() {
    this.fields = new ArrayList<DataField>();
  }

  public static <J extends JavaScriptObject> Dataset<J> create() {
    return new Dataset<J>();
  }

  public Dataset<J> add(DataField field) {
    this.fields.add(field);
    return this;
  }

  public Dataset<J> data(JsArray<J> data) {
    this.data = data;
    this.bus.fire(new DataEvent<J>(data));
    this.pagination.goTo(1);
    return this;
  }
  
  JsArray<J> data() {
    return data;
  }

  public Dataset<J> onData(DataHandler<J> handler) {
    this.bus.add(DataEvent.TYPE, handler);
    return this;
  }

  public List<DataField> fields() {
    return fields;
  }
  
  public Pagination pagination(){
    return this.pagination;
  }
  
  public Dataset<J> sort(DataField field, Sort sort){
    field.sort(this, field.attribute(), sort);
    this.pagination.goTo(1);
    return this;
  }
  
  public class Pagination {
    private IntegerProperty rowsPerPage = new IntegerProperty(5);

    private IntegerProperty numberOfPages = new IntegerProperty(0);
    
    private IterableCallback<J> callback;
    
    public Pagination() {
      this.rowsPerPage.onChange(new ValueChangeHandler<Integer>() {
        @Override
        public void onChange(Integer oldValue, Integer newValue) {
          goTo(1);
        }
      });
    }
    
    public IntegerProperty rowsPerPage() {
      return this.rowsPerPage;
    }
    
    public IntegerProperty numberOfPages() {
      return numberOfPages;
    }
    
    public Pagination onIteration(IterableCallback<J> callback){
      this.callback = callback;
      return this;
    }
    
    public Pagination goTo(int page){
      if(Dataset.this.data == null || this.callback == null){
        return this;
      }
      
      JsArray<J> rows = Dataset.this.data;
      int numberOfRows = this.rowsPerPage.get();

      int numberOfPages = rows.length() / numberOfRows;
      if (rows.length() % numberOfRows > 0) {
        numberOfPages++;
      }

      this.numberOfPages.set(numberOfPages);

      int rowIndex = (page - 1) * numberOfRows;
      numberOfRows = rowIndex + numberOfRows;

      int index = 0;
      int objectIndex = -1;
      
      this.callback.before();
      while (rowIndex < numberOfRows) {

        if (rowIndex == rows.length()) {
          break;
        }

        final J object = rows.get(index++);

        objectIndex++;
        if (objectIndex < rowIndex) {
          continue;
        }

        rowIndex++;
        this.callback.onNext(object);
      }
      this.callback.after();
      
      return this;
    }
  }

  public static interface IterableCallback<J> {
    void before();
    
    void onNext(J next);
    
    void after();
  }
  
  public static class DataEvent<J extends JavaScriptObject> extends ValueEvent<JsArray<J>, DataHandler<J>> {
    public static final Type<DataHandler<?>> TYPE = new Type<DataHandler<?>>();

    public DataEvent(JsArray<J> value) {
      super(value);
    }

    @Override
    public Type<DataHandler<J>> getAssociatedType() {
      return (Type) TYPE;
    }

    @Override
    protected void dispatch(DataHandler<J> handler) {
      handler.onSelect(this);
    }

    public static interface DataHandler<J extends JavaScriptObject> extends EventHandler {
      void onSelect(DataEvent<J> event);
    }
  }
}