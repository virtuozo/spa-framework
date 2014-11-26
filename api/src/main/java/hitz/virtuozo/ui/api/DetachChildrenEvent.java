package hitz.virtuozo.ui.api;

import hitz.virtuozo.ui.api.DetachChildrenEvent.DetachChildrenHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class DetachChildrenEvent extends GwtEvent<DetachChildrenHandler> {
  private static final Type<DetachChildrenHandler> type = new Type<DetachChildrenHandler>();
  
  public static Type<DetachChildrenHandler> type() {
    return type;
  }
  
  @Override
  public Type<DetachChildrenHandler> getAssociatedType() {
    return type;
  }

  @Override
  protected void dispatch(DetachChildrenHandler handler) {
    handler.onDetachChildren(this);
  }
  
  public static interface DetachChildrenHandler extends EventHandler {
    void onDetachChildren(DetachChildrenEvent event);
  }

}