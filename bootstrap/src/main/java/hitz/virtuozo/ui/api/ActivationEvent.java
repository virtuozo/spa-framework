package hitz.virtuozo.ui.api;

import hitz.virtuozo.ui.api.ActivationEvent.ActivationHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ActivationEvent extends GwtEvent<ActivationHandler>{
  public static final Type<ActivationHandler> TYPE = new Type<ActivationHandler>();

  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<ActivationHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ActivationHandler handler) {
    handler.onActivate(this);
  }
  
  public static interface ActivationHandler extends EventHandler{
    void onActivate(ActivationEvent event);
  }
}