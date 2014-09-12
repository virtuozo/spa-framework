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
import hitz.virtuozo.ui.Composite;
import hitz.virtuozo.ui.CssClass;
import hitz.virtuozo.ui.Elements;
import hitz.virtuozo.ui.StyleChooser;
import hitz.virtuozo.ui.Tag;
import hitz.virtuozo.ui.Widget;
import hitz.virtuozo.ui.api.HasActivation;
import hitz.virtuozo.ui.css.State;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;

public class ListGroup extends Widget<ListGroup>{
  private ActivationHelper activationHelper = new ActivationHelper();
  
  public ListGroup() {
    super(Elements.div());
    this.css().set("list-group");
  }
  
  public ListGroup reset(){
    this.activationHelper.reset();
    return this;
  }
  
  public ListGroupItem addItem(){
    ListGroupItem item = new ListGroupItem();
    this.addChild(item);
    this.activationHelper.add(item);
    return item;
  }
  
  public class ListGroupItem extends Composite<ListGroupItem> implements HasClickHandlers<ListGroupItem>, HasActivation<ListGroupItem>{
    public ListGroupItem() {
      super(Tag.asAnchor());
      this.css().set("list-group-item");
    }
    
    public Heading addHeading(){
      Heading heading = new Heading(Heading.Level.FOUR);
      heading.css().set("list-group-item-heading");
      this.add(heading);
      return heading;
    }
    
    public Paragraph addText(){
      Paragraph text = new Paragraph();
      text.css().set("list-group-item-text");
      this.add(text);
      return text;
    }
    
    public Badge addBadge(){
      Badge badge = new Badge();
      this.add(badge);
      return badge;
    }
    
    @Override
    public ListGroupItem onActivate(EventHandler<Void> handler) {
      return this.addHandler(HasActivation.FireableEvent.ACTIVATE, handler);
    }
    
    @Override
    public ListGroupItem onDeactivate(EventHandler<Void> handler) {
      return this.addHandler(HasActivation.FireableEvent.DEACTIVATE, handler);
    }
    
    @Override
    public ListGroupItem onClick(ClickHandler handler) {
      return this.on(handler);
    }
    
    @Override
    public ListGroupItem onDoubleClick(DoubleClickHandler handler) {
      return this.on(handler);
    }

    @Override
    public ListGroupItem activate() {
      this.css(State.ACTIVE);
      return this;
    }

    @Override
    public ListGroupItem deactivate() {
      this.css().remove(State.ACTIVE);
      return this;
    }

    @Override
    public boolean active() {
      return this.css().contains(State.ACTIVE);
    }

    @Override
    public boolean match(Element element) {
      return this.id().equals(element.getId());
    }
  }
  
  public static class ItemColor extends CssClass{
    private ItemColor(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }
    
    public static final ItemColor SUCCESS = new ItemColor("list-group-item-success");
    public static final ItemColor INFO = new ItemColor("list-group-item-info");
    public static final ItemColor WARNING = new ItemColor("list-group-item-warning");
    public static final ItemColor DANGER = new ItemColor("list-group-item-danger");
    private static final StyleChooser STYLES = new StyleChooser(SUCCESS, INFO, WARNING, DANGER);
  }
}
