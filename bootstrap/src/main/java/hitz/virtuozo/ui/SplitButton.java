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

import hitz.virtuozo.infra.api.HasClickHandlers;
import hitz.virtuozo.infra.api.HasText;
import hitz.virtuozo.ui.CompositeClasses;
import hitz.virtuozo.ui.Tag;
import hitz.virtuozo.ui.Widget;
import hitz.virtuozo.ui.api.UIClass;
import hitz.virtuozo.ui.api.UIClasses;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;

public final class SplitButton extends Widget<SplitButton> implements HasText<SplitButton>, HasClickHandlers<SplitButton>{
  private final ButtonGroup group = new ButtonGroup();
  
  private final Button button = new Button();
  
  private final Button caret = new Button();
  
  private Tag<DivElement> dropdown = Tag.asDiv();
  
  private final Menu menu = new Menu();
  
  private CompositeClasses classes = new CompositeClasses(button.css(), caret.css());
  
  public SplitButton() {
    this.compound(this.group);
    this.addChild(this.dropdown);
    this.dropdown.addChild(new ButtonGroup().add(this.button).add(this.caret)).addChild(this.menu).css().set("dropdown");
    
    this.caret.addChild(new Caret()).css("dropdown-toggle");
    this.caret.onClick(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        SplitButton.this.menu.toggle();
      }
    });
  }
  
  public SplitButton up(){
    this.group.css().set("dropup");
    return this;
  }
  
  @Override
  public SplitButton onClick(ClickHandler handler) {
    this.button.onClick(handler);
    return this;
  }
  
  @Override
  public SplitButton onDoubleClick(DoubleClickHandler handler) {
    this.button.onDoubleClick(handler);
    return this;
  }
  
  @Override
  public SplitButton css(String... classes) {
    this.css().append(classes);
    return this;
  }
  
  @Override
  public SplitButton css(UIClass... classes) {
    this.css().append(classes);
    return this;
  }
  
  @Override
  public UIClasses css() {
    return this.classes;
  }
  
  @Override
  public String text() {
    return this.button.text();
  }
  
  public SplitButton text(String text) {
    this.button.text(text);
    return this;
  }
  
  public Menu menu(){
    return this.menu;
  }
}