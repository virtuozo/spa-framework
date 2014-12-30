package virtuozo.ui;

import virtuozo.ui.MoveUpEvent.MoveUpHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class MoveUpEvent extends GwtEvent<MoveUpHandler> {
  public static final Type<MoveUpHandler> TYPE = new Type<MoveUpEvent.MoveUpHandler>();
  
  @Override
  public Type<MoveUpHandler> getAssociatedType() {
    return TYPE;
  }
  
  @Override
  protected void dispatch(MoveUpHandler handler) {
    handler.onMoveUp(this);
  }
  
  public static interface MoveUpHandler extends EventHandler{
    void onMoveUp(MoveUpEvent event);
  }
}