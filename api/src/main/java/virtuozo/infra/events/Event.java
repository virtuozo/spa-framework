package virtuozo.infra.events;

public abstract class Event<S> {
  
  protected Event() {
    super();
  }
  
  public Publisher<S> publish(){
    return EventBus.create().publish(this);
  }
  
  public Subscriber<S> subscribe(){
    return EventBus.create().subscribe(this);
  }
  
  @Override
  public int hashCode() {
    return this.name().hashCode();
  }
  
  @Override
  public boolean equals(Object obj) {
    if(!(obj instanceof Event) || obj == null){
      return false;
    }
    Event<?> event = (Event<?>) obj;
    return super.equals(obj) || event.hashCode() == this.hashCode();
  }
  
  protected String name(){
    return this.getClass().getName();
  }
}