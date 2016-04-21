package virtuozo.interfaces;

import virtuozo.infra.Elements;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.InputLabel;
import virtuozo.interfaces.InputRadio;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeHandler;

public class RadioButton extends Component<RadioButton> implements UICheck<RadioButton, String>, HasText<RadioButton>{

  private InputLabel label = InputLabel.create();
  
  private InputRadio input;
  
  public static RadioButton create(String name){
    return new RadioButton(Elements.div(), name);
  }
  
  public static RadioButton inline(String name){
    return new RadioButton(name).css("radio-inline");
  }
  
  private RadioButton(Element element, String name) {
    super(element);
    this.input = InputRadio.create(name);
    this.css("radio");
    this.label.addFirstChild(this.input);
    this.addChild(this.label);
  }
  
  public RadioButton(String name) {
    this.incorporate(this.label);
    this.input = InputRadio.create(name);
    this.label.addFirstChild(this.input);
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
  
  @Override
  public RadioButton tabIndex(int index) {
    this.input.tabIndex(index);
    return this;
  }
}