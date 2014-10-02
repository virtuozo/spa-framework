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

import hitz.virtuozo.infra.CastIterable;
import hitz.virtuozo.infra.CastIterable.TypeCast;
import hitz.virtuozo.infra.StringProperty;
import hitz.virtuozo.infra.api.AttachHandler;
import hitz.virtuozo.infra.api.DetachHandler;
import hitz.virtuozo.infra.api.HasVisibility;
import hitz.virtuozo.infra.api.HideEvent;
import hitz.virtuozo.infra.api.HideEvent.HideHandler;
import hitz.virtuozo.infra.api.ShowEvent;
import hitz.virtuozo.infra.api.ShowEvent.ShowHandler;
import hitz.virtuozo.infra.api.ToggleEvent;
import hitz.virtuozo.infra.api.ToggleEvent.ToggleHandler;
import hitz.virtuozo.ui.api.Clause;
import hitz.virtuozo.ui.api.CssChangeEvent;
import hitz.virtuozo.ui.api.CssChangeHandler;
import hitz.virtuozo.ui.api.EventInterceptor;
import hitz.virtuozo.ui.api.UIClass;
import hitz.virtuozo.ui.api.UIClasses;
import hitz.virtuozo.ui.api.UIWidget;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchCancelHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Offset;
import com.google.gwt.user.client.ui.WidgetHolder;

@SuppressWarnings("unchecked")
public abstract class Widget<W extends Widget<W>> implements HasVisibility<W>, UIWidget {
  private WidgetHolder holder;

  private final Style style = new Style(this);

  private Classes classes;
  
  private StringProperty id = new StringProperty();
  
  private EventHandlers bus;
  
  public Widget() {
    super();
  }
  
  public Widget(Element element) {
    this.holder = new WidgetHolder(element, this);
    this.bus = new EventHandlers(this.holder.getHandlerManager());
    this.id.onChange(new hitz.virtuozo.infra.api.ChangeHandler<String>() {
      
      @Override
      public void onChange(String oldValue, String newValue) {
        Widget.this.holder.element().id(newValue);
      }
    });
    this.id(DOM.createUniqueId());
    this.classes = new Classes(this.holder);
  }
  
  public Widget(Widget<?> widget){
    this.compound(widget);
  }
  
  protected W compound(Widget<?> widget){
    this.holder = widget.holder;
    this.holder.reference(this);
    this.classes = widget.classes;
    this.id = widget.id;
    return (W) this;
  }
  
  protected WidgetHolder holder(){
    return this.holder;
  }

  @Override
  public Widget<W> asWidget() {
    return this;
  }
  
  public UIWidget parent(){
    return this.holder.parent();
  }

  /** Element behaviors **/
  public <E extends Element> E element() {
    return this.holder.getElement().cast();
  }
  
  public W onIdChange(hitz.virtuozo.infra.api.ChangeHandler<String> handler){
    this.id.onChange(handler);
    return (W) this;
  }
  
  public W onCssChange(CssChangeHandler handler){
    this.bus.add(CssChangeEvent.TYPE, handler);
    return (W) this;
  }
  
  public String id() {
    return this.id.get();
  }

  public W id(String id) {
    this.id.set(id);
    return (W) this;
  }
  
  protected W role(String role){
    return this.attribute("role", role);
  }
  
  public String attribute(String name){
    return this.holder.element().attribute(name);
  }

  public W attribute(String name, String value) {
    this.holder.element().attribute(name, value);
    return (W) this;
  }
  
  public W removeAttribute(String name) {
    this.holder.element().removeAttribute(name);
    return (W) this;
  }

  public String getAttribute(String name) {
    return this.holder.element().attribute(name);
  }

  public W name(String name) {
    this.holder.element().name(name);
    return (W) this;
  }

  public W title(String title) {
    this.holder.element().title(title);
    return (W) this;
  }

  protected W blur() {
    this.holder.element().blur();
    return (W) this;
  }

  protected W focus() {
    this.holder.element().focus();
    return (W) this;
  }

  public Style style() {
    return this.style;
  }

  public UIClasses css() {
    return this.classes;
  }
  
  public W css(UIClass... classes){
    this.classes.append(classes);
    return (W) this;
  }
  
  public W css(String... classes){
    this.classes.append(classes);
    return (W) this;
  }

  /** Visbility behaviors **/
  @Override
  public W onHide(HideHandler handler) {
    return this.addHandler(HideEvent.type(), handler);
  }

  @Override
  public W onShow(ShowHandler handler) {
    return this.addHandler(ShowEvent.type(), handler);
  }

  @Override
  public W onToggleVisibility(ToggleHandler handler) {
    return this.addHandler(ToggleEvent.type(), handler);
  }
  
  @Override
  public W show() {
    this.fireEvent(new ShowEvent());
    this.holder.setVisible(true);
    return (W) this;
  }
  
  @Override
  public W hide() {
    this.fireEvent(new HideEvent());
    this.holder.setVisible(false);
    return (W) this;
  }
  
  @Override
  public W toggleVisibility() {
    this.fireEvent(new ToggleEvent());
    if (this.holder.isVisible()) {
      return this.hide();
    }

    return this.show();
  }
  
  @Override
  public boolean visible() {
    return this.holder.isVisible();
  }
  
  protected W attach(){
    this.holder.attach();
    return (W) this;
  }

  /** Traverse behaviors **/
  public boolean attached() {
    return this.holder.isAttached();
  }

  public W detach() {
    this.holder.removeFromParent();
    return (W) this;
  }

  public W onAttach(AttachHandler handler) {
    this.holder.addAttachHandler(handler);
    return (W) this;
  }

  public W onDetach(DetachHandler handler) {
    this.holder.addAttachHandler(handler);
    return (W) this;
  }
  
  public W onEvent(EventInterceptor interceptor){
    this.holder.setInterceptor(interceptor);
    return (W) this;
  }

  protected W detachChildren() {
    this.holder.detachChildren();
    return (W) this;
  }

  protected W removeChild(UIWidget child) {
    this.holder.remove(child.asWidget().holder);
    return (W) this;
  }

  protected W addChild(UIWidget add) {
    this.holder.add(add.asWidget().holder);
    return (W) this;
  }

  protected W adoptChild(UIWidget child) {
    this.holder.adoptIt(child.asWidget().holder);
    return (W) this;
  }
  
  protected W tradeParent(UIWidget parent){
    UIWidget current = this.parent();
    parent.asWidget().adoptChild(this);
    current.asWidget().adoptChild(parent);
    return (W) this;
  }

  protected W insertChild(UIWidget add, UIWidget before) {
    this.holder.insert(add.asWidget().holder, before.asWidget().holder);
    return (W) this;
  }

  protected Iterable<UIWidget> childrenWidgets() {
    return new CastIterable<UIWidget, WidgetHolder>(this.holder.children()).use(new TypeCast<UIWidget, WidgetHolder>() {
      @Override
      public UIWidget castFrom(WidgetHolder instance) {
        return instance.getReference();
      }
    });
  }

  protected <C extends UIWidget> C childAt(int index) {
    return this.holder.childAt(index).getReference();
  }
  
  protected <C extends UIWidget> C find(Clause clause){
    for(UIWidget child : this.childrenWidgets()){
      if(clause.matches(child)){
        return (C) child;
      }
    }
    
    return null;
  }
  
  protected Iterable<UIWidget> findAll(Clause clause){
    List<UIWidget> children = new ArrayList<UIWidget>();
    
    for(UIWidget child : this.childrenWidgets()){
      if(clause.matches(child)){
        children.add(child);
      }
    }
    
    return children;
  }

  protected int indexOfChild(UIWidget child) {
    return this.holder.indexOf(child.asWidget().holder);
  }

  protected int childrenCount() {
    return this.holder.childrenCount();
  }

  protected boolean hasChildren() {
    return this.holder.hasChildren();
  }

  /** Dimension behaviors **/
  public int innerHeight() {
    return this.holder.dimensions().innerHeight();
  }

  public int innerWidth() {
    return this.holder.dimensions().innerWidth();
  }

  public int left() {
    return this.holder.dimensions().left();
  }

  public Offset offset() {
    return this.holder.dimensions().offset();
  }

  public int offsetHeight() {
    return this.holder.getOffsetHeight();
  }

  public int offsetWidth() {
    return this.holder.getOffsetWidth();
  }

  public Offset offsetParent() {
    return this.holder.dimensions().offsetParent();
  }

  public int outerHeight() {
    return this.holder.dimensions().outerHeight();
  }

  public int outerWidth() {
    return this.holder.dimensions().outerWidth();
  }

  public int scrollLeft() {
    return this.holder.dimensions().scrollLeft();
  }

  public W scrollLeft(int left) {
    this.holder.dimensions().scrollLeft(left);
    return (W) this;
  }

  public W scrollTo(int left, int top) {
    this.holder.dimensions().scrollTo(left, top);
    return (W) this;
  }

  public int scrollTop() {
    return this.holder.dimensions().scrollTop();
  }

  public W scrollTop(int top) {
    this.holder.dimensions().scrollTop(top);
    return (W) this;
  }

  public int top() {
    return this.holder.dimensions().top();
  }

  public W screenCenter() {
    this.holder.dimensions().screenCenter();
    return (W) this;
  }

  /** Event Behaviors **/
  protected W on(BlurHandler handler) {
    this.holder.addDomHandler(handler, BlurEvent.getType());
    return (W) this;
  }

  protected W on(FocusHandler handler) {
    this.holder.addDomHandler(handler, FocusEvent.getType());
    return (W) this;
  }

  protected W on(ChangeHandler handler) {
    this.holder.addDomHandler(handler, ChangeEvent.getType());
    return (W) this;
  }

  protected W on(ClickHandler handler) {
    this.holder.addDomHandler(handler, ClickEvent.getType());
    return (W) this;
  }

  protected W on(DoubleClickHandler handler) {
    this.holder.addDomHandler(handler, DoubleClickEvent.getType());
    return (W) this;
  }

  protected W on(KeyPressHandler handler) {
    this.holder.addDomHandler(handler, KeyPressEvent.getType());
    return (W) this;
  }

  protected W on(KeyDownHandler handler) {
    this.holder.addDomHandler(handler, KeyDownEvent.getType());
    return (W) this;
  }

  protected W on(KeyUpHandler handler) {
    this.holder.addDomHandler(handler, KeyUpEvent.getType());
    return (W) this;
  }

  protected W on(MouseDownHandler handler) {
    this.holder.addDomHandler(handler, MouseDownEvent.getType());
    return (W) this;
  }

  protected W on(MouseMoveHandler handler) {
    this.holder.addDomHandler(handler, MouseMoveEvent.getType());
    return (W) this;
  }

  protected W on(MouseOutHandler handler) {
    this.holder.addDomHandler(handler, MouseOutEvent.getType());
    return (W) this;
  }

  protected W on(MouseOverHandler handler) {
    this.holder.addDomHandler(handler, MouseOverEvent.getType());
    return (W) this;
  }

  protected W on(MouseUpHandler handler) {
    this.holder.addDomHandler(handler, MouseUpEvent.getType());
    return (W) this;
  }

  protected W on(MouseWheelHandler handler) {
    this.holder.addDomHandler(handler, MouseWheelEvent.getType());
    return (W) this;
  }

  protected W on(TouchCancelHandler handler) {
    this.holder.addDomHandler(handler, TouchCancelEvent.getType());
    return (W) this;
  }

  protected W on(TouchEndHandler handler) {
    this.holder.addDomHandler(handler, TouchEndEvent.getType());
    return (W) this;
  }

  protected W on(TouchMoveHandler handler) {
    this.holder.addDomHandler(handler, TouchMoveEvent.getType());
    return (W) this;
  }

  protected W on(TouchStartHandler handler) {
    this.holder.addDomHandler(handler, TouchStartEvent.getType());
    return (W) this;
  }

  protected final <H extends EventHandler> W addHandler(GwtEvent.Type<H> type, H handler) {
    this.bus.add(type, handler);
    return (W) this;
  }

  protected W fireEvent(GwtEvent<?> event) {
    this.holder.fireEvent(event);
    return (W) this;
  }

  protected W fireNativeEvent(NativeEvent event){
    DomEvent.fireNativeEvent(event, this.holder());
    return (W) this;
  }

  EventHandlers eventBus() {
    return this.bus;
  }
  
  @Override
  public String toString() {
    return this.holder.toString();
  }
}