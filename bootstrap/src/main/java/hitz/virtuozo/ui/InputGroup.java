package hitz.virtuozo.ui;

import hitz.virtuozo.infra.api.HasChangeHandlers;
import hitz.virtuozo.infra.api.HasClickHandlers;
import hitz.virtuozo.infra.api.HasFocusHandlers;
import hitz.virtuozo.infra.api.HasKeyHandlers;
import hitz.virtuozo.infra.api.HasMouseHandlers;
import hitz.virtuozo.ui.CssClass;
import hitz.virtuozo.ui.Elements;
import hitz.virtuozo.ui.StyleChooser;
import hitz.virtuozo.ui.Tag;
import hitz.virtuozo.ui.Widget;
import hitz.virtuozo.ui.api.UIClass;
import hitz.virtuozo.ui.api.UIClasses;
import hitz.virtuozo.ui.api.UIInput;
import hitz.virtuozo.ui.api.UIWidget;

import com.google.gwt.dom.client.SpanElement;
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

public class InputGroup extends Widget<InputGroup> implements UIInput<InputGroup, String>, HasClickHandlers<InputGroup>, HasMouseHandlers<InputGroup>, HasKeyHandlers<InputGroup>,
    HasFocusHandlers<InputGroup>, HasChangeHandlers<InputGroup> {

  private AddOn left = new AddOn();

  private UIInput<?, String> input;

  private AddOn right = new AddOn();

  public InputGroup(UIInput<?, String> input) {
    super(Elements.div());
    this.input = input;
    this.input.asWidget().css("form-control");
    super.css("input-group").addChild(this.left).addChild(this.input).addChild(this.right);
  }
  
  @Override
  public InputGroup css(String... classes) {
    this.input.asWidget().css(classes);
    return this;
  }
  
  @Override
  public InputGroup css(UIClass... classes) {
    this.input.asWidget().css(classes);
    return this;
  }
  
  @Override
  public UIClasses css() {
    return this.input.asWidget().css();
  }
  
  @Override
  public String id() {
    return this.input.asWidget().id();
  }
  
  @Override
  public InputGroup id(String id) {
    this.input.asWidget().id(id);
    return this;
  }

  public InputGroup prepend(UIWidget widget) {
    this.left.detachChildren().add(widget);
    return this;
  }

  public InputGroup append(UIWidget widget) {
    this.right.detachChildren().addChild(widget);
    return this;
  }

  @Override
  public InputGroup value(String value) {
    this.input.value(value);
    return this;
  }

  @Override
  public String value() {
    return this.input.value();
  }

  @Override
  public InputGroup clear() {
    this.input.clear();
    return this;
  }

  @Override
  public InputGroup disable() {
    this.input.disable();
    return this;
  }
  
  @Override
  public boolean disabled() {
    return this.input.disabled();
  }

  @Override
  public InputGroup enable() {
    this.input.enable();
    return this;
  }

  @Override
  public InputGroup onFocus(FocusHandler handler) {
    this.input.asWidget().on(handler);
    return this;
  }

  @Override
  public InputGroup onBlur(BlurHandler handler) {
    this.input.asWidget().on(handler);
    return this;
  }

  @Override
  public InputGroup onKeyPress(KeyPressHandler handler) {
    this.input.asWidget().on(handler);
    return this;
  }

  @Override
  public InputGroup onKeyDown(KeyDownHandler handler) {
    this.input.asWidget().on(handler);
    return this;
  }

  @Override
  public InputGroup onKeyUp(KeyUpHandler handler) {
    this.input.asWidget().on(handler);
    return this;
  }

  @Override
  public InputGroup onMouseDown(MouseDownHandler handler) {
    this.input.asWidget().on(handler);
    return this;
  }

  @Override
  public InputGroup onMouseMove(MouseMoveHandler handler) {
    this.input.asWidget().on(handler);
    return this;
  }

  @Override
  public InputGroup onMouseOut(MouseOutHandler handler) {
    this.input.asWidget().on(handler);
    return this;
  }

  @Override
  public InputGroup onMouseOver(MouseOverHandler handler) {
    this.input.asWidget().on(handler);
    return this;
  }

  @Override
  public InputGroup onMouseUp(MouseUpHandler handler) {
    this.input.asWidget().on(handler);
    return this;
  }

  @Override
  public InputGroup onMouseWheel(MouseWheelHandler handler) {
    this.input.asWidget().on(handler);
    return this;
  }

  @Override
  public InputGroup onClick(ClickHandler handler) {
    this.input.asWidget().on(handler);
    return this;
  }

  @Override
  public InputGroup onDoubleClick(DoubleClickHandler handler) {
    this.input.asWidget().on(handler);
    return this;
  }

  @Override
  public InputGroup onChange(ChangeHandler handler) {
    this.input.asWidget().on(handler);
    return this;
  }

  public InputGroup maxLength(int maxLength) {
    this.input.asWidget().attribute("maxLength", String.valueOf(maxLength));
    return this;
  }
  
  public InputGroup placeholder(String placeholder){
    this.input.asWidget().attribute("placeholder", placeholder);
    return this;
  }
  
  class AddOn extends Widget<AddOn>{
    private Tag<SpanElement> buttons = Tag.asSpan().css("input-group-btn");
    
    public AddOn() {
      super(Elements.span());
      this.css("input-group-addon").addChild(this.buttons);
    }
    
    public AddOn add(UIWidget widget){
      if (widget instanceof Button || widget instanceof DropButton || widget instanceof SplitButton) {
        this.buttons.add(widget);
        return this;
      }
      
      return this.addChild(widget);
    }
  }

  public static class Size extends CssClass {
    private Size(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    public static final Size LARGE = new Size("input-group-lg");
    public static final Size MEDIUM = new Size("input-group-md");
    public static final Size SMALL = new Size("input-group-sm");
    static final StyleChooser STYLES = new StyleChooser(LARGE, MEDIUM, SMALL);
  }
}