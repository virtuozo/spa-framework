package hitz.virtuozo.ui.css;

import hitz.virtuozo.ui.CssClass;
import hitz.virtuozo.ui.StyleChooser;

public class State extends CssClass {
  private State(String name) {
    super(name);
  }

  @Override
  protected StyleChooser chooser() {
    return STATES;
  }

  public static final State ACTIVE = new State("active");
  public static final State DISABLED = new State("disabled");
  private static final StyleChooser STATES = new StyleChooser(ACTIVE, DISABLED);
}