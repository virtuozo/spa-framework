package virtuozo.interfaces;

import virtuozo.infra.Async;
import virtuozo.infra.events.ShowEvent;
import virtuozo.infra.events.ShowEvent.ShowHandler;
import virtuozo.infra.handlers.AttachHandler;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.google.gwt.user.client.Window.ScrollHandler;

public class Affix {
  private Type type;

  private UIComponent target;

  private double top;

  private double width;

  public static Affix onBottom() {
    return new Affix(Type.BOTTOM);
  }

  public static Affix onTop() {
    return new Affix(Type.TOP);
  }

  private Affix(Type type) {
    this.type = type;
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

  private void offset() {
    Async.get().execute(new Runnable() {
      @Override
      public void run() {
        Affix.this.top = Affix.this.target.asComponent().offset().top();
        Affix.this.width = Affix.this.target.asComponent().measurement().outerWidth();
      }
    }, 1000);
  }

  private void handle() {
    if (Window.getScrollTop() > this.top) {
      this.target.asComponent().css("affix", this.type.css()).style().width(this.width, Unit.PX);
      return;
    }

    this.target.asComponent().css().remove("affix", this.type.css());
    this.target.asComponent().style().clearTop().clearWidth();
  }

  static enum Type {
    TOP, MIDDLE, BOTTOM;

    String css() {
      return "affix-on-" + this.name().toLowerCase();
    }
  }
}