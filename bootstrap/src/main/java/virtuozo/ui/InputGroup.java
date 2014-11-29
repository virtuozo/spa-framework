package virtuozo.ui;

import virtuozo.ui.Component;
import virtuozo.ui.CssClass;
import virtuozo.ui.Elements;
import virtuozo.ui.StyleChooser;
import virtuozo.ui.api.HasChangeHandlers;
import virtuozo.ui.api.HasClickHandlers;
import virtuozo.ui.api.HasFocusHandlers;
import virtuozo.ui.api.HasKeyHandlers;
import virtuozo.ui.api.HasMouseHandlers;
import virtuozo.ui.api.UIClass;
import virtuozo.ui.api.UIClasses;
import virtuozo.ui.api.UIComponent;
import virtuozo.ui.api.UIInput;

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

public class InputGroup extends Component<InputGroup> implements UIInput<InputGroup, String>, HasClickHandlers<InputGroup>, HasMouseHandlers<InputGroup>, HasKeyHandlers<InputGroup>,
    HasFocusHandlers<InputGroup>, HasChangeHandlers<InputGroup> {

  private AddOn left = new AddOn().hide();

  private UIInput<?, String> input;

  private AddOn right = new AddOn().hide();

  public InputGroup(UIInput<?, String> input) {
    super(Elements.div());
    this.input = input;
    this.input.asComponent().css("form-control");
    super.css("input-group").addChild(this.left).addChild(this.input).addChild(this.right);
  }
  
  @Override
  public InputGroup css(String... classes) {
    this.input.asComponent().css(classes);
    return this;
  }
  
  @Override
  public InputGroup css(UIClass... classes) {
    this.input.asComponent().css(classes);
    return this;
  }
  
  @Override
  public UIClasses css() {
    return this.input.asComponent().css();
  }
  
  @Override
  public String id() {
    if(this.input != null){
      return this.input.asComponent().id();
    }
    return super.id();
  }
  
  @Override
  public InputGroup id(String id) {
    if(this.input != null){
      this.input.asComponent().id(id);
      return this;
    }
    
    return super.id(id);
  }

  public InputGroup prepend(UIComponent widget) {
    this.left.show().add(widget);
    return this;
  }

  public InputGroup append(UIComponent widget) {
    this.right.show().add(widget);
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
    this.input.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onBlur(BlurHandler handler) {
    this.input.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onKeyPress(KeyPressHandler handler) {
    this.input.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onKeyDown(KeyDownHandler handler) {
    this.input.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onKeyUp(KeyUpHandler handler) {
    this.input.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onMouseDown(MouseDownHandler handler) {
    this.input.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onMouseMove(MouseMoveHandler handler) {
    this.input.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onMouseOut(MouseOutHandler handler) {
    this.input.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onMouseOver(MouseOverHandler handler) {
    this.input.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onMouseUp(MouseUpHandler handler) {
    this.input.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onMouseWheel(MouseWheelHandler handler) {
    this.input.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onClick(ClickHandler handler) {
    this.input.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onDoubleClick(DoubleClickHandler handler) {
    this.input.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onChange(ChangeHandler handler) {
    this.input.asComponent().on(handler);
    return this;
  }

  public InputGroup maxLength(int maxLength) {
    this.input.asComponent().attribute("maxLength", String.valueOf(maxLength));
    return this;
  }
  
  public InputGroup placeholder(String placeholder){
    this.input.asComponent().attribute("placeholder", placeholder);
    return this;
  }
  
  class AddOn extends Component<AddOn>{
    public AddOn() {
      super(Elements.span());
      this.css("input-group-addon", "input-group-btn");
    }
    
    public AddOn add(UIComponent widget){
      this.detachChildren().css("input-group-addon");
      
      if (widget instanceof Button || widget instanceof DropButton || widget instanceof SplitButton) {
        this.css().remove("input-group-addon");
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