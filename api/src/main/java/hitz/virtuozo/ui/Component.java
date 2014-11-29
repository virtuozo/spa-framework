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
import hitz.virtuozo.ui.api.AttachHandler;
import hitz.virtuozo.ui.api.Clause;
import hitz.virtuozo.ui.api.CssChangeEvent;
import hitz.virtuozo.ui.api.CssChangeHandler;
import hitz.virtuozo.ui.api.DetachChildrenEvent;
import hitz.virtuozo.ui.api.DetachChildrenEvent.DetachChildrenHandler;
import hitz.virtuozo.ui.api.DetachHandler;
import hitz.virtuozo.ui.api.EventInterceptor;
import hitz.virtuozo.ui.api.HasComponents;
import hitz.virtuozo.ui.api.HasVisibility;
import hitz.virtuozo.ui.api.HideEvent;
import hitz.virtuozo.ui.api.HideEvent.HideHandler;
import hitz.virtuozo.ui.api.ScrollSpyEvent;
import hitz.virtuozo.ui.api.ScrollSpyEvent.ScrollSpyHandler;
import hitz.virtuozo.ui.api.ShowEvent;
import hitz.virtuozo.ui.api.ShowEvent.ShowHandler;
import hitz.virtuozo.ui.api.ToggleEvent;
import hitz.virtuozo.ui.api.ToggleEvent.ToggleHandler;
import hitz.virtuozo.ui.api.UIClass;
import hitz.virtuozo.ui.api.UIClasses;
import hitz.virtuozo.ui.api.UIComponent;

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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Offset;
import com.google.gwt.user.client.ui.WidgetHolder;

@SuppressWarnings("unchecked")
public abstract class Component<C extends Component<C>> implements HasVisibility<C>, UIComponent {
  private WidgetHolder holder;

  private final Style style = new Style(this);

  private Classes classes;
  
  private StringProperty id = new StringProperty();
  
  private EventHandlers bus;
  
  public Component() {
    super();
  }
  
  public Component(Element element) {
    this.holder = new WidgetHolder(element, this);
    this.bus = new EventHandlers(this.holder.getHandlerManager());
    this.id.onChange(new hitz.virtuozo.infra.api.ChangeHandler<String>() {
      
      @Override
      public void onChange(String oldValue, String newValue) {
        Component.this.holder.element().id(newValue);
      }
    });
    this.id(DOM.createUniqueId());
    this.classes = new Classes(this.holder);
  }
  
  public Component(Component<?> widget){
    this.incorporate(widget);
  }
  
  protected C incorporate(Component<?> widget){
    this.holder = widget.holder;
    this.holder.reference(this);
    this.classes = widget.classes;
    this.bus = widget.bus;
    this.id = widget.id;
    return (C) this;
  }
  
  protected WidgetHolder holder(){
    return this.holder;
  }

  @Override
  public C asComponent() {
    return (C) this;
  }
  
  public UIComponent parent(){
    return this.holder.parent();
  }

  /** Element behaviors **/
  public <E extends Element> E element() {
    return this.holder.getElement().cast();
  }
  
  public C onIdChange(hitz.virtuozo.infra.api.ChangeHandler<String> handler){
    this.id.onChange(handler);
    return (C) this;
  }
  
  public C onCssChange(CssChangeHandler handler){
    this.bus.add(CssChangeEvent.TYPE, handler);
    return (C) this;
  }
  
  public String id() {
    return this.id.get();
  }

  public C id(String id) {
    this.id.set(id);
    return (C) this;
  }
  
  protected C role(String role){
    return this.attribute("role", role);
  }
  
  public String attribute(String name){
    return this.holder.element().attribute(name);
  }

  public C attribute(String name, String value) {
    this.holder.element().attribute(name, value);
    return (C) this;
  }
  
  public C removeAttribute(String name) {
    this.holder.element().removeAttribute(name);
    return (C) this;
  }

  public String getAttribute(String name) {
    return this.holder.element().attribute(name);
  }

  public C name(String name) {
    this.holder.element().name(name);
    return (C) this;
  }

  public C title(String title) {
    this.holder.element().title(title);
    return (C) this;
  }

  protected C blur() {
    this.holder.element().blur();
    return (C) this;
  }

  protected C focus() {
    this.holder.element().focus();
    return (C) this;
  }

  public Style style() {
    return this.style;
  }

  public UIClasses css() {
    return this.classes;
  }
  
  public C css(UIClass... classes){
    this.classes.append(classes);
    return (C) this;
  }
  
  public C css(String... classes){
    this.classes.append(classes);
    return (C) this;
  }

  /** Visbility behaviors **/
  @Override
  public C onHide(HideHandler handler) {
    return this.addHandler(HideEvent.type(), handler);
  }

  @Override
  public C onShow(ShowHandler handler) {
    return this.addHandler(ShowEvent.type(), handler);
  }

  @Override
  public C onToggleVisibility(ToggleHandler handler) {
    return this.addHandler(ToggleEvent.type(), handler);
  }
  
  @Override
  public C show() {
    this.holder.setVisible(true);
    this.fireEvent(new ShowEvent());
    return (C) this;
  }
  
  @Override
  public C hide() {
    this.holder.setVisible(false);
    this.fireEvent(new HideEvent());
    return (C) this;
  }
  
  @Override
  public C toggleVisibility() {
    this.fireEvent(new ToggleEvent());
    if (this.visible()) {
      return this.hide();
    }

    return this.show();
  }
  
  @Override
  public boolean visible() {
    return this.holder.isVisible();
  }
  
  protected C attach(){
    this.holder.attach();
    return (C) this;
  }

  /** Traverse behaviors **/
  public <UI extends UIComponent> C attachTo(HasComponents<?, UI> parent){
    parent.add((UI) this);
    return (C) this;
  }
  
  public boolean attached() {
    return this.holder.isAttached();
  }

  public C detach() {
    this.holder.removeFromParent();
    return (C) this;
  }

  public C onAttach(AttachHandler handler) {
    this.holder.addAttachHandler(handler);
    return (C) this;
  }

  public C onDetach(DetachHandler handler) {
    this.holder.addAttachHandler(handler);
    return (C) this;
  }
  
  protected C onDetachChildren(DetachChildrenHandler handler){
    this.holder.addHandler(handler, DetachChildrenEvent.type());
    return (C) this;
  }
  
  public C onEvent(EventInterceptor interceptor){
    this.holder.setInterceptor(interceptor);
    return (C) this;
  }

  protected C detachChildren() {
    this.holder.detachChildren();
    this.fireEvent(new DetachChildrenEvent());
    return (C) this;
  }

  protected C removeChild(UIComponent child) {
    this.holder.remove(child.asComponent().holder);
    return (C) this;
  }

  protected C addChild(UIComponent add) {
    this.holder.add(add.asComponent().holder);
    return (C) this;
  }
  
  protected C firstChild(UIComponent add){
    if(!this.hasChildren()) {
      this.addChild(add);
      return (C) this;
    }
    
    return this.insertChild(add, this.childAt(0));
  }

  protected C adoptChild(UIComponent child) {
    this.holder.adoptIt(child.asComponent().holder);
    return (C) this;
  }
  
  protected C tradeParent(UIComponent parent){
    UIComponent current = this.parent();
    parent.asComponent().adoptChild(this);
    current.asComponent().adoptChild(parent);
    return (C) this;
  }

  protected C insertChild(UIComponent add, UIComponent before) {
    this.holder.insert(add.asComponent().holder, before.asComponent().holder);
    return (C) this;
  }

  protected <UI extends UIComponent> Iterable<UI> childrenComponents() {
    return new CastIterable<UI, WidgetHolder>(this.holder.children()).use(new TypeCast<UI, WidgetHolder>() {
      @Override
      public UI castFrom(WidgetHolder instance) {
        return instance.getReference();
      }
    });
  }

  protected <UI extends UIComponent> UI childAt(int index) {
    return this.holder.childAt(index).getReference();
  }
  
  protected <UI extends UIComponent> UI find(Clause clause){
    for(UIComponent child : this.childrenComponents()){
      if(clause.matches(child)){
        return (UI) child;
      }
    }
    
    return null;
  }
  
  protected <UI extends UIComponent> Iterable<UI> findAll(Clause clause){
    List<UI> children = new ArrayList<UI>();
    
    for(UIComponent child : this.childrenComponents()){
      if(clause.matches(child)){
        children.add((UI) child);
      }
    }
    
    return children;
  }

  protected int indexOfChild(UIComponent child) {
    return this.holder.indexOf(child.asComponent().holder);
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

  public C scrollLeft(int left) {
    this.holder.dimensions().scrollLeft(left);
    return (C) this;
  }

  public C scrollTo() {
    Window.scrollTo(Window.getScrollLeft() + this.left(), Window.getScrollTop() + this.top());
    return (C) this;
  }

  public int scrollTop() {
    return this.holder.dimensions().scrollTop();
  }

  public C scrollTop(int top) {
    this.holder.dimensions().scrollTop(top);
    return (C) this;
  }
  
  public int top() {
    return this.holder.dimensions().top();
  }

  public C screenCenter() {
    this.holder.dimensions().screenCenter();
    return (C) this;
  }

  /** Event Behaviors **/
  protected C on(BlurHandler handler) {
    this.holder.addDomHandler(handler, BlurEvent.getType());
    return (C) this;
  }

  protected C on(FocusHandler handler) {
    this.holder.addDomHandler(handler, FocusEvent.getType());
    return (C) this;
  }

  protected C on(ChangeHandler handler) {
    this.holder.addDomHandler(handler, ChangeEvent.getType());
    return (C) this;
  }

  protected C on(ClickHandler handler) {
    this.holder.addDomHandler(handler, ClickEvent.getType());
    return (C) this;
  }

  protected C on(DoubleClickHandler handler) {
    this.holder.addDomHandler(handler, DoubleClickEvent.getType());
    return (C) this;
  }

  protected C on(KeyPressHandler handler) {
    this.holder.addDomHandler(handler, KeyPressEvent.getType());
    return (C) this;
  }

  protected C on(KeyDownHandler handler) {
    this.holder.addDomHandler(handler, KeyDownEvent.getType());
    return (C) this;
  }

  protected C on(KeyUpHandler handler) {
    this.holder.addDomHandler(handler, KeyUpEvent.getType());
    return (C) this;
  }

  protected C on(MouseDownHandler handler) {
    this.holder.addDomHandler(handler, MouseDownEvent.getType());
    return (C) this;
  }

  protected C on(MouseMoveHandler handler) {
    this.holder.addDomHandler(handler, MouseMoveEvent.getType());
    return (C) this;
  }

  protected C on(MouseOutHandler handler) {
    this.holder.addDomHandler(handler, MouseOutEvent.getType());
    return (C) this;
  }

  protected C on(MouseOverHandler handler) {
    this.holder.addDomHandler(handler, MouseOverEvent.getType());
    return (C) this;
  }

  protected C on(MouseUpHandler handler) {
    this.holder.addDomHandler(handler, MouseUpEvent.getType());
    return (C) this;
  }

  protected C on(MouseWheelHandler handler) {
    this.holder.addDomHandler(handler, MouseWheelEvent.getType());
    return (C) this;
  }
  
  C on(ScrollSpyHandler handler){
    this.addHandler(ScrollSpyEvent.type(), handler);
    return (C) this;
  }

  protected C on(TouchCancelHandler handler) {
    this.holder.addDomHandler(handler, TouchCancelEvent.getType());
    return (C) this;
  }

  protected C on(TouchEndHandler handler) {
    this.holder.addDomHandler(handler, TouchEndEvent.getType());
    return (C) this;
  }

  protected C on(TouchMoveHandler handler) {
    this.holder.addDomHandler(handler, TouchMoveEvent.getType());
    return (C) this;
  }

  protected C on(TouchStartHandler handler) {
    this.holder.addDomHandler(handler, TouchStartEvent.getType());
    return (C) this;
  }

  protected final <H extends EventHandler> C addHandler(GwtEvent.Type<H> type, H handler) {
    this.bus.add(type, handler);
    return (C) this;
  }

  protected C fireEvent(GwtEvent<?> event) {
    this.holder.fireEvent(event);
    return (C) this;
  }

  protected C fireNativeEvent(NativeEvent event){
    DomEvent.fireNativeEvent(event, this.holder());
    return (C) this;
  }

  EventHandlers eventBus() {
    return this.bus;
  }
  
  @Override
  public String toString() {
    return this.holder.toString();
  }
}