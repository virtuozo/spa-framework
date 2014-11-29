package virtuozo.ui;

import virtuozo.ui.Component;
import virtuozo.ui.Elements;
import virtuozo.ui.InputCheckbox;
import virtuozo.ui.InputLabel;
import virtuozo.ui.Text;
import virtuozo.ui.api.HasText;
import virtuozo.ui.api.UISelection;

import com.google.gwt.event.dom.client.ChangeHandler;

public class RadioButton extends Component<RadioButton> implements UISelection<RadioButton, String>, HasText<RadioButton>{

  private InputLabel label = new InputLabel();
  
  private InputCheckbox input = new InputCheckbox();
  
  private Text text = new Text();
  
  private RadioButton() {
    super(Elements.div());
    this.css("radio");
    this.label.addChild(this.input).addChild(this.text);
    this.addChild(this.label);
  }
  
  @Override
  public RadioButton checked(Boolean checked) {
    this.input.checked(checked);
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
    this.css("disabled");
    return this;
  }

  @Override
  public boolean disabled() {
    return this.css().contains("disabled");
  }

  @Override
  public RadioButton enable() {
    this.css().remove("disabled");
    return this;
  }

  @Override
  public RadioButton text(String text) {
    this.text.text(text);
    return this;
  }

  @Override
  public String text() {
    return this.text.text();
  }

  @Override
  public RadioButton onChange(ChangeHandler handler) {
    this.input.onChange(handler);
    return this;
  }
}