package hitz.virtuozo.ui;

import hitz.virtuozo.ui.api.Placeholder;
import hitz.virtuozo.ui.api.UIInput;

public class PlaceholderHTML5 implements Placeholder {

  @Override
  public void apply(UIInput<?, String> input, String placeholder) {
    input.asComponent().attribute("placeholder", placeholder);
  }

  @Override
  public String valueOf(UIInput<?, String> input) {
    return ((Input<?>)input).element().getValue();
  }
}
