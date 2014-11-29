package virtuozo.ui;

import virtuozo.ui.DrawEvent.DrawHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class DrawEvent extends GwtEvent<DrawHandler> {
  private static final Type<DrawHandler> type = new Type<DrawHandler>();

  public static Type<DrawHandler> type() {
    return type;
  }

  public Type<DrawHandler> getAssociatedType() {
    return type;
  }

  protected void dispatch(DrawHandler handler) {
    handler.onDraw(this);
  }

  public static interface DrawHandler extends EventHandler {
    void onDraw(DrawEvent event);
  }
}