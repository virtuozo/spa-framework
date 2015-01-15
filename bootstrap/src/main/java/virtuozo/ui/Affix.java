package virtuozo.ui;

import virtuozo.infra.Logger;
import virtuozo.ui.events.ShowEvent;
import virtuozo.ui.events.ShowEvent.ShowHandler;
import virtuozo.ui.interfaces.AttachHandler;
import virtuozo.ui.interfaces.UIComponent;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.google.gwt.user.client.Window.ScrollHandler;

public class Affix {
  private UIComponent target;
  
  private int top;
  
  private int width;
  
  public static Affix create(){
    return new Affix();
  }
  
  private Affix() {
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
  
  public Affix to(final UIComponent target) {
    this.target = target;
    
    target.asComponent().onAttach(new AttachHandler() {
      
      @Override
      protected void onAttach(AttachEvent event) {
        Affix.this.offset();
      }
    }).onShow(new ShowHandler() {
      
      @Override
      public void onShow(ShowEvent event) {
        Affix.this.offset();
      }
    });
    return this;
  }
  
  private void offset(){
    this.top = this.target.asComponent().offset().top();
    this.width = this.target.asComponent().outerWidth();
  }
  
  private void handle(){
    Logger.get().error("window top:" + Window.getScrollTop());
    Logger.get().error("target top:" + this.top);
    
    if(Window.getScrollTop() > this.top) {
      this.target.asComponent().css("affix").style().top(10, Unit.PX).width(this.width, Unit.PX);
      return;
    }
    
    this.target.asComponent().css().remove("affix");
    this.target.asComponent().style().clearTop().clearWidth();
  }
}