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

package virtuozo.ui;

import virtuozo.ui.Component;
import virtuozo.ui.Composite;
import virtuozo.ui.CssClass;
import virtuozo.ui.Elements;
import virtuozo.ui.Parent;
import virtuozo.ui.StyleChooser;
import virtuozo.ui.Tag;
import virtuozo.ui.api.HasClickHandlers;
import virtuozo.ui.api.HasMouseHandlers;
import virtuozo.ui.api.HasText;
import virtuozo.ui.api.UICell;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.layout.client.Layout.Alignment;

@SuppressWarnings("unchecked")
public final class Table extends Component<Table> {

  private Caption caption;

  private Header header;

  private Body body = new Body();

  private Footer footer;

  public Table() {
    super(Elements.table());
    this.css().set("table");
  }

  public Caption caption() {
    if (this.caption == null) {
      this.caption = new Caption();
      this.addChild(this.caption);
    }

    return this.caption;
  }

  public Header header() {
    if (this.header == null) {
      this.header = new Header();
      this.addChild(this.header);
    }

    return this.header;
  }

  public Body body() {
    this.addChild(this.body);
    return this.body;
  }

  public Footer footer() {
    if (this.footer == null) {
      this.footer = new Footer();
      this.addChild(this.footer);
    }

    return this.footer;
  }
  
  public Table stripped(){
    this.css("table-striped");
    return this;
  }
  
  public Table bordered(){
    this.css("table-bordered");
    return this;
  }
  
  public Table hover(){
    this.css("table-hover");
    return this;
  }
  
  public Table condensed(){
    this.css("table-condensed");
    return this;
  }
  
  public Table responsive(){
    Tag<DivElement> container = Tag.asDiv();
    container.css().set("table-responsive");
    return this.tradeParent(container);
  }

  public TableElement element() {
    return super.element();
  }

  public class Caption extends Component<Caption> implements HasText<Caption> {
    public Caption() {
      super(Elements.caption());
    }
    
    @Override
    public String text() {
      return this.element().getInnerText();
    }
    
    public Caption text(String text) {
      this.element().setInnerText(text);
      return this;
    };
  }

  public class Header extends Parent<Header, Row> {

    public Header() {
      super(Elements.thead());
    }

    public Row addRow() {
      Row row = new Row(true);
      this.add(row);
      return row;
    }
  }

  public class Footer extends Parent<Footer, Row> {

    public Footer() {
      super(Elements.tfoot());
    }

    public Row addRow() {
      Row row = new Row(true);

      this.add(row);

      return row;
    }
  }

  public class Body extends Parent<Body, Row> {

    public Body() {
      super(Elements.tbody());
    }

    public Row addRow() {
      Row row = new Row(false);
      this.add(row);
      return row;
    }
  }

  public class Row extends Parent<Row, Cell> implements HasClickHandlers<Row>, HasMouseHandlers<Row> {

    private boolean head;

    public Row(boolean head) {
      super(Elements.tr());
      this.head = head;
    }

    @Override
    public Row onMouseDown(MouseDownHandler handler) {
      return this.on(handler);
    }

    @Override
    public Row onMouseMove(MouseMoveHandler handler) {
      return this.on(handler);
    }

    @Override
    public Row onMouseOut(MouseOutHandler handler) {
      return this.on(handler);
    }

    @Override
    public Row onMouseOver(MouseOverHandler handler) {
      return this.on(handler);
    }

    @Override
    public Row onMouseUp(MouseUpHandler handler) {
      return this.on(handler);
    }

    @Override
    public Row onMouseWheel(MouseWheelHandler handler) {
      return this.on(handler);
    }

    @Override
    public Row onClick(ClickHandler handler) {
      return this.on(handler);
    }

    @Override
    public Row onDoubleClick(DoubleClickHandler handler) {
      return this.on(handler);
    }

    public Cell addCell() {
      Cell cell = new Cell(this.head);
      this.add(cell);
      return cell;
    }
  }
  
  public class Cell extends Composite<Cell> implements UICell<Cell> {

    public Cell(boolean head) {
      super(head ? Elements.th() : Elements.td());
    }

    @Override
    public Cell onClick(ClickHandler handler) {
      return this.on(handler);
    }

    @Override
    public Cell onDoubleClick(DoubleClickHandler handler) {
      return this.on(handler);
    }

    @Override
    public Cell onMouseDown(MouseDownHandler handler) {
      return this.on(handler);
    }

    @Override
    public Cell onMouseMove(MouseMoveHandler handler) {
      return this.on(handler);
    }

    @Override
    public Cell onMouseOut(MouseOutHandler handler) {
      return this.on(handler);
    }

    @Override
    public Cell onMouseOver(MouseOverHandler handler) {
      return this.on(handler);
    }

    @Override
    public Cell onMouseUp(MouseUpHandler handler) {
      return this.on(handler);
    }

    @Override
    public Cell onMouseWheel(MouseWheelHandler handler) {
      return this.on(handler);
    }

    public Cell colspan(int colspan) {
      this.element().setColSpan(colspan);
      return this;
    }

    public Cell rowspan(int rowspan) {
      this.element().setRowSpan(rowspan);
      return this;
    }

    public Cell align(Alignment align) {
      this.element().setAlign(align.toString());
      return this;
    }

    public TableCellElement element() {
      return super.element();
    }

    @Override
    public Cell text(String text) {
      this.element().setInnerText(text);
      return this;
    }

    @Override
    public String text() {
      return this.element().getInnerText();
    }
    
    @Override
    public String html() {
      return this.element().getInnerHTML();
    }
    
    @Override
    public Cell html(String html) {
      this.element().setInnerHTML(html);
      return this;
    }
  }
  
  public static class Color extends CssClass{
    private Color(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }
    
    public static final Color ACTIVE = new Color("active");
    public static final Color SUCCESS = new Color("success");
    public static final Color INFO = new Color("info");
    public static final Color WARNING = new Color("warning");
    public static final Color DANGER = new Color("danger");
    static final StyleChooser STYLES = new StyleChooser(ACTIVE, SUCCESS, INFO, WARNING, DANGER);
  }
}