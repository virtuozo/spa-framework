package virtuozo.infra.events;

import virtuozo.infra.events.FinishEvent.FinishHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class FinishEvent extends GwtEvent<FinishHandler> {
  public static final Type<FinishHandler> TYPE = new Type<FinishHandler>();

  @Override
  public Type<FinishHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(FinishHandler handler) {
    handler.onFinish(this);
  }

  public static interface FinishHandler extends EventHandler {
    void onFinish(FinishEvent event);
  }
}