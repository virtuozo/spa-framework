package virtuozo.ui.api;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public abstract class ValueEvent<V, H extends EventHandler> extends GwtEvent<H>{
  private V value;
  
  public ValueEvent(V value) {
    super();
    this.value = value;
  }

  public V value() {
    return value;
  }
}