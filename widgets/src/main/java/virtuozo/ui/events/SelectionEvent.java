package virtuozo.ui.events;

import virtuozo.ui.events.SelectionEvent.SelectionHandler;

import com.google.gwt.event.shared.EventHandler;

public class SelectionEvent<T> extends ValueEvent<T, SelectionHandler<T>> {
  public static final Type<SelectionHandler<?>> TYPE = new Type<SelectionEvent.SelectionHandler<?>>();
  
  public SelectionEvent(T value) {
    super(value);
  }
  
  @Override
  public Type<SelectionHandler<T>> getAssociatedType() {
    return (Type) TYPE;
  }

  @Override
  protected void dispatch(SelectionHandler<T> handler) {
    handler.onSelect(this);
  }
  
  public static interface SelectionHandler<T> extends EventHandler{
    void onSelect(SelectionEvent<T> event);
  }
}