package virtuozo.ui;

import java.util.ArrayList;
import java.util.List;

import virtuozo.ui.events.ScrollSpyEvent;
import virtuozo.ui.events.ScrollSpyEvent.ScrollSpyHandler;
import virtuozo.ui.interfaces.DetachHandler;
import virtuozo.ui.interfaces.UIComponent;

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
      int top = Window.getScrollTop() + target.asComponent().top();
      
      int height = top + target.asComponent().innerHeight();
      boolean inRange = Window.getScrollTop() >= top && Window.getScrollTop() < height;
      target.asComponent().fireEvent(new ScrollSpyEvent(inRange));
    }
  }
}