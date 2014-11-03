package hitz.virtuozo.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;

public class EventHandlers {
  private HandlerManager bus;
  
  private List<HandlerRegistration> register = new ArrayList<HandlerRegistration>();
  
  public EventHandlers() {
    this(new HandlerManager(null));
  }
  
  EventHandlers(HandlerManager bus){
    this.bus = bus;
  }
  
  public <H extends EventHandler> EventHandlers add(Type<H> type, H handler){
    this.register.add(this.bus.addHandler(type, handler));
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
    
    for(HandlerRegistration registration : this.register){
      registration.removeHandler();
    }
    
    this.register.clear();
    return this;
  }
}