package virtuozo.infra;

public abstract class Event<S> {
  protected abstract String name();
  
  public Publisher<S> publish(){
    return EventBus.get().publish(this);
  }
  
  public Subscriber<S> subscribe(){
    return EventBus.get().subscribe(this);
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
}