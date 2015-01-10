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

import virtuozo.ui.interfaces.HasClickHandlers;
import virtuozo.ui.interfaces.HasFocusHandlers;
import virtuozo.ui.interfaces.HasKeyHandlers;
import virtuozo.ui.interfaces.HasMouseHandlers;
import virtuozo.ui.interfaces.HasTouchHandlers;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.DomEvent.Type;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.dom.client.TouchCancelEvent;
import com.google.gwt.event.dom.client.TouchCancelHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;

public class BrowserEventInterceptor implements HasClickHandlers<BrowserEventInterceptor>, HasFocusHandlers<BrowserEventInterceptor>, HasKeyHandlers<BrowserEventInterceptor>,
    HasMouseHandlers<BrowserEventInterceptor>, HasTouchHandlers<BrowserEventInterceptor>, HasHandlers {

  private static final BrowserEventInterceptor instance = new BrowserEventInterceptor();

  private HandlerManager manager = new HandlerManager(this);
  
  private NativePreviewHandler nativeHandler = new NativePreviewHandler() {
    
    @Override
    public void onPreviewNativeEvent(NativePreviewEvent event) {
      try {
        DomEvent.fireNativeEvent(event.getNativeEvent(), BrowserEventInterceptor.this);
      } finally {
        event.consume();
      }
    }
  };

  public static BrowserEventInterceptor get() {
    return instance;
  }

  private BrowserEventInterceptor() {
    Event.addNativePreviewHandler(this.nativeHandler);
  }

  @Override
  public void fireEvent(GwtEvent<?> event) {
    this.manager.fireEvent(event);
  }

  @Override
  public BrowserEventInterceptor onClick(ClickHandler handler) {
    return this.put(ClickEvent.getType(), handler);
  }

  @Override
  public BrowserEventInterceptor onDoubleClick(DoubleClickHandler handler) {
    return this.put(DoubleClickEvent.getType(), handler);
  }

  @Override
  public BrowserEventInterceptor onBlur(BlurHandler handler) {
    return this.put(BlurEvent.getType(), handler);
  }

  @Override
  public BrowserEventInterceptor onFocus(FocusHandler handler) {
    return this.put(FocusEvent.getType(), handler);
  }

  @Override
  public BrowserEventInterceptor onKeyPress(KeyPressHandler handler) {
    return this.put(KeyPressEvent.getType(), handler);
  }

  @Override
  public BrowserEventInterceptor onKeyDown(KeyDownHandler handler) {
    return this.put(KeyDownEvent.getType(), handler);
  }

  @Override
  public BrowserEventInterceptor onKeyUp(KeyUpHandler handler) {
    return this.put(KeyUpEvent.getType(), handler);
  }

  @Override
  public BrowserEventInterceptor onMouseDown(MouseDownHandler handler) {
    return this.put(MouseDownEvent.getType(), handler);
  }

  @Override
  public BrowserEventInterceptor onMouseMove(MouseMoveHandler handler) {
    return this.put(MouseMoveEvent.getType(), handler);
  }

  @Override
  public BrowserEventInterceptor onMouseOut(MouseOutHandler handler) {
    return this.put(MouseOutEvent.getType(), handler);
  }

  @Override
  public BrowserEventInterceptor onMouseOver(MouseOverHandler handler) {
    return this.put(MouseOverEvent.getType(), handler);
  }

  @Override
  public BrowserEventInterceptor onMouseUp(MouseUpHandler handler) {
    return this.put(MouseUpEvent.getType(), handler);
  }

  @Override
  public BrowserEventInterceptor onMouseWheel(MouseWheelHandler handler) {
    return this.put(MouseWheelEvent.getType(), handler);
  }

  @Override
  public BrowserEventInterceptor onTouchCancel(TouchCancelHandler handler) {
    return this.put(TouchCancelEvent.getType(), handler);
  }

  @Override
  public BrowserEventInterceptor onTouchEnd(TouchEndHandler handler) {
    return this.put(TouchEndEvent.getType(), handler);
  }

  @Override
  public BrowserEventInterceptor onTouchMove(TouchMoveHandler handler) {
    return this.put(TouchMoveEvent.getType(), handler);
  }

  @Override
  public BrowserEventInterceptor onTouchStart(TouchStartHandler handler) {
    return this.put(TouchStartEvent.getType(), handler);
  }

  public <H extends EventHandler> BrowserEventInterceptor remove(Type<H> type, H handler) {
    this.manager.removeHandler(type, handler);
    return this;
  }

  private <H extends EventHandler> BrowserEventInterceptor put(Type<H> type, H handler) {
    this.manager.addHandler(type, handler);
    return this;
  }
}