package hitz.virtuozo.ui;

import hitz.virtuozo.infra.api.HasMouseHandlers;
import hitz.virtuozo.ui.CssClass;
import hitz.virtuozo.ui.Elements;
import hitz.virtuozo.ui.HTML;
import hitz.virtuozo.ui.StyleChooser;
import hitz.virtuozo.ui.Widget;
import hitz.virtuozo.ui.api.UIWidget;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.user.client.ui.Offset;

@SuppressWarnings("unchecked")
@Deprecated
public abstract class FloatPanel<P extends FloatPanel<P>> extends Widget<P> implements HasMouseHandlers<P> {

  private Widget<?> target;

  private Position position = Position.BOTTOM;

  private DisplayState display = DisplayState.STATIC;

  public FloatPanel() {
    super(Elements.div());
  }

  @Override
  public P onMouseDown(MouseDownHandler handler) {
    return this.on(handler);
  }

  @Override
  public P onMouseMove(MouseMoveHandler handler) {
    return this.on(handler);
  }

  @Override
  public P onMouseOut(MouseOutHandler handler) {
    return this.on(handler);
  }

  @Override
  public P onMouseOver(MouseOverHandler handler) {
    return this.on(handler);
  }

  @Override
  public P onMouseUp(MouseUpHandler handler) {
    return this.on(handler);
  }

  @Override
  public P onMouseWheel(MouseWheelHandler handler) {
    return this.on(handler);
  }

  public P target(Widget<?> target) {
    this.target = target;
    return (P) this;
  }

  public P show() {
    if (!this.attached()) {
      HTML.body().addChild(this);
    }
    this.positioning();
    this.display.show((P) this);

    return (P) this;
  }
  
  public P hide() {
    this.display.hide((P) this);
    return (P) this;
  }
  
  @Override
  public P toggleVisibility() {
    this.display.toggle((P) this);
    return (P) this;
  }

  public P position(Position position) {
    this.position = position;
    this.css(position);

    return (P) this;
  }

  public P trigger(Trigger... triggers) {
    for (Trigger trigger : triggers) {
      trigger.register(this.target, (P) this);
    }

    return (P) this;
  }
  
  protected void open(){
    super.show();
  }
  
  protected void close(){
    super.hide();
  }
  
  protected void toggle(){
    super.toggleVisibility();
  }

  protected void positioning() {
    int top = 0;
    int left = 0;
    int actualWidth = this.outerWidth();
    int actualHeight = this.outerHeight();

    Offset pos = this.target.offset();
    int height = this.target.offsetHeight();
    int width = this.target.offsetWidth();

    if(Position.BOTTOM.equals(this.position)) {
      top = pos.top() + height;
      left = pos.left() + width / 2 - actualWidth / 2;
    } else if (Position.TOP.equals(this.position)) {
      top = pos.top() - actualHeight;
      left = pos.left() + width / 2 - actualWidth / 2;
    } else if (Position.LEFT.equals(this.position)) {
      top = pos.top() + height / 2 - actualHeight / 2;
      left = pos.left() - actualWidth;
    } else if (Position.RIGHT.equals(this.position)) {
        top = pos.top() + height / 2 - actualHeight / 2;
        left = pos.left() + width;
    }
    
    this.style().top(top, Unit.PX).left(left, Unit.PX).display(Display.BLOCK);
  }

  enum DisplayState {
    ANIMATE {

      @Override
      public <P extends FloatPanel<P>> void show(P panel) {
        panel.css().remove("out").append("in");
      }

      public <P extends FloatPanel<P>> void hide(P panel) {
        panel.css().remove("in").append("out");
      }

      public <P extends FloatPanel<P>> void toggle(P panel) {
        if (panel.css().contains("in")) {
          this.hide(panel);
          return;
        }
        this.show(panel);
      }
    },
    STATIC {

      @Override
      public <P extends FloatPanel<P>> void show(P panel) {
        panel.open();
      }

      public <P extends FloatPanel<P>> void hide(P panel) {
        panel.close();
      }

      public <P extends FloatPanel<P>> void toggle(P panel) {
        panel.toggle();
      }
    };

    public abstract <P extends FloatPanel<P>> void show(P panel);

    public abstract <P extends FloatPanel<P>> void hide(P panel);

    public abstract <P extends FloatPanel<P>> void toggle(P panel);
  }

  public enum Trigger {
    CLICK {

      @Override
      <P extends FloatPanel<P>> void register(UIWidget target, final P panel) {
        target.asWidget().on(new ClickHandler() {

          @Override
          public void onClick(ClickEvent event) {
            panel.toggleVisibility();
          }
        });
      }
    },
    HOVER {

      @Override
      <P extends FloatPanel<P>> void register(UIWidget target, final P panel) {
        target.asWidget().on(new MouseOverHandler() {

          @Override
          public void onMouseOver(MouseOverEvent event) {
            panel.show();
          }
        });

        target.asWidget().on(new MouseOutHandler() {

          @Override
          public void onMouseOut(MouseOutEvent event) {
            panel.hide();
          }
        });
      }
    },
    FOCUS {

      @Override
      <P extends FloatPanel<P>> void register(UIWidget target, final P panel) {
        target.asWidget().on(new FocusHandler() {

          @Override
          public void onFocus(FocusEvent event) {
            panel.show();
          }
        });

        target.asWidget().on(new BlurHandler() {
          @Override
          public void onBlur(BlurEvent event) {
            panel.hide();
          }
        });
      }
    },
    MANUAL {

      @Override
      <P extends FloatPanel<P>> void register(UIWidget target, final P panel) {
        return;
      }
    };

    abstract <P extends FloatPanel<P>> void register(UIWidget target, P panel);
  }
  
  public static class Position extends CssClass {
    private Position(String name) {
      super(name);
    }
    
    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    public static final Position BOTTOM = new Position("bottom");
    public static final Position LEFT = new Position("left");
    public static final Position RIGHT = new Position("right");
    public static final Position TOP = new Position("top");
    private static final StyleChooser STYLES = new StyleChooser(BOTTOM, LEFT, RIGHT, TOP);

    public static class Horizontal {
      public static final Position LEFT = Position.LEFT;
      public static final Position RIGHT = Position.RIGHT;
    }

    public static class Vertical {
      public static final Position BOTTOM = Position.BOTTOM;
      public static final Position TOP = Position.TOP;
    }

  }
}