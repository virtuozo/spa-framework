package virtuozo.interfaces.css;

import virtuozo.infra.StyleChooser;
import virtuozo.interfaces.CssClass;

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