package virtuozo.infra;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerManager;

public class EventManager {
  private HandlerManager bus;
  
  private EventManager() {
    this(new HandlerManager(null));
  }
  
  private EventManager(HandlerManager bus){
    this.bus = bus;
  }
  
  public static EventManager create(){
    return new EventManager();
  }
  
  public static EventManager create(HandlerManager bus){
    return new EventManager(bus);
  }
  
  public <H extends EventHandler> EventManager add(Type<H> type, H handler){
    this.bus.addHandler(type, handler);
    return this;
  }
  
  public EventManager fire(GwtEvent<?> event){
    this.bus.fireEvent(event);
    return this;
  }
  
  public <H extends EventHandler> EventManager remove(Type<H> type, H handler){
    this.bus.removeHandler(type, handler);
    return this;
  }
}