package virtuozo.ui;

import virtuozo.ui.api.EventInterceptor;

import com.google.gwt.user.client.Event;

public class SimpleEventInterceptor implements EventInterceptor {
  private boolean state = true;
  
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