package virtuozo.interfaces;

import virtuozo.infra.Elements;
import virtuozo.infra.StyleChooser;
import virtuozo.infra.handlers.HasChangeHandlers;
import virtuozo.infra.handlers.HasClickHandlers;
import virtuozo.infra.handlers.HasFocusHandlers;
import virtuozo.infra.handlers.HasKeyHandlers;
import virtuozo.infra.handlers.HasMouseHandlers;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.CssClass;
import virtuozo.interfaces.Input;

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

  private Input<?> control;

  private AddOn right = new AddOn().hide();

  public static InputGroup create(Input<?> control){
    return new InputGroup(control);
  }
  
  protected InputGroup(Input<?> control) {
    super(Elements.div());
    this.control= control;
    this.control.asComponent().css("form-control");
    super.css("input-group").addChild(this.left).addChild(this.control).addChild(this.right);
  }
  
  public Input<?> control() {
    return control;
  }
  
  @Override
  public InputGroup css(String... classes) {
    this.control.asComponent().css(classes);
    return this;
  }
  
  @Override
  public InputGroup css(UIClass... classes) {
    this.control.asComponent().css(classes);
    return this;
  }
  
  @Override
  public UIClasses css() {
    return this.control.asComponent().css();
  }
  
  @Override
  public String id() {
    if(this.control != null){
      return this.control.asComponent().id();
    }
    return super.id();
  }
  
  @Override
  public InputGroup id(String id) {
    if(this.control != null){
      this.control.asComponent().id(id);
      return this;
    }
    
    return super.id(id);
  }
  
  @Override
  protected InputGroup focus() {
    this.control.asComponent().focus();
    return this;
  }
  
  @Override
  protected InputGroup blur() {
    this.control.asComponent().blur();
    return this;
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
    this.control.value(value);
    return this;
  }

  @Override
  public String value() {
    return this.control.value();
  }

  @Override
  public InputGroup clear() {
    this.control.clear();
    return this;
  }

  @Override
  public InputGroup disable() {
    this.control.disable();
    return this;
  }
  
  @Override
  public boolean disabled() {
    return this.control.disabled();
  }

  @Override
  public InputGroup enable() {
    this.control.enable();
    return this;
  }

  @Override
  public InputGroup onFocus(FocusHandler handler) {
    this.control.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onBlur(BlurHandler handler) {
    this.control.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onKeyPress(KeyPressHandler handler) {
    this.control.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onKeyDown(KeyDownHandler handler) {
    this.control.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onKeyUp(KeyUpHandler handler) {
    this.control.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onMouseDown(MouseDownHandler handler) {
    this.control.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onMouseMove(MouseMoveHandler handler) {
    this.control.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onMouseOut(MouseOutHandler handler) {
    this.control.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onMouseOver(MouseOverHandler handler) {
    this.control.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onMouseUp(MouseUpHandler handler) {
    this.control.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onMouseWheel(MouseWheelHandler handler) {
    this.control.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onClick(ClickHandler handler) {
    this.control.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onDoubleClick(DoubleClickHandler handler) {
    this.control.asComponent().on(handler);
    return this;
  }

  @Override
  public InputGroup onChange(ChangeHandler handler) {
    this.control.asComponent().on(handler);
    return this;
  }

  public InputGroup maxLength(int maxLength) {
    this.control.asComponent().attribute("maxLength", String.valueOf(maxLength));
    return this;
  }
  
  public InputGroup placeholder(String placeholder){
    this.control.asComponent().attribute("placeholder", placeholder);
    return this;
  }
  
  @Override
  public InputGroup tabIndex(int index) {
    this.control.tabIndex(index);
    return this;
  }
  
  class AddOn extends Component<AddOn>{
    private AddOn() {
      super(Elements.span());
      this.css("input-group-addon");
    }
    
    public AddOn add(UIComponent widget){
      this.css("input-group-addon");
      
      if (widget instanceof Button || widget instanceof DropButton || widget instanceof SplitButton) {
        this.css().remove("input-group-addon").append("input-group-btn");
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