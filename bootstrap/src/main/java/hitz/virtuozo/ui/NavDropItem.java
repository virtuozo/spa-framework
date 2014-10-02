package hitz.virtuozo.ui;

import hitz.virtuozo.infra.api.HasClickHandlers;
import hitz.virtuozo.infra.api.HasMouseHandlers;
import hitz.virtuozo.infra.api.HasText;
import hitz.virtuozo.ui.Menu.MenuItem;
import hitz.virtuozo.ui.api.ActivationEvent.ActivationHandler;
import hitz.virtuozo.ui.api.DeactivationEvent.DeactivationHandler;
import hitz.virtuozo.ui.api.HasActivation;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;

class NavDropItem<N extends NavDropItem<N>> extends Widget<N> implements HasText<N>, HasClickHandlers<N>, HasMouseHandlers<N>, HasActivation<N> {
  private MenuItem item;

  NavDropItem(MenuItem item) {
    super(item);
    this.item = item;
  }

  @Override
  public N onMouseDown(MouseDownHandler handler) {
    return this.on(handler);
  }

  @Override
  public N onMouseMove(MouseMoveHandler handler) {
    return this.on(handler);
  }

  @Override
  public N onMouseOut(MouseOutHandler handler) {
    return this.on(handler);
  }

  @Override
  public N onMouseOver(MouseOverHandler handler) {
    return this.on(handler);
  }

  @Override
  public N onMouseUp(MouseUpHandler handler) {
    return this.on(handler);
  }

  @Override
  public N onMouseWheel(MouseWheelHandler handler) {
    return this.on(handler);
  }

  @Override
  public N onClick(ClickHandler handler) {
    return this.on(handler);
  }

  @Override
  public N onDoubleClick(DoubleClickHandler handler) {
    return this.on(handler);
  }

  @Override
  public N text(String text) {
    this.item.text(text);
    return (N) this;
  }

  @Override
  public String text() {
    return this.item.text();
  }

  @Override
  public N activate() {
    this.item.activate();
    return (N) this;
  }

  @Override
  public N onActivate(ActivationHandler handler) {
    this.item.onActivate(handler);
    return (N) this;
  }

  @Override
  public N deactivate() {
    this.item.deactivate();
    return (N) this;
  }

  @Override
  public N onDeactivate(DeactivationHandler handler) {
    this.item.onDeactivate(handler);
    return (N) this;
  }

  @Override
  public boolean active() {
    return this.item.active();
  }

  @Override
  public boolean match(Element element) {
    return this.id().equals(element.getId());
  }
}