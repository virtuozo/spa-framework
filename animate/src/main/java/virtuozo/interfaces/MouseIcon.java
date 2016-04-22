package virtuozo.interfaces;

import virtuozo.infra.handlers.AttachHandler;

import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.logical.shared.AttachEvent;

public class MouseIcon implements Icon {
  private Tag<SpanElement> mouse = Tag.asSpan().css("mouse");
  private Tag<SpanElement> scroller = Tag.asSpan().css("mouse-scroller");
  
  private MouseIcon() {
    this.mouse.add(this.scroller);
    this.scroller.onAttach(new AttachHandler() {
      @Override
      protected void onAttach(AttachEvent event) {
        Animate.fadeInDown.loop(scroller);
      }
    });
  }
  
  public static MouseIcon create(){
    return new MouseIcon();
  }
  
  @Override
  public UIComponent asComponent() {
    return this.mouse;
  }
  
  @Override
  public void attachTo(UIComponent component) {
    component.asComponent().addChild(this.mouse);
  }

  @Override
  public void update(UIComponent component) {
    return;
  }

  @Override
  public String name() {
    return "mouse";
  }

  @Override
  public boolean is(UIComponent component) {
    return component.asComponent().css().contains("mouse");
  }
}