package hitz.virtuozo.ui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;

public class EventHandlers {
  private HandlerManager bus;
  
  private Map<EventHandler, HandlerRegistration> register = new HashMap<EventHandler, HandlerRegistration>();
  
  public EventHandlers() {
    this(new HandlerManager(null));
  }
  
  EventHandlers(HandlerManager bus){
    this.bus = bus;
  }
  
  public <H extends EventHandler> EventHandlers add(Type<H> type, H handler){
    if(this.register.containsKey(handler)){
      this.register.get(handler).removeHandler();
    }
    
    HandlerRegistration registration = this.bus.addHandler(type, handler);
    this.register.put(handler, registration);
    return this;
  }
  
  public EventHandlers fire(GwtEvent<?> event){
    this.bus.fireEvent(event);
    return this;
  }
  
  public <H extends EventHandler> EventHandlers remove(Type<H> type, H handler){
    this.bus.removeHandler(type, handler);
    return this;
  }
  
  public EventHandlers release(){
    
    for(HandlerRegistration registration : this.register.values()){
      registration.removeHandler();
    }
    
    this.register.clear();
    return this;
  }
}