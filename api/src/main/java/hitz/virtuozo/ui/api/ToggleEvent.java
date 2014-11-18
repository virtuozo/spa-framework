package hitz.virtuozo.ui.api;

import hitz.virtuozo.ui.api.ToggleEvent.ToggleHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ToggleEvent extends GwtEvent<ToggleHandler> {
  private static final Type<ToggleHandler> type = new Type<ToggleHandler>();
  
  public static Type<ToggleHandler> type(){
    return type;
  }
  
  @Override
  public Type<ToggleHandler> getAssociatedType() {
    return type;
  }

  @Override
  protected void dispatch(ToggleHandler handler) {
    handler.onToggle(this);
  }
  
  public static interface ToggleHandler extends EventHandler {
    void onToggle(ToggleEvent event);
  }

}