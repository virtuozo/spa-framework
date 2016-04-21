package virtuozo.interfaces;

import virtuozo.infra.Elements;


public class InputLabel extends Component<InputLabel> implements HasText<InputLabel>{
  private Text text = Text.create();
  
  public static InputLabel create(){
    return new InputLabel();
  }
  
  InputLabel() {
    super(Elements.label());
    this.addChild(this.text);
  }

  public InputLabel to(UIInput<?, ?> input) {
    return this.attribute("for", input.asComponent().id());
  }
  
  @Override
  public String text() {
    return this.text.text();
  }
  
  @Override
  public InputLabel text(String text) {
    this.text.text(text);
    return this;
  }
}