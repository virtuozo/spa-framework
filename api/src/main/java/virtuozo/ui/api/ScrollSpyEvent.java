package virtuozo.ui.api;

import virtuozo.ui.api.ScrollSpyEvent.ScrollSpyHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ScrollSpyEvent extends GwtEvent<ScrollSpyHandler> {
  public static final Type<ScrollSpyHandler> TYPE = new Type<ScrollSpyHandler>();
  
  private boolean inRange;
  
  public ScrollSpyEvent(boolean inRange) {
    this.inRange = inRange;
  }
  
  public boolean isInRange() {
    return inRange;
  }
  
  @Override
  public Type<ScrollSpyHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ScrollSpyHandler handler) {
    handler.onScroll(this);
  }
  
  public static interface ScrollSpyHandler extends EventHandler {
    void onScroll(ScrollSpyEvent event);
  }

}