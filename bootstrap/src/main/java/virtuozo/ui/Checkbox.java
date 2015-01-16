package virtuozo.ui;

import virtuozo.ui.css.State;
import virtuozo.ui.interfaces.HasText;
import virtuozo.ui.interfaces.UICheck;

import com.google.gwt.event.dom.client.ChangeHandler;

public class Checkbox extends Component<Checkbox> implements UICheck<Checkbox, String>, HasText<Checkbox>{

  private InputLabel label = InputLabel.create();
  
  private InputCheckbox input = InputCheckbox.create();
  
  public static Checkbox create(){
    return new Checkbox();
  }
  
  private Checkbox() {
    super(Elements.div());
    this.css("checkbox");
    this.label.addFirstChild(this.input);
    this.addChild(this.label);
  }
  
  @Override
  public Checkbox check() {
    this.input.check();
    return this;
  }
  
  @Override
  public Checkbox uncheck() {
    this.input.uncheck();
    return this;
  }
  
  @Override
  public Boolean checked() {
    return this.input.checked();
  }

  @Override
  public Checkbox value(String value) {
    this.input.value(value);
    return this;
  }

  @Override
  public String value() {
    return this.input.value();
  }

  @Override
  public Checkbox clear() {
    this.input.clear();
    return this;
  }

  @Override
  public Checkbox disable() {
    this.input.disable();
    this.css(State.DISABLED);
    return this;
  }

  @Override
  public boolean disabled() {
    return this.css().contains(State.DISABLED);
  }

  @Override
  public Checkbox enable() {
    this.input.enable();
    this.css().remove(State.DISABLED);
    return this;
  }

  @Override
  public Checkbox text(String text) {
    this.label.text(text);
    return this;
  }

  @Override
  public String text() {
    return this.label.text();
  }
  
  InputLabel label(){
    return this.label;
  }

  @Override
  public Checkbox onChange(ChangeHandler handler) {
    this.input.onChange(handler);
    return this;
  }
}