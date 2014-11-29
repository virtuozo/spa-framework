package virtuozo.ui.api;

import virtuozo.ui.api.ShowEvent.ShowHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ShowEvent extends GwtEvent<ShowHandler> {
  private static final Type<ShowHandler> type = new Type<ShowHandler>();
  
  public static Type<ShowHandler> type() {
    return type;
  }
  
  @Override
  public Type<ShowHandler> getAssociatedType() {
    return type;
  }

  @Override
  protected void dispatch(ShowHandler handler) {
    handler.onShow(this);
  }
  
  public static interface ShowHandler extends EventHandler {
    void onShow(ShowEvent event);
  }

}