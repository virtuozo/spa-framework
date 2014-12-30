package virtuozo.ui;

import virtuozo.ui.DrawEvent.DrawHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class DrawEvent extends GwtEvent<DrawHandler> {
  public static final Type<DrawHandler> TYPE = new Type<DrawHandler>();

  public Type<DrawHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(DrawHandler handler) {
    handler.onDraw(this);
  }

  public static interface DrawHandler extends EventHandler {
    void onDraw(DrawEvent event);
  }
}