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
import virtuozo.ui.CssClass;
import virtuozo.ui.Elements;
import virtuozo.ui.StyleChooser;
import virtuozo.ui.Text;
import virtuozo.ui.api.ActivationEvent;
import virtuozo.ui.api.DeactivationEvent;
import virtuozo.ui.api.HasActivation;
import virtuozo.ui.api.HasClickHandlers;
import virtuozo.ui.api.HasFocusHandlers;
import virtuozo.ui.api.HasIcon;
import virtuozo.ui.api.HasKeyHandlers;
import virtuozo.ui.api.HasMouseHandlers;
import virtuozo.ui.api.HasState;
import virtuozo.ui.api.HasText;
import virtuozo.ui.api.Icon;
import virtuozo.ui.api.UIComponent;
import virtuozo.ui.api.ActivationEvent.ActivationHandler;
import virtuozo.ui.api.DeactivationEvent.DeactivationHandler;
import virtuozo.ui.css.State;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;

public class Button extends Component<Button> implements HasText<Button>, HasIcon<Button>, HasState<Button>, HasClickHandlers<Button>, HasMouseHandlers<Button>, HasFocusHandlers<Button>, HasKeyHandlers<Button> {
  private Text textHolder = new Text();
  
  private ClickHandler toggleHandler = new ClickHandler() {
    @Override
    public void onClick(ClickEvent event) {
      if(Button.this.active()){
        Button.this.deactivate();
        return;
      }
      Button.this.activate();
    }
  };
  
  public Button() {
    super(Elements.button());
    this.addChild(textHolder).css().set("btn btn-default");
  }
  
  public Button toggleable() {
    this.on(this.toggleHandler);
    return this;
  }
  
  @Override
  protected Button addChild(UIComponent add) {
    return super.addChild(add);
  }
  
  @Override
  public Button onActivate(ActivationHandler handler) {
    return this.addHandler(ActivationEvent.TYPE, handler);
  }
  
  @Override
  public Button onDeactivate(DeactivationHandler handler) {
    return this.addHandler(DeactivationEvent.TYPE, handler);
  }
  
  @Override
  public Button onClick(ClickHandler handler) {
    return this.on(handler);
  }
  
  @Override
  public Button onDoubleClick(DoubleClickHandler handler) {
    return this.on(handler);
  }
  
  @Override
  public Button onFocus(FocusHandler handler) {
    return this.on(handler);
  }

  @Override
  public Button onBlur(BlurHandler handler) {
    return this.on(handler);
  }

  @Override
  public Button onMouseDown(MouseDownHandler handler) {
    return this.on(handler);
  }

  @Override
  public Button onMouseMove(MouseMoveHandler handler) {
    return this.on(handler);
  }

  @Override
  public Button onMouseOut(MouseOutHandler handler) {
    return this.on(handler);
  }

  @Override
  public Button onMouseOver(MouseOverHandler handler) {
    return this.on(handler);
  }

  @Override
  public Button onMouseUp(MouseUpHandler handler) {
    return this.on(handler);
  }

  @Override
  public Button onMouseWheel(MouseWheelHandler handler) {
    return this.on(handler);
  }

  @Override
  public Button onKeyPress(KeyPressHandler handler) {
    return this.on(handler);
  }

  @Override
  public Button onKeyDown(KeyDownHandler handler) {
    return this.on(handler);
  }

  @Override
  public Button onKeyUp(KeyUpHandler handler) {
    return this.on(handler);
  }
  
  @Override
  public Button deactivate() {
    this.fireEvent(new DeactivationEvent());
    this.css().remove(State.ACTIVE);
    return this;
  }
  
  @Override
  public Button activate() {
    this.fireEvent(new ActivationEvent());
    this.css(State.ACTIVE);
    return this;
  }
  
  @Override
  public boolean active() {
    return this.css().contains(State.ACTIVE);
  }
  
  @Override
  public Button disable() {
    this.attribute("disabled", "disabled").css().remove(State.ACTIVE);
    return this;
  }
  
  @Override
  public Button enable() {
    this.removeAttribute("disabled");
    return this;
  }
  
  @Override
  public boolean disabled() {
    return "disabled".equals(this.attribute("disabled"));
  }
  
  @Override
  public boolean match(Element element) {
    return this.id().equals(element.getId());
  }
  
  public Button block(){
    this.css("btn-block");
    return this;
  }
  
  @Override
  public Button icon(Icon icon) {
    icon.appendTo(this);
    return this;
  }
  
  @Override
  public String text() {
    return this.textHolder.text();
  }
  
  @Override
  public Button text(String text) {
    this.textHolder.text(text);
    return this;
  }
  
  public static class Size extends CssClass {
    private Size(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    public static final Size LARGE = new Size("btn-lg");

    public static final Size DEFAULT = new Size("btn-md");

    public static final Size SMALL = new Size("btn-sm");

    public static final Size X_SMALL = new Size("btn-xs");

    private static final StyleChooser STYLES = new StyleChooser(LARGE, DEFAULT, SMALL, X_SMALL);
  }
}