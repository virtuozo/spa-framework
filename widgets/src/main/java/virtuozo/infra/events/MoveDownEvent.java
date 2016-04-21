package virtuozo.infra.events;

import virtuozo.infra.events.MoveDownEvent.MoveDownHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class MoveDownEvent extends GwtEvent<MoveDownHandler> {
  public static final Type<MoveDownHandler> TYPE = new Type<MoveDownEvent.MoveDownHandler>();
  
  @Override
  public Type<MoveDownHandler> getAssociatedType() {
    return TYPE;
  }
  
  @Override
  protected void dispatch(MoveDownHandler handler) {
    handler.onMoveDown(this);
  }
  
  public static interface MoveDownHandler extends EventHandler{
    void onMoveDown(MoveDownEvent event);
  }
}