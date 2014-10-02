package hitz.virtuozo.ui;

import hitz.virtuozo.infra.api.ValueEvent;
import hitz.virtuozo.ui.SelectionEvent.SelectionHandler;

import com.google.gwt.event.shared.EventHandler;

public class SelectionEvent<T> extends ValueEvent<T, SelectionHandler<T>> {
  private static final Type<SelectionHandler<?>> type = new Type<SelectionEvent.SelectionHandler<?>>();
  
  public static Type<SelectionHandler<?>> type() {
    return type;
  }
  
  public SelectionEvent(T value) {
    super(value);
  }
  
  @Override
  public Type<SelectionHandler<T>> getAssociatedType() {
    return (Type) type;
  }

  @Override
  protected void dispatch(SelectionHandler<T> handler) {
    handler.onSelect(this);
  }
  
  public static interface SelectionHandler<T> extends EventHandler{
    void onSelect(SelectionEvent<T> event);
  }
}