package hitz.virtuozo.ui;

import hitz.virtuozo.ui.MoveDownEvent.MoveDownHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class MoveDownEvent extends GwtEvent<MoveDownHandler> {
  private static final Type<MoveDownHandler> type = new Type<MoveDownEvent.MoveDownHandler>();
  
  public static Type<MoveDownHandler> type() {
    return type;
  }
  
  @Override
  public Type<MoveDownHandler> getAssociatedType() {
    return type;
  }
  
  @Override
  protected void dispatch(MoveDownHandler handler) {
    handler.onMoveDown(this);
  }
  
  public static interface MoveDownHandler extends EventHandler{
    void onMoveDown(MoveDownEvent event);
  }
}