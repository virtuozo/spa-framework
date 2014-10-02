package hitz.virtuozo.ui.api;

import hitz.virtuozo.ui.api.DeactivationEvent.DeactivationHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class DeactivationEvent extends GwtEvent<DeactivationHandler>{
  public static final Type<DeactivationHandler> TYPE = new Type<DeactivationHandler>();

  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<DeactivationHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DeactivationHandler handler) {
    handler.onDeactivate(this);
  }
  
  public static interface DeactivationHandler extends EventHandler{
    void onDeactivate(DeactivationEvent event);
  }
}