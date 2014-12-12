package virtuozo.ui;

import java.util.ArrayList;
import java.util.List;

import virtuozo.infra.Logger;
import virtuozo.ui.api.DetachHandler;
import virtuozo.ui.api.ScrollSpyEvent;
import virtuozo.ui.api.UIComponent;
import virtuozo.ui.api.ScrollSpyEvent.ScrollSpyHandler;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.google.gwt.user.client.Window.ScrollHandler;

public class ScrollSpy {
  private static final ScrollSpy instance = new ScrollSpy();
  
  private List<UIComponent> monitor = new ArrayList<UIComponent>();
  
  public static ScrollSpy get() {
    return instance;
  }
  
  public ScrollSpy dispose(){
    this.monitor.clear();
    return this;
  }
  
  public ScrollSpy spy(final UIComponent target, ScrollSpyHandler handler){
    this.monitor.add(target);
    target.asComponent().onDetach(new DetachHandler() {
      
      @Override
      protected void onDetach(AttachEvent event) {
        ScrollSpy.this.monitor.remove(target);
      }
    }).on(handler);
    
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
      int max = Math.max(Document.get().getDocumentElement().getScrollHeight(), HTML.body().scrollHeight());
      
      int height = top + target.asComponent().innerHeight();
      boolean inRange = Window.getScrollTop() >= top && Window.getScrollTop() < height;
      target.asComponent().fireEvent(new ScrollSpyEvent(inRange));
    }
  }
}
