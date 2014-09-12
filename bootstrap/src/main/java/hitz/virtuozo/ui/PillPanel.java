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

import hitz.virtuozo.infra.api.EventHandler;
import hitz.virtuozo.infra.api.HasClickHandlers;
import hitz.virtuozo.infra.api.HasMouseHandlers;
import hitz.virtuozo.infra.api.HasText;
import hitz.virtuozo.ui.CssClass;
import hitz.virtuozo.ui.StyleChooser;
import hitz.virtuozo.ui.Tag;
import hitz.virtuozo.ui.Widget;
import hitz.virtuozo.ui.Menu.MenuItem;
import hitz.virtuozo.ui.api.HasActivation;
import hitz.virtuozo.ui.css.State;

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

public class PillPanel extends Widget<PillPanel> {
  private OrderList nav = new OrderList(OrderList.Type.UNORDERED);

  private ActivationHelper activationHelper = new ActivationHelper();

  public PillPanel() {
    this.compound(nav);
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

  public class PillDroppable extends Widget<PillDroppable> implements HasText<PillDroppable> {
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

  public class Pill extends Widget<Pill> implements HasText<Pill>, HasClickHandlers<Pill>, HasMouseHandlers<Pill>, HasActivation<Pill> {
    private Tag<AnchorElement> anchor = Tag.asAnchor();

    public Pill(ListItem item) {
      super(item);
      this.addChild(this.anchor);
    }

    public Badge addBadge() {
      Badge badge = new Badge();
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
    public Pill onActivate(EventHandler<Void> handler) {
      return this.addHandler(HasActivation.FireableEvent.ACTIVATE, handler);
    }

    @Override
    public Pill onDeactivate(EventHandler<Void> handler) {
      return this.addHandler(HasActivation.FireableEvent.DEACTIVATE, handler);
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
      return this;
    }

    @Override
    public Pill deactivate() {
      this.css().remove(State.ACTIVE);
      return this;
    }

    @Override
    public boolean active() {
      return this.css().contains(State.ACTIVE);
    }

    @Override
    public boolean match(Element element) {
      return this.anchor.id().equals(element.getId());
    }
  }
}