package virtuozo.infra;

import java.util.ArrayList;
import java.util.List;

import virtuozo.infra.events.ScrollSpyEvent;
import virtuozo.infra.events.ScrollSpyEvent.ScrollSpyHandler;
import virtuozo.infra.handlers.DetachHandler;
import virtuozo.interfaces.UIComponent;

import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.google.gwt.user.client.Window.ScrollHandler;

public class ScrollSpy {
  private List<UIComponent> monitor = new ArrayList<UIComponent>();
  
  public static ScrollSpy create() {
    return new ScrollSpy();
  }
  
  public ScrollSpy dispose(){
    this.monitor.clear();
    return this;
  }
  
  public ScrollSpy spy(final UIComponent target, ScrollSpyHandler handler) {
    this.monitor.add(target);
    target.asComponent().onDetach(new DetachHandler() {
      
      @Override
      protected void onDetach(AttachEvent event) {
        ScrollSpy.this.monitor.remove(target);
      }
    }).onScroll(handler);
    
    return this;
  }
  
  public ScrollSpy unspy(UIComponent target){
    this.monitor.remove(target);
    return this;
  }
  
  public boolean isInRange(UIComponent target){
    double top = Window.getScrollTop() + target.asComponent().top();
    
    double height = top + target.asComponent().measurement().innerHeight();
    return Window.getScrollTop() >= top && Window.getScrollTop() < height;
  }
  
  private ScrollSpy() {
    this.init();
  }
  
  private void init(){
    Window.addWindowScrollHandler(new ScrollHandler() {
      @Override
      public void onWindowScroll(ScrollEvent event) {
        handle();
      }
    });
    
  }
  
  private void handle(){
    for(UIComponent target : this.monitor){
      boolean inRange = this.isInRange(target);
      target.asComponent().fireEvent(new ScrollSpyEvent(inRange));
    }
  }
}