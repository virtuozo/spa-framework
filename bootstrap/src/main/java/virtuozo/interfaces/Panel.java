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
package virtuozo.interfaces;

import virtuozo.infra.Elements;
import virtuozo.infra.StyleChooser;
import virtuozo.infra.events.TextChangeEvent;
import virtuozo.infra.events.TextChangeEvent.TextChangeHandler;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.Composite;
import virtuozo.interfaces.CssClass;
import virtuozo.interfaces.FontAwesome;
import virtuozo.interfaces.Tag;

import com.google.gwt.dom.client.SpanElement;

public class Panel extends Component<Panel> {
  private Header header = new Header();

  private Body body = new Body();

  private Footer footer = new Footer();

  public static Panel create(){
    return new Panel();
  }
  
  protected Panel() {
    super(Elements.div());
    this.css().set("panel").append(Panel.Color.DEFAULT);
    this.addChild(this.header.hide()).addChild(this.body.hide()).addChild(this.footer.hide());
  }
  
  public Header header() {
    return this.header.show();
  }

  public Body body() {
    return this.body.show();
  }

  public Footer footer() {
    return this.footer.show();
  }

  public Table addTable() {
    Table table = Table.create();
    this.insertChild(table, this.footer);
    return table;
  }

  public ListGroup addListGroup() {
    ListGroup group = ListGroup.create();
    this.insertChild(group, this.footer);
    return group;
  }

  public class Header extends Composite<Header> implements HasText<Header>, HasIcon<Header> {
    private RichHeading heading = RichHeading.three().css("panel-title").hide();
    
    private Tag<SpanElement> icon = Tag.asSpan();
    
    private Header() {
      super(Elements.div());
      this.css().set("panel-heading");
      this.heading.addFirstChild(this.icon);
      this.add(heading);
    }
    
    RichHeading heading(){
      return this.heading;
    }
    
    Header onTextChange(TextChangeHandler handler){
      this.addHandler(TextChangeEvent.TYPE, handler);
      return this;
    }
    
    @Override
    public String text() {
      return this.heading.text();
    }
    
    @Override
    public Header text(String text) {
      this.heading.text(text).show();
      return this.fireEvent(new TextChangeEvent(text));
    }
    
    @Override
    public Header icon(Icon icon) {
      icon.update(this.icon);
      this.icon.css(FontAwesome.Styles.FIXED);
      return this;
    }
    
    @Override
    public Header show() {
      this.heading.show();
      return super.show();
    }
    
    @Override
    public Header hide() {
      this.heading.hide();
      return super.hide();
    }
  }

  public class Body extends Composite<Body> {
    private Body() {
      super(Elements.div());
      this.css().set("panel-body");
    }
  }

  public class Footer extends Composite<Footer> {
    private Footer() {
      super(Elements.div());
      this.css().set("panel-footer");
    }
  }

  public static class Color extends CssClass {
    private Color(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    public static final Color PRIMARY = new Color("panel-primary");
    public static final Color SUCCESS = new Color("panel-success");
    public static final Color INFO = new Color("panel-info");
    public static final Color WARNING = new Color("panel-warning");
    public static final Color DANGER = new Color("panel-danger");
    public static final Color DEFAULT = new Color("panel-default");
    private static final StyleChooser STYLES = new StyleChooser(PRIMARY, SUCCESS, INFO, WARNING, DANGER, DEFAULT);
  }
}
