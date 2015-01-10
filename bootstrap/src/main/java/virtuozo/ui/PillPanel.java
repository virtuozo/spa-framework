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

import virtuozo.ui.Menu.MenuItem;
import virtuozo.ui.css.State;
import virtuozo.ui.events.ActivationEvent;
import virtuozo.ui.events.ActivationEvent.ActivationHandler;
import virtuozo.ui.events.DeactivationEvent;
import virtuozo.ui.events.DeactivationEvent.DeactivationHandler;
import virtuozo.ui.interfaces.HasClickHandlers;
import virtuozo.ui.interfaces.HasMouseHandlers;
import virtuozo.ui.interfaces.HasState;
import virtuozo.ui.interfaces.HasText;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;

public class PillPanel extends Component<PillPanel> {
  private OrderList nav = OrderList.unordered();

  private ActivationHelper activationHelper = ActivationHelper.create();

  public static PillPanel create(){
    return new PillPanel();
  }
  
  private PillPanel() {
    this.incorporate(nav);
    this.nav.css("nav", "nav-pills");
  }

  public Pill addPill() {
    Pill pill = new Pill(this.nav.addItem());
    this.activationHelper.add(pill);
    if (this.nav.childrenCount() == 1) {
      pill.activate();
    }

    return pill;
  }

  public PillDroppable addDropItem() {
    return new PillDroppable(this.nav.addItem());
  }

  public class PillDroppable extends Component<PillDroppable> implements HasText<PillDroppable> {
    private DropItem item;

    public PillDroppable(ListItem item) {
      this.item = new DropItem(item);
    }

    public PillDropItem addItem(){
      PillDropItem item = new PillDropItem(this.item.menu().addItem());
      PillPanel.this.activationHelper.add(item);
      return item;
    }

    @Override
    public String text() {
      return this.item.text();
    }

    @Override
    public PillDroppable text(String text) {
      this.item.text(text);
      return this;
    }

    public class PillDropItem extends NavDropItem<PillDropItem> {
      PillDropItem(MenuItem item) {
        super(item);
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

    public static final Type STACKED = new Type("nav-stacked");
    public static final Type BLOCK = new Type("nav-justified");
    private static final StyleChooser TYPES = new StyleChooser(STACKED, BLOCK);
  }

  public class Pill extends Component<Pill> implements HasText<Pill>, HasClickHandlers<Pill>, HasMouseHandlers<Pill>, HasState<Pill> {
    private Tag<AnchorElement> anchor = Tag.asAnchor();
    
    private EnablementHelper<Pill> helper;

    public Pill(ListItem item) {
      super(item);
      this.addChild(this.anchor);
      this.helper = new EnablementHelper<Pill>(this).intercept(this.anchor);
    }

    public Badge addBadge() {
      Badge badge = Badge.create();
      this.anchor.add(badge);
      return badge;
    }

    @Override
    public String text() {
      return this.anchor.text();
    }

    @Override
    public Pill text(String text) {
      this.anchor.text(text);
      return this;
    }
    
    @Override
    public Pill onActivate(ActivationHandler handler) {
      return this.addHandler(ActivationEvent.TYPE, handler);
    }

    @Override
    public Pill onDeactivate(DeactivationHandler handler) {
      return this.addHandler(DeactivationEvent.TYPE, handler);
    }

    @Override
    public Pill onMouseDown(MouseDownHandler handler) {
      this.anchor.onMouseDown(handler);
      return this;
    }

    @Override
    public Pill onMouseMove(MouseMoveHandler handler) {
      this.anchor.onMouseMove(handler);
      return this;
    }

    @Override
    public Pill onMouseOut(MouseOutHandler handler) {
      this.anchor.onMouseOut(handler);
      return this;
    }

    @Override
    public Pill onMouseOver(MouseOverHandler handler) {
      this.anchor.onMouseOver(handler);
      return this;
    }

    @Override
    public Pill onMouseUp(MouseUpHandler handler) {
      this.anchor.onMouseUp(handler);
      return this;
    }

    @Override
    public Pill onMouseWheel(MouseWheelHandler handler) {
      this.anchor.onMouseWheel(handler);
      return this;
    }

    @Override
    public Pill onClick(ClickHandler handler) {
      this.anchor.onClick(handler);
      return this;
    }

    @Override
    public Pill onDoubleClick(DoubleClickHandler handler) {
      this.anchor.onDoubleClick(handler);
      return this;
    }

    @Override
    public Pill activate() {
      this.css(State.ACTIVE);
      this.fireEvent(new ActivationEvent());
      return this;
    }

    @Override
    public Pill deactivate() {
      this.css().remove(State.ACTIVE);
      this.fireEvent(new DeactivationEvent());
      return this;
    }

    @Override
    public boolean active() {
      return this.css().contains(State.ACTIVE);
    }
    
    public Pill disable() {
      return this.helper.disable();
    }
    
    public Pill enable(){
      return this.helper.enable();
    }
    
    @Override
    public boolean disabled() {
      return this.helper.disabled();
    }

    @Override
    public boolean match(Element element) {
      return this.anchor.id().equals(element.getId());
    }
  }
}