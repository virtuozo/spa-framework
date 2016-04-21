package virtuozo.interfaces;

import virtuozo.infra.handlers.HasClickHandlers;
import virtuozo.infra.handlers.HasMouseHandlers;
import virtuozo.interfaces.Anchor;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.Icon;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;

public class Iconic extends Component<Iconic> implements HasClickHandlers<Iconic>, HasMouseHandlers<Iconic> {

  private Anchor anchor = Anchor.create();

  public static Iconic create(){
    return new Iconic();
  }
  
  private Iconic() {
    this.incorporate(this.anchor);
  }

  public Iconic icon(Icon icon) {
    icon.attachTo(this);
    return this;
  }

  @Override
  public Iconic onMouseDown(MouseDownHandler handler) {
    this.anchor.onMouseDown(handler);
    return this;
  }

  @Override
  public Iconic onMouseMove(MouseMoveHandler handler) {
    this.anchor.onMouseMove(handler);
    return this;
  }

  @Override
  public Iconic onMouseOut(MouseOutHandler handler) {
    this.anchor.onMouseOut(handler);
    return this;
  }

  @Override
  public Iconic onMouseOver(MouseOverHandler handler) {
    this.anchor.onMouseOver(handler);
    return this;
  }

  @Override
  public Iconic onMouseUp(MouseUpHandler handler) {
    this.anchor.onMouseUp(handler);
    return this;
  }

  @Override
  public Iconic onMouseWheel(MouseWheelHandler handler) {
    this.anchor.onMouseWheel(handler);
    return this;
  }

  @Override
  public Iconic onClick(ClickHandler handler) {
    this.anchor.onClick(handler);
    return this;
  }

  @Override
  public Iconic onDoubleClick(DoubleClickHandler handler) {
    this.anchor.onDoubleClick(handler);
    return this;
  }
}