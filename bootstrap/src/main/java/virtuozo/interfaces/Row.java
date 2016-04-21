package virtuozo.interfaces;

import virtuozo.infra.Elements;
import virtuozo.interfaces.Composite;
import virtuozo.interfaces.Parent;
import virtuozo.interfaces.Row.Column;

public class Row extends Parent<Row, Column> {

  public static Row create(){
    return new Row();
  }
  
  private Row() {
    super(Elements.div());
    this.css().set("row");
  }

  public Column addColumn() {
    Column column = new Column();
    this.addChild(column);
    return column;
  }
  
  public class Column extends Composite<Column> {
    private Column() {
      super(Elements.div());
    }
    
    public Row addRow(){
      Row row = Row.create();
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