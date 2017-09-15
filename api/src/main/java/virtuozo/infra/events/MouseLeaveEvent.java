package virtuozo.infra.events;

import virtuozo.infra.events.MouseLeaveEvent.MouseLeaveHandler;

import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.event.dom.client.MouseEvent;
import com.google.gwt.event.shared.EventHandler;

public class MouseLeaveEvent extends MouseEvent<MouseLeaveHandler> {

  private static final Type<MouseLeaveHandler> TYPE = new Type<MouseLeaveHandler>(
      "mouseleave", new MouseLeaveEvent());
  
  public static Type<MouseLeaveHandler> getType() {
    return TYPE;
  }
  
  @Override
  public com.google.gwt.event.dom.client.DomEvent.Type<MouseLeaveHandler> getAssociatedType() {
    return TYPE;
  }
  
  public EventTarget getRelatedTarget() {
    return getNativeEvent().getRelatedEventTarget();
  }
  
  @Override
  protected void dispatch(MouseLeaveHandler handler) {
    handler.onMouseLeave(this);
  }
  
  public static interface MouseLeaveHandler extends EventHandler{
    void onMouseLeave(MouseLeaveEvent event);
  }
}