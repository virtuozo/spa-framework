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

import hitz.virtuozo.ui.Composite;
import hitz.virtuozo.ui.CssClass;
import hitz.virtuozo.ui.Elements;
import hitz.virtuozo.ui.StyleChooser;
import hitz.virtuozo.ui.Component;

public final class Container extends Component<Container> {

  public Container(Type type) {
    super(Elements.div());
    this.css().set(type);
  }

  public Row addRow() {
    Row row = new Row();
    this.addChild(row);
    return row;
  }
  
  public static class Row extends Component<Row> {

    public Row() {
      super(Elements.div());
      this.css().set("row");
    }

    public Column addColumn() {
      Column column = new Column();
      this.addChild(column);
      return column;
    }
    
    public class Column extends Composite<Column> {
      public Column() {
        super(Elements.div());
      }
      
      public Row addRow(){
        Row row = new Row();
        this.add(row);
        return row;
      }

      public Column span(int span, ViewPort viewPort) {
        String prefix = "";

        switch (viewPort) {
          case X_SMALL:
            prefix = "col-xs-";
            break;
          case SMALL:
            prefix = "col-sm-";
            break;
          case MEDIUM:
            prefix = "col-md-";
          case LARGE:
            prefix = "col-lg-";
            break;
        }
        this.css(prefix + span);
        return this;
      }
      
      public Column offset(int offset, ViewPort viewPort) {
        String prefix = "";

        switch (viewPort) {
          case X_SMALL:
            prefix = "col-xs-offset-";
            break;
          case SMALL:
            prefix = "col-sm-offset-";
            break;
          case MEDIUM:
            prefix = "col-md-offset-";
          case LARGE:
            prefix = "col-lg-offset-";
            break;
        }
        this.css(prefix + offset);
        return this;
      }

      public Column clearfix(ViewPort... viewPorts) {
        this.css("clearfix");
        for (ViewPort viewPort : viewPorts) {
          viewPort.visible(this);
        }

        return this;
      }
    }
  }

  public static class Type extends CssClass {
    private Type(String name) {
      super(name);
    }
    
    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    public static final Type FIXED = new Type("container");
    public static final Type FLUID = new Type("container-fluid");
    private static final StyleChooser STYLES = new StyleChooser(FIXED, FLUID);
  }
}
