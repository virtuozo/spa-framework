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

import hitz.virtuozo.infra.BrowserEventInterceptor;
import hitz.virtuozo.ui.OrderList.Type;
import hitz.virtuozo.ui.api.ActivationEvent;
import hitz.virtuozo.ui.api.HasClickHandlers;
import hitz.virtuozo.ui.api.HasMouseHandlers;
import hitz.virtuozo.ui.api.HasText;
import hitz.virtuozo.ui.api.ActivationEvent.ActivationHandler;
import hitz.virtuozo.ui.api.DeactivationEvent;
import hitz.virtuozo.ui.api.DeactivationEvent.DeactivationHandler;
import hitz.virtuozo.ui.api.HasActivation;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;

public class Menu extends Component<Menu>{
  private OrderList menu;
  
  private boolean hover;
  
  private final ClickHandler closeHandler = new ClickHandler() {
    @Override
    public void onClick(ClickEvent event) {
      Menu.this.close();
    }
  };

  public Menu() {
    super();
    this.menu = new OrderList(Type.UNORDERED).hide();
    this.incorporate(this.menu);
    this.role("menu").css().set("dropdown-menu");
    BrowserEventInterceptor.get().onClick(this.closeHandler);
    this.on(new MouseOverHandler() {

      @Override
      public void onMouseOver(MouseOverEvent event) {
        Menu.this.hover = true;
      }
    }).on(new MouseOutHandler() {

      @Override
      public void onMouseOut(MouseOutEvent event) {
        Menu.this.hover = false;
      }
    });
  }
  
  Menu(Menu menu){
    super(menu);
    this.menu = menu.menu;
  }
  
  public Menu align(Alignment align){
    this.menu.css(align);
    return this;
  }
  
  public MenuItem addItem() {
    return new MenuItem(this.menu.addItem()).onClick(this.closeHandler);
  }
  
  public Menu addHeader(String text){
    this.menu.addItem().text(text).css().set("dropdown-header");
    return this;
  }

  public Menu addDivider() {
    this.menu.addItem().css().set("divider");
    return this;
  }

  public Menu open() {
    this.parent().asComponent().css("open");
    this.menu.show();
    return this;
  }

  public Menu close() {
    this.parent().asComponent().css().remove("open");
    this.menu.hide();
    return this;
  }
  
  public Menu toggle(){
    if(this.css().contains("open")){
      return this.close();
    }
    
    return this.open();
  }
  
  public boolean hover() {
    return hover;
  }

  public class MenuItem extends Component<MenuItem> implements HasText<MenuItem>, HasClickHandlers<MenuItem>, HasMouseHandlers<MenuItem>, HasActivation<MenuItem> {
    private Tag<AnchorElement> anchor = Tag.asAnchor().role("menuitem");

    public MenuItem(ListItem item) {
      super(item);
      this.role("presentation").addChild(this.anchor);
    }
    
    @Override
    public String text() {
      return this.anchor.text();
    }

    @Override
    public MenuItem text(String text) {
      this.anchor.text(text);
      return this;
    }

    @Override
    public MenuItem onMouseDown(MouseDownHandler handler) {
      this.anchor.onMouseDown(handler);
      return this;
    }

    @Override
    public MenuItem onMouseMove(MouseMoveHandler handler) {
      this.anchor.onMouseMove(handler);
      return this;
    }

    @Override
    public MenuItem onMouseOut(MouseOutHandler handler) {
      this.anchor.onMouseOut(handler);
      return this;
    }

    @Override
    public MenuItem onMouseOver(MouseOverHandler handler) {
      this.anchor.onMouseOver(handler);
      return this;
    }

    @Override
    public MenuItem onMouseUp(MouseUpHandler handler) {
      this.anchor.onMouseUp(handler);
      return this;
    }

    @Override
    public MenuItem onMouseWheel(MouseWheelHandler handler) {
      this.anchor.onMouseWheel(handler);
      return this;
    }

    @Override
    public MenuItem onClick(ClickHandler handler) {
      this.anchor.onClick(handler);
      return this;
    }

    @Override
    public MenuItem onDoubleClick(DoubleClickHandler handler) {
      this.anchor.onDoubleClick(handler);
      return this;
    }

    @Override
    public MenuItem activate() {
      this.css("active");
      return this.fireEvent(new ActivationEvent());
    }

    @Override
    public MenuItem onActivate(ActivationHandler handler) {
      return this.addHandler(ActivationEvent.TYPE, handler);
    }
    

    @Override
    public MenuItem deactivate() {
      this.css().remove("active");
      return this;
    }

    @Override
    public MenuItem onDeactivate(DeactivationHandler handler) {
      return this.addHandler(DeactivationEvent.TYPE, handler);
    }

    @Override
    public boolean active() {
      return this.css().contains("active");
    }

    @Override
    public boolean match(Element element) {
      return this.anchor.id().equals(element.getId());
    }
  }
  
  public static class Alignment extends CssClass{
    private Alignment(String name) {
      super(name);
    }
    
    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }
    
    public static final Alignment LEFT = new Alignment("dropdown-menu-left");
    public static final Alignment RIGHT = new Alignment("dropdown-menu-right");
    private static final StyleChooser STYLES = new StyleChooser(LEFT, RIGHT);
  }
}