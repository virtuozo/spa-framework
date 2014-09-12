package hitz.virtuozo.ui;

import hitz.virtuozo.infra.api.HasText;
import hitz.virtuozo.ui.api.UIInput;

public class InputLabel extends Widget<InputLabel> implements HasText<InputLabel>{
  public InputLabel() {
    super(Elements.label());
  }

  public InputLabel to(UIInput<?, ?> input) {
    return this.attribute("for", input.asWidget().id());
  }
  
  @Override
  public String text() {
    return this.element().getInnerText();
  }
  
  @Override
  public InputLabel text(String text) {
    this.element().setInnerText(text);
    return this;
  }
}