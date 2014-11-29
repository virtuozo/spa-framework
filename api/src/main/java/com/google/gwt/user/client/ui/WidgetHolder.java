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

package com.google.gwt.user.client.ui;

import java.util.Iterator;

import virtuozo.infra.CastIterator;
import virtuozo.ui.api.CssChangeEvent;
import virtuozo.ui.api.DetachChildrenEvent;
import virtuozo.ui.api.EventInterceptor;
import virtuozo.ui.api.UIComponent;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;

public final class WidgetHolder extends ComplexPanel {
  private final ElementHolder element = new ElementHolder();

  private DimensionHolder dimensions;

  private Object reference;
  
  private EventInterceptor interceptor = new EventInterceptor() {
    @Override
    public boolean shouldFire(Event event) {
      return true;
    }
  };

  public WidgetHolder(Element element, Object reference) {
    super.setElement(element);
    this.reference = reference;
  }

  public WidgetHolder reference(Object reference) {
    this.reference = reference;
    return this;
  }
  
  public void setInterceptor(EventInterceptor interceptor) {
    this.interceptor = interceptor;
  }
  
  @Override
  public void onBrowserEvent(Event event) {
    if(this.interceptor.shouldFire(event)){
      super.onBrowserEvent(event);
    }
  }

  public void attach() {
    this.onAttach();
  }

  public UIComponent parent() {
    return (UIComponent) ((WidgetHolder) super.getParent()).getReference();
  }

  public <T> T getReference() {
    return (T) this.reference;
  }

  @Override
  public HandlerManager getHandlerManager() {
    return this.ensureHandlers();
  }

  @Override
  public void addStyleName(String style) {
    super.addStyleName(style);
    this.fireEvent(new CssChangeEvent());
  }

  @Override
  public void setStyleName(String style) {
    super.setStyleName(style);
    this.fireEvent(new CssChangeEvent());
  }

  @Override
  public void removeStyleName(String style) {
    super.removeStyleName(style);
    this.fireEvent(new CssChangeEvent());
  }

  public DimensionHolder dimensions() {
    if (this.dimensions == null) {
      this.dimensions = new DimensionHolder();
    }
    return this.dimensions;
  }

  public ElementHolder element() {
    return this.element;
  }

  public void add(WidgetHolder child) {
    if (child.getParent() == null) {
      this.adoptIt(child);
    }
  }

  public void adoptIt(WidgetHolder child) {
    this.add(child.asWidget(), this.getElement());
  }

  public void insert(WidgetHolder add, WidgetHolder before) {
    int beforeIndex = this.getWidgetIndex(before);
    this.insert(add, this.getElement(), beforeIndex, true);
  }

  public Iterator<WidgetHolder> children() {
    return new CastIterator<WidgetHolder, Widget>(this.iterator());
  }

  public void detachChildren() {
    for (Widget child : this.getChildren()) {
      child.removeFromParent();
    }
    this.clear();
  }

  public WidgetHolder childAt(int index) {
    return (WidgetHolder) this.getWidget(index);
  }

  public int indexOf(WidgetHolder child) {
    return this.getWidgetIndex(child);
  }

  public int childrenCount() {
    return this.getWidgetCount();
  }

  public boolean hasChildren() {
    return this.getWidgetCount() > 0;
  }

  public final class DimensionHolder {

    public int innerHeight() {
      return this.element().getClientHeight();
    }

    public int innerWidth() {
      return this.element().getClientWidth();
    }

    public int left() {
      return this.left(this.element());
    }

    native int left(Element element)/*-{
			return element.getBoundingClientRect().left;
    }-*/;

    public Offset offset() {
      return offset(this.element());
    }

    public Offset offsetParent() {
      return offset(WidgetHolder.this.getParent().getElement());
    }

    private Offset offset(Element element) {
      return new Offset(element.getAbsoluteLeft(), element.getAbsoluteTop());
    }

    public int outerHeight() {
      return this.element().getOffsetHeight();
    }

    public int outerWidth() {
      return this.element().getOffsetWidth();
    }

    public int scrollLeft() {
      return this.element().getScrollLeft();
    }

    public void scrollLeft(int left) {
      this.element().setScrollLeft(left);
    }

    public void scrollTo(int left, int top) {
      this.scrollLeft(left);
      this.scrollTop(top);
    }

    public int scrollTop() {
      return this.element().getScrollTop();
    }

    public void scrollTop(int top) {
      this.element().setScrollTop(top);
    }

    public int top() {
      return this.top(this.element());
    }

    native int top(Element element)/*-{
			return element.getBoundingClientRect().top;
    }-*/;

    public void screenCenter() {
      int width = this.outerWidth();
      int height = this.outerHeight();

      int left = (Window.getClientWidth() - width) >> 1;
      int top = (Window.getClientHeight() - height) >> 1;

      int computedLeft = Math.max(Window.getScrollLeft() + left, 0) - Document.get().getBodyOffsetLeft();
      int computedTop = Math.max(Window.getScrollTop() + top, 0) - Document.get().getBodyOffsetTop();

      Element element = WidgetHolder.this.getElement();
      element.getStyle().setPropertyPx("left", computedLeft);
      element.getStyle().setPropertyPx("top", computedTop);
      element.getStyle().setPosition(Position.ABSOLUTE);
    }

    private Element element() {
      return WidgetHolder.this.getElement();
    }
  }

  public final class ElementHolder {
    public String id() {
      return WidgetHolder.this.getElement().getId();
    }

    public void id(String id) {
      WidgetHolder.this.getElement().setId(id);
    }

    public void attribute(String name, String value) {
      WidgetHolder.this.getElement().setAttribute(name, value);
    }

    public void removeAttribute(String name) {
      WidgetHolder.this.getElement().removeAttribute(name);
    }

    public String attribute(String name) {
      return WidgetHolder.this.getElement().getAttribute(name);
    }

    public void name(String name) {
      this.attribute("name", name);
    }

    public void title(String title) {
      WidgetHolder.this.getElement().setTitle(title);
    }

    public void blur() {
      WidgetHolder.this.getElement().blur();
    }

    public void focus() {
      WidgetHolder.this.getElement().focus();
    }
  }
}