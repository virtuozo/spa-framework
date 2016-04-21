package virtuozo.infra;

import virtuozo.infra.data.JSArrays;
import virtuozo.infra.data.DataBinding.Attribute;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class DataField {
  private final Type type;

  private Attribute attribute;

  private DataSort sort;

  DataField(Type type) {
    this.type = type;
    this.sort = type.sort();
  }

  public static DataField of(Type type) {
    return new DataField(type);
  }

  public DataField bindingTo(Attribute attribute) {
    this.attribute = attribute;
    return this;
  }

  public DataField sortingBy(DataSort sort) {
    this.sort = sort;
    return this;
  }

  public Attribute attribute() {
    return attribute;
  }
  
  public <J extends JavaScriptObject> void sort(Dataset<J> dataset, Attribute attribute, Sort direction) {
    sort.sort(dataset.data(), attribute, direction);
  }

  public Type type() {
    return type;
  }

  public interface DataSort {
    <J extends JavaScriptObject> void sort(JsArray<J> rows, Attribute attribute, Sort direction);
  }

  public enum Type {
    BOOLEAN {
      private DataSort sort = new DataSort() {
        @Override
        public <J extends JavaScriptObject> void sort(JsArray<J> rows, Attribute attribute, Sort direction) {
          JSArrays.sortBoolean(rows, attribute.name(), direction.direction());
        }
      };

      @Override
      DataSort sort() {
        return sort;
      }
    },
    DATE {
      private DataSort sort = new DataSort() {
        @Override
        public <J extends JavaScriptObject> void sort(JsArray<J> rows, Attribute attribute, Sort direction) {
          JSArrays.sort(rows, attribute.name(), direction.direction());
        }
      };

      @Override
      DataSort sort() {
        return sort;
      }
    },
    NUMBER {
      private DataSort sort = new DataSort() {
        @Override
        public <J extends JavaScriptObject> void sort(JsArray<J> rows, Attribute attribute, Sort direction) {
          JSArrays.sortNumber(rows, attribute.name(), direction.direction());
        }
      };

      @Override
      DataSort sort() {
        return sort;
      }
    },
    STRING {
      private DataSort sort = new DataSort() {
        @Override
        public <J extends JavaScriptObject> void sort(JsArray<J> rows, Attribute attribute, Sort direction) {
          JSArrays.sort(rows, attribute.name(), direction.direction());
        }
      };

      @Override
      DataSort sort() {
        return sort;
      }
    };

    abstract DataSort sort();
  }
}