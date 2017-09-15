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

import virtuozo.infra.Elements;
import virtuozo.infra.handlers.HasClickHandlers;
import virtuozo.infra.handlers.HasFocusHandlers;
import virtuozo.infra.handlers.HasKeyHandlers;
import virtuozo.infra.handlers.HasMouseHandlers;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.BlurHandler;
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

public class Button extends Component<Button> implements HasText<Button>, HasClickHandlers<Button>, HasMouseHandlers<Button>, HasFocusHandlers<Button>, HasKeyHandlers<Button> {
  private Text textHolder = Text.create();
  
  public static Button create(){
    return new Button(Elements.button());
  }
  
  public static Button submit(){
    return new Button(Elements.submit());
  }
  
  public static Button reset(){
    return new Button(Elements.reset());
  }
  
  private Button(Element element) {
    super(element);
    this.addChild(textHolder);
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
  
  public Button disable() {
    this.attribute("disabled", "disabled");
    return this;
  }
  
  public Button enable() {
    this.removeAttribute("disabled");
    return this;
  }
  
  public boolean disabled() {
    return "disabled".equals(this.attribute("disabled"));
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
}