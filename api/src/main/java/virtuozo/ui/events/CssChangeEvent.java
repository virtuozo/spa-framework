package virtuozo.ui.events;

import virtuozo.ui.events.CssChangeEvent.CssChangeHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class CssChangeEvent extends GwtEvent<CssChangeHandler>{

  public static final Type<CssChangeHandler> TYPE = new Type<CssChangeHandler>();
  
  @Override
  public Type<CssChangeHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CssChangeHandler handler) {
    handler.onChange(this);
  }

  public interface CssChangeHandler extends EventHandler {
    void onChange(CssChangeEvent event);
  }
}
