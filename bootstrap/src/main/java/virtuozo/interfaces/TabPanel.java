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

import virtuozo.infra.ActivationHelper;
import virtuozo.infra.Elements;
import virtuozo.infra.StyleChooser;
import virtuozo.infra.event.ActivationEvent;
import virtuozo.infra.event.DeactivationEvent;
import virtuozo.infra.event.ActivationEvent.ActivationHandler;
import virtuozo.infra.event.DeactivationEvent.DeactivationHandler;
import virtuozo.infra.handlers.HasClickHandlers;
import virtuozo.infra.handlers.HasMouseHandlers;
import virtuozo.interfaces.Anchor;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.Composite;
import virtuozo.interfaces.CssClass;
import virtuozo.interfaces.Tag;
import virtuozo.interfaces.Menu.MenuItem;
import virtuozo.interfaces.OrderList.ListItem;
import virtuozo.interfaces.css.State;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;

public class TabPanel extends Component<TabPanel> {
  private OrderList nav = OrderList.unordered();

  private Tag<DivElement> content = Tag.asDiv();

  private ActivationHelper activationHelper = ActivationHelper.create();

  public static TabPanel create(){
    return new TabPanel();
  }
  
  private TabPanel() {
    super(Elements.div());
    this.addChild(nav).addChild(this.content);
    this.nav.css("nav", "nav-tabs");
    this.content.css("tab-content");
  }

  public Tab addTab() {
    Tab tab = new Tab(this.nav.addItem());
    this.activationHelper.add(tab);
    this.content.add(tab.panel());

    if (this.nav.childrenCount() == 1) {
      tab.activate();
    }

    return tab;
  }

  public TabDroppable addDropTab() {
    return new TabDroppable(this.nav.addItem());
  }
  
  @Override
  public TabPanel css(String... classes) {
    this.nav.css(classes);
    return this;
  }
  
  @Override
  public TabPanel css(UIClass... classes) {
    this.nav.css(classes);
    return this;
  }

  @Override
  public UIClasses css() {
    return this.nav.css();
  }

  public class TabDroppable extends Component<TabDroppable> implements HasText<TabDroppable> {
    private DropItem item;

    private TabDroppable(ListItem item) {
      this.item = new DropItem(item);
    }

    public TabDropItem addItem(){
      TabDropItem item = new TabDropItem(this.item.menu().addItem());
      TabPanel.this.activationHelper.add(item);
      TabPanel.this.content.add(item.panel());
      return item;
    }

    @Override
    public String text() {
      return this.item.text();
    }

    @Override
    public TabDroppable text(String text) {
      this.item.text(text);
      return this;
    }

    public class TabDropItem extends NavDropItem<TabDropItem> {
      private Panel panel;
      
      TabDropItem(MenuItem item) {
        super(item);
        this.panel = new Panel();
      }
      
      public Panel panel() {
        return this.panel;
      }
      
      @Override
      public TabDropItem activate() {
        this.panel.css().remove("out").append("active", "in");
        return super.activate();
      }
      
      @Override
      public TabDropItem deactivate() {
        this.panel.css().remove("active", "in").append("out");
        return super.deactivate();
      }
    }
  }

  public static class Type extends CssClass {
    private Type(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return TYPES;
    }

    public static final Type DEFAULT = new Type("nav-default");
    public static final Type BLOCK = new Type("nav-justified");
    private static final StyleChooser TYPES = new StyleChooser(DEFAULT, BLOCK);
  }

  public class Tab extends Component<Tab> implements HasText<Tab>, HasClickHandlers<Tab>, HasMouseHandlers<Tab>, HasActivation<Tab> {
    private Anchor anchor = Anchor.create();

    private Panel panel = new Panel();

    private Tab(ListItem item) {
      super(item);
      this.addChild(this.anchor);
    }

    public Panel panel() {
      return this.panel;
    }

    @Override
    public String text() {
      return this.anchor.text();
    }

    @Override
    public Tab text(String text) {
      this.anchor.text(text);
      return this;
    }

    @Override
    public Tab onActivate(ActivationHandler handler) {
      return this.addHandler(ActivationEvent.TYPE, handler);
    }

    @Override
    public Tab onDeactivate(DeactivationHandler handler) {
      return this.addHandler(DeactivationEvent.TYPE, handler);
    }

    @Override
    public Tab onMouseDown(MouseDownHandler handler) {
      this.anchor.onMouseDown(handler);
      return this;
    }

    @Override
    public Tab onMouseMove(MouseMoveHandler handler) {
      this.anchor.onMouseMove(handler);
      return this;
    }

    @Override
    public Tab onMouseOut(MouseOutHandler handler) {
      this.anchor.onMouseOut(handler);
      return this;
    }

    @Override
    public Tab onMouseOver(MouseOverHandler handler) {
      this.anchor.onMouseOver(handler);
      return this;
    }

    @Override
    public Tab onMouseUp(MouseUpHandler handler) {
      this.anchor.onMouseUp(handler);
      return this;
    }

    @Override
    public Tab onMouseWheel(MouseWheelHandler handler) {
      this.anchor.onMouseWheel(handler);
      return this;
    }

    @Override
    public Tab onClick(ClickHandler handler) {
      this.anchor.onClick(handler);
      return this;
    }

    @Override
    public Tab onDoubleClick(DoubleClickHandler handler) {
      this.anchor.onDoubleClick(handler);
      return this;
    }

    @Override
    public Tab activate() {
      this.css(State.ACTIVE);
      this.panel.css().remove("out").append(State.ACTIVE).append("in");
      return this;
    }

    @Override
    public boolean active() {
      return this.css().contains(State.ACTIVE);
    }

    @Override
    public Tab deactivate() {
      this.css().remove("active");
      this.panel.css().remove(State.ACTIVE).remove("in").append("out");
      return this;
    }

    @Override
    public boolean match(Element element) {
      return this.anchor.id().equals(element.getId());
    }
  }
  
  public class Panel extends Composite<Panel> {
    private Panel() {
      super(Elements.div());
      this.css("tab-pane", "fade");
    }
  }
}