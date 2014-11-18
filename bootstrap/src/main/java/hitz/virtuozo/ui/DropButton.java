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

import hitz.virtuozo.infra.api.HasText;
import hitz.virtuozo.ui.Tag;
import hitz.virtuozo.ui.Widget;
import hitz.virtuozo.ui.api.UIClass;
import hitz.virtuozo.ui.api.UIClasses;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public final class DropButton extends Widget<DropButton> implements HasText<DropButton> {
  private final Button dropButton = new Button();

  private Tag<DivElement> dropdown = Tag.asDiv();

  private final Menu menu = new Menu();
  
  public DropButton() {
    this.incorporate(new ButtonGroup());
    this.addChild(this.dropdown);
    Caret caret = new Caret();
    this.dropdown.addChild(this.dropButton.addChild(caret)).addChild(this.menu).css().set("dropdown");

    this.dropButton.css("dropdown-toggle");
    this.dropButton.onClick(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        DropButton.this.menu.toggle();
      }
    });
  }

  public DropButton up() {
    this.dropdown.css().set("dropup");
    return this;
  }
  
  public DropButton down(){
    this.dropdown.css().remove("dropup");
    return this;
  }
  
  @Override
  public DropButton css(String... classes) {
    this.dropButton.css(classes);
    return this;
  }
  
  @Override
  public DropButton css(UIClass... classes) {
    this.dropButton.css(classes);
    return this;
  }

  @Override
  public UIClasses css() {
    return this.dropButton.css();
  }

  @Override
  public String text() {
    return this.dropButton.text();
  }

  public DropButton text(String text) {
    this.dropButton.text(text);
    return this;
  }

  public Menu menu() {
    return this.menu;
  }
}