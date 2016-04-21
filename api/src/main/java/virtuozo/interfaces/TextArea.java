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
import virtuozo.infra.handlers.HasChangeHandlers;
import virtuozo.infra.handlers.HasFocusHandlers;
import virtuozo.infra.handlers.HasKeyHandlers;
import virtuozo.infra.handlers.HasMouseHandlers;

import com.google.gwt.dom.client.TextAreaElement;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeHandler;
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

@SuppressWarnings("unchecked")
public final class TextArea extends Component<TextArea> implements UIInput<TextArea, String>, HasFocusHandlers<TextArea>, HasMouseHandlers<TextArea>, HasKeyHandlers<TextArea>,
    HasChangeHandlers<TextArea> {

  public static TextArea create(){
    return new TextArea();
  }
  
  private TextArea() {
    super(Elements.textarea());
  }

  public TextArea onChange(ChangeHandler handler) {
    return this.on(handler);
  }

  @Override
  public TextArea onKeyPress(KeyPressHandler handler) {
    return this.on(handler);
  }

  @Override
  public TextArea onKeyDown(KeyDownHandler handler) {
    return this.on(handler);
  }

  @Override
  public TextArea onKeyUp(KeyUpHandler handler) {
    return this.on(handler);
  }

  @Override
  public TextArea onMouseDown(MouseDownHandler handler) {
    return this.on(handler);
  }

  @Override
  public TextArea onMouseMove(MouseMoveHandler handler) {
    return this.on(handler);
  }

  @Override
  public TextArea onMouseOut(MouseOutHandler handler) {
    return this.on(handler);
  }

  @Override
  public TextArea onMouseOver(MouseOverHandler handler) {
    return this.on(handler);
  }

  @Override
  public TextArea onMouseUp(MouseUpHandler handler) {
    return this.on(handler);
  }

  @Override
  public TextArea onMouseWheel(MouseWheelHandler handler) {
    return this.on(handler);
  }

  @Override
  public TextArea onFocus(FocusHandler handler) {
    return this.on(handler);
  }

  @Override
  public TextArea onBlur(BlurHandler handler) {
    return this.on(handler);
  }

  public TextArea cols(int cols) {
    this.element().setCols(cols);
    return this;
  }

  public TextArea rows(int rows) {
    this.element().setRows(rows);
    return this;
  }

  @Override
  public TextArea clear() {
    return this.value("");
  }

  @Override
  public TextArea value(String value) {
    this.element().setValue(value);
    return this;
  }

  @Override
  public String value() {
    return this.element().getValue();
  }

  protected TextAreaElement element() {
    return super.element();
  }

  @Override
  public TextArea disable() {
    this.element().setDisabled(true);
    return this;
  }
  
  @Override
  public boolean disabled() {
    return this.element().isDisabled();
  }

  @Override
  public TextArea enable() {
    this.element().setDisabled(false);
    return this;
  }
  
   @Override
  public TextArea tabIndex(int index) {
    this.element().setTabIndex(index);
    return this;
  }
}