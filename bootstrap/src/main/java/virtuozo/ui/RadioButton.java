package virtuozo.ui;

import virtuozo.ui.interfaces.HasText;
import virtuozo.ui.interfaces.UISelection;

import com.google.gwt.event.dom.client.ChangeHandler;

public class RadioButton extends Component<RadioButton> implements UISelection<RadioButton, String>, HasText<RadioButton>{

  private InputLabel label = InputLabel.create();
  
  private InputRadio input;
  
  public static RadioButton create(String name){
    return new RadioButton(name);
  }
  
  private RadioButton(String name) {
    super(Elements.div());
    this.input = InputRadio.create(name);
    this.css("radio");
    this.label.addFirstChild(this.input);
    this.addChild(this.label);
  }
  
  @Override
  public RadioButton check() {
    this.input.check();
    return this;
  }
  
  @Override
  public RadioButton uncheck() {
    this.input.uncheck();
    return this;
  }
  
  @Override
  public Boolean checked() {
    return this.input.checked();
  }

  @Override
  public RadioButton value(String value) {
    this.input.value(value);
    return this;
  }

  @Override
  public String value() {
    return this.input.value();
  }

  @Override
  public RadioButton clear() {
    this.input.clear();
    return this;
  }

  @Override
  public RadioButton disable() {
    this.input.disable();
    this.css("disabled");
    return this;
  }

  @Override
  public boolean disabled() {
    return this.css().contains("disabled");
  }

  @Override
  public RadioButton enable() {
    this.input.enable();
    this.css().remove("disabled");
    return this;
  }

  @Override
  public RadioButton text(String text) {
    this.label.text(text);
    return this;
  }

  @Override
  public String text() {
    return this.label.text();
  }
  
  public InputLabel label() {
    return label;
  }

  @Override
  public RadioButton onChange(ChangeHandler handler) {
    this.input.onChange(handler);
    return this;
  }
}