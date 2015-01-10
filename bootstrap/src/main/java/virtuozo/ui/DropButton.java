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

import virtuozo.ui.interfaces.HasText;
import virtuozo.ui.interfaces.UIClass;
import virtuozo.ui.interfaces.UIClasses;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public final class DropButton extends Component<DropButton> implements HasText<DropButton> {
  private final Button dropButton = Button.create();

  private Tag<DivElement> dropdown = Tag.asDiv();

  private final Menu menu = Menu.create();
  
  public static DropButton create(){
    return new DropButton();
  }
  
  private DropButton() {
    this.incorporate(ButtonGroup.create());
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