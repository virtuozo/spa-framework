package virtuozo.ui.api;

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

}
