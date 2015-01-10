package virtuozo.ui.events;

import virtuozo.ui.events.HideEvent.HideHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class HideEvent extends GwtEvent<HideHandler> {
  public static final Type<HideHandler> TYPE = new Type<HideHandler>();
  
  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<HideHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(HideHandler handler) {
    handler.onHide(this);
  }
  
  public static interface HideHandler extends EventHandler {
    void onHide(HideEvent event);
  }

}