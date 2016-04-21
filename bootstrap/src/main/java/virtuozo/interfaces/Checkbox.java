package virtuozo.interfaces;

import virtuozo.infra.Elements;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.InputCheckbox;
import virtuozo.interfaces.InputLabel;
import virtuozo.interfaces.css.State;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeHandler;

public class Checkbox extends Component<Checkbox> implements UICheck<Checkbox, String>, HasText<Checkbox>{

  private InputLabel label = InputLabel.create();
  
  private InputCheckbox input = InputCheckbox.create();
  
  public static Checkbox create(){
    return new Checkbox(Elements.div());
  }
  
  static Checkbox inline(){
    return new Checkbox();
  }
  
  private Checkbox() {
    this.incorporate(this.label);
    this.label.css("checkbox-inline").addFirstChild(input);
  }

  private Checkbox(Element element) {
    super(element);
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
  
  @Override
  public Checkbox tabIndex(int index) {
    this.input.tabIndex(index);
    return this;
  }
}