package hitz.virtuozo.ui.api;

import hitz.virtuozo.ui.api.ScrollSpyEvent.ScrollSpyHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ScrollSpyEvent extends GwtEvent<ScrollSpyHandler> {
  private static final Type<ScrollSpyHandler> type = new Type<ScrollSpyHandler>();
  
  private boolean inRange;
  
  public static Type<ScrollSpyHandler> type() {
    return type;
  }
  
  public ScrollSpyEvent(boolean inRange) {
    this.inRange = inRange;
  }
  
  public boolean isInRange() {
    return inRange;
  }
  
  @Override
  public Type<ScrollSpyHandler> getAssociatedType() {
    return type;
  }

  @Override
  protected void dispatch(ScrollSpyHandler handler) {
    handler.onScroll(this);
  }
  
  public static interface ScrollSpyHandler extends EventHandler {
    void onScroll(ScrollSpyEvent event);
  }

}