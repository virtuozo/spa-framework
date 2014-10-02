package hitz.virtuozo.ui;

import hitz.virtuozo.ui.MoveUpEvent.MoveUpHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class MoveUpEvent extends GwtEvent<MoveUpHandler> {
  private static final Type<MoveUpHandler> type = new Type<MoveUpEvent.MoveUpHandler>();
  
  public static Type<MoveUpHandler> type() {
    return type;
  }
  
  @Override
  public Type<MoveUpHandler> getAssociatedType() {
    return type;
  }
  
  @Override
  protected void dispatch(MoveUpHandler handler) {
    handler.onMoveUp(this);
  }
  
  public static interface MoveUpHandler extends EventHandler{
    void onMoveUp(MoveUpEvent event);
  }
}