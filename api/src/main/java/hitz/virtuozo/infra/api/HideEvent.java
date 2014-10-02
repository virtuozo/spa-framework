package hitz.virtuozo.infra.api;

import hitz.virtuozo.infra.api.HideEvent.HideHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class HideEvent extends GwtEvent<HideHandler> {
  private static final Type<HideHandler> type = new Type<HideHandler>();
  
  public static Type<HideHandler> type() {
    return type;
  }
  
  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<HideHandler> getAssociatedType() {
    return type;
  }

  @Override
  protected void dispatch(HideHandler handler) {
    handler.onHide(this);
  }
  
  public static interface HideHandler extends EventHandler {
    void onHide(HideEvent event);
  }

}