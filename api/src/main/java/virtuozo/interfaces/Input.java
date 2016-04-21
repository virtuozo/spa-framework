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

import virtuozo.infra.handlers.HasChangeHandlers;
import virtuozo.infra.handlers.HasClickHandlers;
import virtuozo.infra.handlers.HasFocusHandlers;
import virtuozo.infra.handlers.HasKeyHandlers;
import virtuozo.infra.handlers.HasMouseHandlers;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeHandler;
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

@SuppressWarnings("unchecked")
public abstract class Input<I extends Input<I>> extends Component<I> implements UIInput<I, String>, HasClickHandlers<I>, HasMouseHandlers<I>, HasKeyHandlers<I>, HasFocusHandlers<I>,
    HasChangeHandlers<I> {

  private InputElement element;
  
  protected Input(InputElement element) {
    super(element);
    this.element = element;
  }

  public InputElement element() {
    return this.element;
  }
  
  public I value(String value) {
    this.element().setValue(value);
    this.fireNativeEvent(Document.get().createChangeEvent());
    return (I) this;
  }
  
  public String value() {
    return this.element().getValue();
  }

  @Override
  public I onClick(ClickHandler handler) {
    return this.on(handler);
  }

  @Override
  public I onDoubleClick(DoubleClickHandler handler) {
    return this.on(handler);
  }

  @Override
  public I onFocus(FocusHandler handler) {
    return this.on(handler);
  }

  @Override
  public I onBlur(BlurHandler handler) {
    return this.on(handler);
  }

  public I onChange(ChangeHandler handler) {
    return this.on(handler);
  }

  @Override
  public I onKeyPress(KeyPressHandler handler) {
    return this.on(handler);
  }

  @Override
  public I onKeyDown(KeyDownHandler handler) {
    return this.on(handler);
  }

  @Override
  public I onKeyUp(KeyUpHandler handler) {
    return this.on(handler);
  }

  @Override
  public I onMouseDown(MouseDownHandler handler) {
    return this.on(handler);
  }

  @Override
  public I onMouseMove(MouseMoveHandler handler) {
    return this.on(handler);
  }

  @Override
  public I onMouseOut(MouseOutHandler handler) {
    return this.on(handler);
  }

  @Override
  public I onMouseOver(MouseOverHandler handler) {
    return this.on(handler);
  }

  @Override
  public I onMouseUp(MouseUpHandler handler) {
    return this.on(handler);
  }

  @Override
  public I onMouseWheel(MouseWheelHandler handler) {
    return this.on(handler);
  }

  public I disable() {
    this.element.setDisabled(true);;
    return (I) this;
  }
  
  @Override
  public boolean disabled() {
    return this.element.isDisabled();
  }

  public I enable() {
    this.element.setDisabled(false);
    return (I) this;
  }
  
  @Override
  public I tabIndex(int index) {
    this.element.setTabIndex(index);
    return (I) this;
  }
}