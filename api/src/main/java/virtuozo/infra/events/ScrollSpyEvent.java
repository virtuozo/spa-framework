package virtuozo.infra.events;

import virtuozo.infra.events.ScrollSpyEvent.ScrollSpyHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ScrollSpyEvent extends GwtEvent<ScrollSpyHandler> {
  public static final Type<ScrollSpyHandler> TYPE = new Type<ScrollSpyHandler>();
  
  private int index;
  
  private boolean inRange;
  
  private boolean visible;
  
  public ScrollSpyEvent(int index, boolean inRange, boolean visible) {
    this.index = index;
    this.inRange = inRange;
    this.visible = visible;
  }
  
  public int index() {
    return index;
  }
  
  public boolean inRange() {
    return inRange;
  }
  
  public boolean visible() {
    return visible;
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