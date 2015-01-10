package virtuozo.ui.events;

import virtuozo.ui.interfaces.EventInterceptor;

import com.google.gwt.user.client.Event;

public class SimpleEventInterceptor implements EventInterceptor {
  private boolean state = true;
  
  public static SimpleEventInterceptor create(){
    return new SimpleEventInterceptor();
  }
  
  private SimpleEventInterceptor() {
    super();
  }
  
  @Override
  public boolean shouldFire(Event event) {
    return this.state;
  }
  
  public SimpleEventInterceptor on(){
    this.state = true;
    return this;
  }
  
  public SimpleEventInterceptor off(){
    this.state = false;
    return this;
  }
}