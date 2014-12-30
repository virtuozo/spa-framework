package virtuozo.ui.api;

import virtuozo.ui.api.DetachChildrenEvent.DetachChildrenHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class DetachChildrenEvent extends GwtEvent<DetachChildrenHandler> {
  public static final Type<DetachChildrenHandler> TYPE = new Type<DetachChildrenHandler>();
  
  @Override
  public Type<DetachChildrenHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DetachChildrenHandler handler) {
    handler.onDetachChildren(this);
  }
  
  public static interface DetachChildrenHandler extends EventHandler {
    void onDetachChildren(DetachChildrenEvent event);
  }

}