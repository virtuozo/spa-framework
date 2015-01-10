package virtuozo.infra;

import java.util.HashMap;
import java.util.Map;

import virtuozo.infra.interfaces.SubscriptionCallback;
import virtuozo.ui.EventManager;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;

@SuppressWarnings({"rawtypes", "unchecked"})
class EventBus {
  private static final EventBus instance = new EventBus();
  
  private EventManager handlers = EventManager.create();
  
  private Map<Event, Type<SubscriptionCallback>> types = new HashMap<Event, Type<SubscriptionCallback>>();
  
  private EventBus() {}
  
  static EventBus get() {
    return instance;
  }
  
  <S> Publisher<S> publish(Event<S> event) {
    if(!this.types.containsKey(event)){
      this.types.put(event, new Type<SubscriptionCallback>());
    }
    
    return new Publisher<S>(this.types.get(event));
  }
  
  <S> Subscriber<S> subscribe(Event<S> event){
    if(!this.types.containsKey(event)){
      this.types.put(event, new Type<SubscriptionCallback>());
    }
    
    return new Subscriber<S>(this.types.get(event));
  }
  
  <H extends EventHandler> void add(Type<H> type, H handler) {
    this.handlers.add(type, handler);
  }
  
  void fire(GwtEvent<?> event){
    this.handlers.fire(event);
  }
  
  static class PublishEvent<S> extends GwtEvent<SubscriptionCallback> {
    private S subject;
    
    private Type<SubscriptionCallback> type;

    PublishEvent(S subject, Type<SubscriptionCallback> type) {
      this.subject = subject;
      this.type = type;
    }
    
    @Override
    protected void dispatch(SubscriptionCallback handler) {
      handler.onPublish(this.subject);
    }
    
    @Override
    public Type<SubscriptionCallback> getAssociatedType() {
      return this.type;
    }
  }
}