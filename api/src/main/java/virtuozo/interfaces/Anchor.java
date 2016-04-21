package virtuozo.interfaces;

import virtuozo.infra.Elements;
import virtuozo.infra.handlers.HasClickHandlers;
import virtuozo.infra.handlers.HasFocusHandlers;
import virtuozo.infra.handlers.HasKeyHandlers;
import virtuozo.infra.handlers.HasMouseHandlers;
import virtuozo.infra.handlers.HasTouchHandlers;

import com.google.gwt.dom.client.AnchorElement;
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

public class Anchor extends Composite<Anchor> implements HasText<Anchor>, HasClickHandlers<Anchor>, HasMouseHandlers<Anchor>, HasKeyHandlers<Anchor>, HasFocusHandlers<Anchor>,
  HasTouchHandlers<Anchor> {
  
  private Text text = Text.create();
  
  public static Anchor create(){
    return new Anchor();
  }
  
  private Anchor() {
    super(Elements.a());
    this.href("javascript:void(0)").add(this.text);
  }
  
  @Override
  public Anchor onTouchCancel(TouchCancelHandler handler) {
    return this.on(handler);
  }

  @Override
  public Anchor onTouchEnd(TouchEndHandler handler) {
    return this.on(handler);
  }

  @Override
  public Anchor onTouchMove(TouchMoveHandler handler) {
    return this.on(handler);
  }

  @Override
  public Anchor onTouchStart(TouchStartHandler handler) {
    return this.on(handler);
  }

  @Override
  public Anchor onFocus(FocusHandler handler) {
    return this.on(handler);
  }

  @Override
  public Anchor onBlur(BlurHandler handler) {
    return this.on(handler);
  }

  @Override
  public Anchor onKeyPress(KeyPressHandler handler) {
    return this.on(handler);
  }

  @Override
  public Anchor onKeyDown(KeyDownHandler handler) {
    return this.on(handler);
  }

  @Override
  public Anchor onKeyUp(KeyUpHandler handler) {
    return this.on(handler);
  }

  @Override
  public Anchor onMouseDown(MouseDownHandler handler) {
    return this.on(handler);
  }

  @Override
  public Anchor onMouseMove(MouseMoveHandler handler) {
    return this.on(handler);
  }

  @Override
  public Anchor onMouseOut(MouseOutHandler handler) {
    return this.on(handler);
  }

  @Override
  public Anchor onMouseOver(MouseOverHandler handler) {
    return this.on(handler);
  }

  @Override
  public Anchor onMouseUp(MouseUpHandler handler) {
    return this.on(handler);
  }

  @Override
  public Anchor onMouseWheel(MouseWheelHandler handler) {
    return this.on(handler);
  }

  @Override
  public Anchor onClick(ClickHandler handler) {
    return this.on(handler);
  }

  @Override
  public Anchor onDoubleClick(DoubleClickHandler handler) {
    return this.on(handler);
  }

  public Anchor href(String href){
    this.element().setHref(href);
    return this;
  }
  
  public Anchor target(Target target){
    return this.target(target.toString());
  }
  
  public Anchor target(String name){
    this.element().setTarget(name);
    return this;
  }
  
  @Override
  public String text() {
    return this.text.text();
  }
  
  @Override
  public Anchor text(String text) {
    this.text.text(text);
    return this;
  }
  
  @Override
  protected AnchorElement element() {
    return super.element();
  }
  
  public static enum Target{
    BLANK, SELF, PARENT, TOP;
    
    public String toString() {
      return "_" + this.name().toLowerCase(); 
    };
  }
}