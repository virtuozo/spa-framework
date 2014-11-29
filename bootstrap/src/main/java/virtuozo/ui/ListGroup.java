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

import virtuozo.ui.Component;
import virtuozo.ui.Composite;
import virtuozo.ui.CssClass;
import virtuozo.ui.Elements;
import virtuozo.ui.StyleChooser;
import virtuozo.ui.Tag;
import virtuozo.ui.api.ActivationEvent;
import virtuozo.ui.api.DeactivationEvent;
import virtuozo.ui.api.HasActivation;
import virtuozo.ui.api.HasClickHandlers;
import virtuozo.ui.api.ActivationEvent.ActivationHandler;
import virtuozo.ui.api.DeactivationEvent.DeactivationHandler;
import virtuozo.ui.css.State;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;

public class ListGroup extends Component<ListGroup>{
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
    public ListGroupItem onActivate(ActivationHandler handler) {
      return this.addHandler(ActivationEvent.TYPE, handler);
    }
    
    @Override
    public ListGroupItem onDeactivate(DeactivationHandler handler) {
      return this.addHandler(DeactivationEvent.TYPE, handler);
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
