package virtuozo.infra;

import java.util.ArrayList;
import java.util.List;

import virtuozo.infra.events.ScrollSpyEvent;
import virtuozo.infra.events.ScrollSpyEvent.ScrollSpyHandler;
import virtuozo.infra.handlers.DetachHandler;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.UIComponent;

import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.google.gwt.user.client.Window.ScrollHandler;

public class ScrollSpy {
  private List<UIComponent> monitor = new ArrayList<UIComponent>();

  private static final ScrollSpy instance = new ScrollSpy();

  public static ScrollSpy get() {
    return instance;
  }

  public ScrollSpy dispose() {
    this.monitor.clear();
    return this;
  }

  public ScrollSpy spy(final UIComponent target, ScrollSpyHandler handler) {
    this.monitor.add(target);
    target.asComponent().onDetach(new DetachHandler() {
      @Override
      protected void onDetach(AttachEvent event) {
        unspy(target);
      }
    }).onScroll(handler);

    return this;
  }

  public ScrollSpy unspy(int index) {
    this.monitor.remove(index);
    return this;
  }

  /**
   * This method will remove all handlers associated with the target
   * 
   * @param target
   * @return
   */
  public ScrollSpy unspy(UIComponent target) {
    while (monitor.indexOf(target) > -1) {
      ScrollSpy.this.monitor.remove(target);
    }
    return this;
  }

  private ScrollSpy() {
    this.init();
  }

  private void init() {
    Window.addWindowScrollHandler(new ScrollHandler() {
      @Override
      public void onWindowScroll(ScrollEvent event) {
        handle();
      }
    });
  }

  private void handle() {
    int index = 0;
    for (UIComponent target : this.monitor) {

      boolean inRange = this.isInRange(target);
      boolean isVisible = this.isVisible(target);
      target.asComponent().fireEvent(new ScrollSpyEvent(index++, inRange, isVisible));

      Logger.get().debug("In range? " + inRange);
      Logger.get().debug("Visible? " + isVisible);
      Logger.get().debug("index? " + index);
      Logger.get().debug("Type? " + target);
    }
  }

  private boolean isInRange(UIComponent target) {
    Component<?> component = target.asComponent();
    double top = Window.getScrollTop() + component.top();

    double height = top + component.measurement().innerHeight();
    return Window.getScrollTop() >= top && Window.getScrollTop() < height;
  }

  private boolean isVisible(UIComponent target) {
    Component<?> component = target.asComponent();

    double height = component.measurement().outerHeight() / 2;

    double top = component.top() + height;
    
    return top < Window.getScrollTop();
  }
}