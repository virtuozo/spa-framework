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
import virtuozo.infra.handlers.HasTouchHandlers;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.FieldSetElement;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.LegendElement;
import com.google.gwt.dom.client.OListElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.UListElement;
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
import com.google.gwt.event.dom.client.TouchCancelHandler;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartHandler;

@SuppressWarnings("unchecked")
public final class Tag<E extends Element> extends Composite<Tag<E>> implements HasText<Tag<E>>, HasHtml<Tag<E>>, HasClickHandlers<Tag<E>>, HasMouseHandlers<Tag<E>>, HasKeyHandlers<Tag<E>>, HasFocusHandlers<Tag<E>>,
    HasTouchHandlers<Tag<E>> {

  private Tag(E element) {
    super(element);
  }
  
  @Override
  public Tag<E> text(String text) {
    this.element().setInnerText(text);
    return this;
  }

  @Override
  public String text() {
    return this.element().getInnerText();
  }
  
  @Override
  public String html() {
    return this.element().getInnerHTML();
  }
  
  @Override
  public Tag<E> html(String html) {
    this.element().setInnerHTML(html);
    return this;
  }

  @Override
  public Tag<E> onClick(ClickHandler handler) {
    return this.on(handler);
  }

  @Override
  public Tag<E> onDoubleClick(DoubleClickHandler handler) {
    return this.on(handler);
  }

  @Override
  public Tag<E> onBlur(BlurHandler handler) {
    return this.on(handler);
  }

  @Override
  public Tag<E> onFocus(FocusHandler handler) {
    return this.on(handler);
  }

  @Override
  public Tag<E> onKeyDown(KeyDownHandler handler) {
    return this.on(handler);
  }

  @Override
  public Tag<E> onKeyPress(KeyPressHandler handler) {
    return this.on(handler);
  }

  @Override
  public Tag<E> onKeyUp(KeyUpHandler handler) {
    return this.on(handler);
  }

  @Override
  public Tag<E> onMouseDown(MouseDownHandler handler) {
    return this.on(handler);
  }

  @Override
  public Tag<E> onMouseMove(MouseMoveHandler handler) {
    return this.on(handler);
  }

  @Override
  public Tag<E> onMouseOut(MouseOutHandler handler) {
    return this.on(handler);
  }

  @Override
  public Tag<E> onMouseOver(MouseOverHandler handler) {
    return this.on(handler);
  }

  @Override
  public Tag<E> onMouseUp(MouseUpHandler handler) {
    return this.on(handler);
  }

  @Override
  public Tag<E> onMouseWheel(MouseWheelHandler handler) {
    return this.on(handler);
  }

  @Override
  public Tag<E> onTouchCancel(TouchCancelHandler handler) {
    return this.on(handler);
  }

  @Override
  public Tag<E> onTouchEnd(TouchEndHandler handler) {
    return this.on(handler);
  }

  @Override
  public Tag<E> onTouchMove(TouchMoveHandler handler) {
    return this.on(handler);
  }

  @Override
  public Tag<E> onTouchStart(TouchStartHandler handler) {
    return this.on(handler);
  }

  public static <E extends Element> Tag<E> as(E element) {
    return new Tag<E>(element);
  }

  @Override
  public E element() {
    return super.element();
  }

  public static Tag<DivElement> asDiv() {
    return Tag.as(Elements.div());
  }

  public static Tag<FieldSetElement> asFieldSet() {
    return Tag.as(Elements.fieldset());
  }

  public static Tag<LabelElement> asLabel() {
    return Tag.as(Elements.label());
  }

  public static Tag<LegendElement> asLegend() {
    return Tag.as(Elements.legend());
  }

  public static Tag<LIElement> asListItem() {
    return Tag.as(Elements.li());
  }

  public static Tag<OListElement> asOrderedList() {
    return Tag.as(Elements.ol());
  }

  public static Tag<UListElement> asUnorderedList() {
    return Tag.as(Elements.ul());
  }

  public static Tag<SpanElement> asSpan() {
    return Tag.as(Elements.span());
  }
}