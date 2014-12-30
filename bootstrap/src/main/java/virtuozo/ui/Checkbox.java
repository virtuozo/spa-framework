package virtuozo.ui;

import virtuozo.ui.api.HasText;
import virtuozo.ui.api.UISelection;
import virtuozo.ui.css.State;

import com.google.gwt.event.dom.client.ChangeHandler;

public class Checkbox extends Component<Checkbox> implements UISelection<Checkbox, String>, HasText<Checkbox>{

  private InputLabel label = new InputLabel();
  
  private InputCheckbox input = new InputCheckbox();
  
  private Text text = new Text();
  
  private Checkbox() {
    super(Elements.div());
    this.css("checkbox");
    this.label.addChild(this.input).addChild(this.text);
    this.addChild(this.label);
  }
  
  @Override
  public Checkbox checked(Boolean checked) {
    this.input.checked(checked);
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
    this.css(State.DISABLED);
    return this;
  }

  @Override
  public boolean disabled() {
    return this.css().contains(State.DISABLED);
  }

  @Override
  public Checkbox enable() {
    this.css().remove(State.DISABLED);
    return this;
  }

  @Override
  public Checkbox text(String text) {
    this.text.text(text);
    return this;
  }

  @Override
  public String text() {
    return this.text.text();
  }

  @Override
  public Checkbox onChange(ChangeHandler handler) {
    this.input.onChange(handler);
    return this;
  }
}