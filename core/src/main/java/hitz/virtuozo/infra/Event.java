package hitz.virtuozo.infra;

import com.google.inject.Singleton;

@Singleton
public abstract class Event<S> {
  protected abstract String name();
  
  public Publisher<S> publish(){
    return EventBus.get().publish(this);
  }
  
  public Subscriber<S> subscribe(){
    return EventBus.get().subscribe(this);
  }
}