package hitz.virtuozo.infra;

import hitz.virtuozo.infra.BooleanProperty;
import hitz.virtuozo.infra.api.ChangeHandler;

public class InputState {
  private BooleanProperty enablement = new BooleanProperty();

  public InputState enable() {
    this.enablement.set(true);
    return this;
  }

  public InputState disable() {
    this.enablement.set(false);
    return this;
  }

  public InputState onEnablementChange(ChangeHandler<Boolean> handler) {
    this.enablement.onChange(handler);
    return this;
  }
  
  public boolean disabled(){
    return !this.enablement.get();
  }
}