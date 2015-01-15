package virtuozo.ui;

import virtuozo.ui.Table.Cell;
import virtuozo.ui.Table.Row;
import virtuozo.ui.interfaces.UIComponent;

import com.google.gwt.dom.client.Style.Unit;

public class LayoutPanel extends Component<LayoutPanel> {
  private Orientation orientation;
  
  private Table table = Table.create();
  
  public static LayoutPanel horizontal(){
    LayoutPanel panel = new LayoutPanel(Orientation.HORIZONTAL);
    panel.table.body().addRow();
    return panel;
  }
  
  public static LayoutPanel vertical(){
    return new LayoutPanel(Orientation.VERTICAL);
  }
  
  private LayoutPanel(Orientation orientation) {
    this.orientation = orientation;
    this.table.css().set("layout-panel");
    this.incorporate(this.table);
  }
  
  public LayoutPanel add(UIComponent component){
    this.orientation.cell(this.table).add(component);
    return this;
  }
  
  public LayoutPanel distribute() {
    this.table.style().width(100, Unit.PCT);
    this.orientation.disposition(this);
    return this;
  }
  
  enum Orientation{
    HORIZONTAL{
      @Override
      void disposition(LayoutPanel panel) {
        Row row = panel.table.body().childAt(0);
        double width = 100d / row.childrenCount();
        for(Cell cell : row.children()){
          cell.style().width(width, Unit.PCT);
        }
      }
      
      @Override
      Cell cell(Table table) {
        return table.body().childAt(0).addCell().css("layout-horizontal");
      }
    }, VERTICAL{
      @Override
      Cell cell(Table table) {
        return table.body().addRow().addCell().css("layout-vertical");
      }
      
      @Override
      void disposition(LayoutPanel panel) {
        //Do nothing
      }
    };
    
    abstract Cell cell(Table table);
    
    abstract void disposition(LayoutPanel panel);
  }
}