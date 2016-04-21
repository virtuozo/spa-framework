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

import virtuozo.infra.Clause;
import virtuozo.infra.EventInterceptor;
import virtuozo.infra.EventManager;
import virtuozo.infra.events.CssChangeEvent;
import virtuozo.infra.events.CssChangeEvent.CssChangeHandler;
import virtuozo.infra.events.DetachChildrenEvent.DetachChildrenHandler;
import virtuozo.infra.events.HideEvent;
import virtuozo.infra.events.HideEvent.HideHandler;
import virtuozo.infra.events.ScrollSpyEvent;
import virtuozo.infra.events.ScrollSpyEvent.ScrollSpyHandler;
import virtuozo.infra.events.ShowEvent;
import virtuozo.infra.events.ShowEvent.ShowHandler;
import virtuozo.infra.events.ToggleEvent;
import virtuozo.infra.events.ToggleEvent.ToggleHandler;
import virtuozo.infra.handlers.AttachHandler;
import virtuozo.infra.handlers.DetachHandler;
import virtuozo.interfaces.Style.Computer;

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
import com.google.gwt.user.client.ui.TextRectangle;
import com.google.gwt.user.client.ui.WidgetHolder;

@SuppressWarnings("unchecked")
public class Component<C extends Component<C>> implements HasVisibility<C>, UIComponent {
  private WidgetHolder holder;
  
  private ChildrenProxy proxy = new ChildrenProxy();

  private final Style style = new Style(this);

  private Classes classes;
  
  private EventManager bus;
  
  private Measurement measurement = new Measurement();
  
  protected Component() {
    super();
  }
  
  protected Component(Element element) {
    this.holder = new WidgetHolder(element, this);
    this.proxy.holder(this.holder);
    this.bus = EventManager.create(this.holder.getHandlerManager());
    this.id(DOM.createUniqueId());
    this.classes = new Classes(this.holder);
  }
  
  protected Component(Component<?> widget){
    this.incorporate(widget);
  }
  
  protected C incorporate(Component<?> widget){
    this.holder = widget.holder;
    this.holder.reference(this);
    this.proxy.holder(this.holder);
    this.classes = widget.classes;
    this.bus = widget.bus;
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
  
  protected C html(String html){
    this.element().setInnerHTML(html);
    return (C) this;
  }
  
  protected String html(){
    return this.element().getInnerHTML();
  }
  
  /** Element behaviors **/
  protected <E extends Element> E element() {
    return this.holder.getElement().cast();
  }
  
  public C onCssChange(CssChangeHandler handler){
    this.bus.add(CssChangeEvent.TYPE, handler);
    return (C) this;
  }
  
  public String id() {
    return this.holder.element().id();
  }

  public C id(String id) {
    this.holder.element().id(id);
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
    return this.addHandler(HideEvent.TYPE, handler);
  }

  @Override
  public C onShow(ShowHandler handler) {
    return this.addHandler(ShowEvent.TYPE, handler);
  }

  @Override
  public C onToggleVisibility(ToggleHandler handler) {
    return this.addHandler(ToggleEvent.TYPE, handler);
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
  
  public C onEvent(EventInterceptor interceptor){
    this.holder.setInterceptor(interceptor);
    return (C) this;
  }
  
  public C onScroll(ScrollSpyHandler handler){
    this.addHandler(ScrollSpyEvent.TYPE, handler);
    return (C) this;
  }
  
  public Measurement measurement() {
    return measurement;
  }
  
  public double left() {
    return this.measurement.rectangle().left();
  }
  
  public double top() {
    return this.measurement.rectangle().top();
  }

  public Offset offset() {
    return Component.this.holder.dimensions().offset();
  }

  public Offset offsetParent() {
    return Component.this.holder.dimensions().offsetParent();
  }
  
  public int scrollHeight(){
    return this.holder.dimensions().scrollHeight();
  }

  public int scrollLeft() {
    return this.holder.dimensions().scrollLeft();
  }

  public C scrollLeft(int left) {
    this.holder.dimensions().scrollLeft(left);
    return (C) this;
  }

  public C scrollTo() {
    double left = Window.getScrollLeft() + this.left();
    double top = Window.getScrollTop() + this.top();
    
    Window.scrollTo((int) left, (int) top);
    return (C) this;
  }

  public int scrollTop() {
    return this.holder.dimensions().scrollTop();
  }

  public C scrollTop(int top) {
    this.holder.dimensions().scrollTop(top);
    return (C) this;
  }
  
  public int scrollWidth(){
    return this.holder.dimensions().scrollWidth();
  }
  
  public C screenCenter() {
    Component.this.holder.dimensions().screenCenter();
    return (C) this;
  }
  
  protected C onDetachChildren(DetachChildrenHandler handler){
    this.proxy.onDetachChildren(handler);
    return (C) this;
  }

  protected C detachChildren() {
    this.proxy.detachChildren();
    return (C) this;
  }

  protected C removeChild(UIComponent child) {
    this.proxy.removeChild(child);
    return (C) this;
  }

  protected C addChild(UIComponent add) {
    this.proxy.addChild(add);
    return (C) this;
  }
  
  protected C addFirstChild(UIComponent add){
    this.proxy.addFirstChild(add);
    return (C) this;
  }

  protected C adoptChild(UIComponent child) {
    this.proxy.adoptChild(child);
    return (C) this;
  }
  
  protected C fakeParent(UIComponent parent){
    this.proxy.holder(parent.asComponent().holder());
    return (C) this;
  }
  
  protected C tradeParent(UIComponent parent){
    this.proxy.tradeParent(parent);
    return (C) this;
  }

  protected C insertChild(UIComponent add, UIComponent before) {
    this.proxy.insertChild(add, before);
    return (C) this;
  }

  protected <UI extends UIComponent> Iterable<UI> childrenComponents() {
    return this.proxy.childrenComponents();
  }
  
  protected <UI extends UIComponent> UI firstChild(){
    return this.proxy.firstChild();
  }
  
  protected <UI extends UIComponent> UI lastChild(){
    return this.proxy.lastChild();
  }

  protected <UI extends UIComponent> UI childAt(int index) {
    return this.proxy.childAt(index);
  }
  
  protected <UI extends UIComponent> UI find(Clause clause){
    return this.proxy.find(clause);
  }
  
  protected <UI extends UIComponent> Iterable<UI> findAll(Clause clause){
    return this.proxy.findAll(clause);
  }

  protected int indexOfChild(UIComponent child) {
    return this.proxy.indexOfChild(child);
  }

  protected int childrenCount() {
    return this.proxy.childrenCount();
  }

  protected boolean hasChildren() {
    return this.proxy.hasChildren();
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

  public C fireEvent(GwtEvent<?> event) {
    this.holder.fireEvent(event);
    return (C) this;
  }

  public C fireNativeEvent(NativeEvent event){
    DomEvent.fireNativeEvent(event, this.holder());
    return (C) this;
  }

  EventManager eventBus() {
    return this.bus;
  }
  
  @Override
  public String toString() {
    return this.holder.toString();
  }
  
  class Measurement {
    public double innerHeight() {
      double height = this.rectangle().height();
      Computer computer = Component.this.style().computer();
      
      height = height - computer.borderHeight();
      height = height - computer.marginHeight();
      height = height - computer.paddingHeight();
      
      return height;
    }

    public double innerWidth() {
      double width = this.rectangle().width();
      Computer computer = Component.this.style().computer();
      
      width = width - computer.borderWidth();
      width = width - computer.marginWidth();
      width = width - computer.paddingWidth();
      
      return width;
    }

    public double outerHeight() {
      return Component.this.holder.getOffsetHeight();
    }

    public double outerWidth() {
      return Component.this.holder.getOffsetWidth();
    }
    
    public TextRectangle rectangle() {
      return Component.this.holder.dimensions().rectangle();
    }
  }
}