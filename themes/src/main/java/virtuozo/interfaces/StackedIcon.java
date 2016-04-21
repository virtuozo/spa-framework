package virtuozo.interfaces;

import virtuozo.infra.Elements;
import virtuozo.infra.handlers.HasClickHandlers;
import virtuozo.infra.handlers.HasMouseHandlers;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.Tag;
import virtuozo.interfaces.UIClass;

import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;

public class StackedIcon extends Component<StackedIcon> implements HasClickHandlers<StackedIcon>, HasMouseHandlers<StackedIcon> {
  private Tag<SpanElement> regular = Tag.asSpan();

  private Tag<SpanElement> larger = Tag.asSpan();

  public static StackedIcon create() {
    return new StackedIcon();
  }

  private StackedIcon() {
    super(Elements.span());
    this.css("fa-stack", "fa-lg").addChild(this.larger).addChild(this.regular);
  }

  public StackedIcon regular(FontAwesome regular, UIClass... styles) {
    this.regular.css().set("fa").append(regular.key()).append("fa-stack-1x").append(styles);
    return this;
  }

  public StackedIcon larger(FontAwesome larger, UIClass... styles) {
    this.larger.css().set("fa").append(larger.key()).append("fa-stack-2x").append(styles);
    return this;
  }

  @Override
  public StackedIcon onClick(ClickHandler handler) {
    return this.on(handler);
  }

  @Override
  public StackedIcon onDoubleClick(DoubleClickHandler handler) {
    return this.on(handler);
  }

  @Override
  public StackedIcon onMouseDown(MouseDownHandler handler) {
    return this.on(handler);
  }

  @Override
  public StackedIcon onMouseMove(MouseMoveHandler handler) {
    return this.on(handler);
  }

  @Override
  public StackedIcon onMouseOut(MouseOutHandler handler) {
    return this.on(handler);
  }

  @Override
  public StackedIcon onMouseOver(MouseOverHandler handler) {
    return this.on(handler);
  }

  @Override
  public StackedIcon onMouseUp(MouseUpHandler handler) {
    return this.on(handler);
  }

  @Override
  public StackedIcon onMouseWheel(MouseWheelHandler handler) {
    return this.on(handler);
  }
}