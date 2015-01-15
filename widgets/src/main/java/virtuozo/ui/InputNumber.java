package virtuozo.ui;

import virtuozo.ui.interfaces.UIInput;

public final class InputNumber extends Component<InputNumber> implements UIInput<InputNumber, Integer> {
  private InputText control = InputText.create();
  
  public static InputNumber create(){
    return new InputNumber();
  }
  
  private InputNumber() {
    this.incorporate(this.control);
    NumberInputPrevent.create().attachTo(this.control);
  }

  @Override
  public InputNumber value(Integer value) {
    this.control.value(value.toString());
    return this;
  }

  @Override
  public Integer value() {
    try{
      return Integer.valueOf(this.control.value());
    } catch(Exception e){
      this.value(0);
      return 0;
    }
  }

  @Override
  public InputNumber enable() {
    this.control.enable();
    return this;
  }

  @Override
  public InputNumber disable() {
    this.control.disable();
    return this;
  }

  @Override
  public boolean disabled() {
    return this.control.disabled();
  }

  @Override
  public InputNumber clear() {
    this.control.clear();
    return this;
  }
}