package virtuozo.ui.events;

import virtuozo.ui.events.ToggleEvent.ToggleHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ToggleEvent extends GwtEvent<ToggleHandler> {
  public static final Type<ToggleHandler> TYPE = new Type<ToggleHandler>();
  
  @Override
  public Type<ToggleHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ToggleHandler handler) {
    handler.onToggle(this);
  }
  
  public static interface ToggleHandler extends EventHandler {
    void onToggle(ToggleEvent event);
  }

}