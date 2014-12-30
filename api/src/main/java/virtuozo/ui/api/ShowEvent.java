package virtuozo.ui.api;

import virtuozo.ui.api.ShowEvent.ShowHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ShowEvent extends GwtEvent<ShowHandler> {
  public static final Type<ShowHandler> TYPE = new Type<ShowHandler>();
  
  @Override
  public Type<ShowHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(ShowHandler handler) {
    handler.onShow(this);
  }
  
  public static interface ShowHandler extends EventHandler {
    void onShow(ShowEvent event);
  }

}