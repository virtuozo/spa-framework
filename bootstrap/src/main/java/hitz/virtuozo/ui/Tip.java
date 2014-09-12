package hitz.virtuozo.ui;

import hitz.virtuozo.infra.api.EventHandler;
import hitz.virtuozo.infra.api.HasMouseHandlers;
import hitz.virtuozo.infra.api.HasVisibility;
import hitz.virtuozo.ui.Event;
import hitz.virtuozo.ui.HTML;
import hitz.virtuozo.ui.Tag;
import hitz.virtuozo.ui.api.Direction;
import hitz.virtuozo.ui.api.UIWidget;

import com.google.gwt.dom.client.DivElement;
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
abstract class Tip<T extends Tip<T>> implements HasMouseHandlers<T>, HasVisibility<T> {

  private Tag<DivElement> tip = Tag.asDiv();
  
  private UIWidget target;

  private Direction direction;
  
  public Tip() {
    HTML.body().add(this.tip.hide());
    
    this.tip.onShow(new EventHandler<Void>() {
      @Override
      public void onEvent(Event<Void> e) {
        Tip.this.positioning();
      }
    });
  }
  
  protected T add(UIWidget widget){
    this.tip.add(widget);
    return (T) this;
  }
  
  protected T css(String... classes){
    this.tip.css(classes);
    return (T) this;
  }
  
  @Override
  public T onMouseDown(MouseDownHandler handler) {
    this.tip.on(handler);
    return (T) this;
  }

  @Override
  public T onMouseMove(MouseMoveHandler handler) {
    this.tip.on(handler);
    return (T) this;
  }

  @Override
  public T onMouseOut(MouseOutHandler handler) {
    this.tip.on(handler);
    return (T) this;
  }

  @Override
  public T onMouseOver(MouseOverHandler handler) {
    this.tip.on(handler);
    return (T) this;
  }

  @Override
  public T onMouseUp(MouseUpHandler handler) {
    this.tip.on(handler);
    return (T) this;
  }

  @Override
  public T onMouseWheel(MouseWheelHandler handler) {
    this.tip.on(handler);
    return (T) this;
  }

  public T placement(Direction direction) {
    this.direction = direction;
    this.tip.css(direction);

    return (T) this;
  }

  public T trigger(UIWidget holder, Trigger... triggers) {
    this.target = holder;
    for (Trigger trigger : triggers) {
      trigger.register(this.target, (T) this);
    }

    return (T) this;
  }
  
  @Override
  public T onHide(EventHandler<Void> handler) {
    this.tip.onHide(handler);
    return (T) this;
  }

  @Override
  public T onShow(EventHandler<Void> handler) {
    this.tip.onShow(handler);
    return (T) this;
  }

  @Override
  public T onToggleVisibility(EventHandler<Void> handler) {
    this.tip.onToggleVisibility(handler);
    return (T) this;
  }

  @Override
  public T show() {
    this.tip.show();
    return (T) this;
  }
  
  @Override
  public T hide() {
    this.tip.hide();
    return (T) this;
  }
  
  @Override
  public T toggleVisibility() {
    this.tip.toggleVisibility();
    return (T) this;
  }

  @Override
  public boolean visible() {
    return this.tip.visible();
  }

  protected void positioning() {
    int top = 0;
    int left = 0;
    
    this.tip.style().display(Display.BLOCK);
    
    int actualWidth = this.tip.outerWidth();
    Logger.get().debug("tip width: " + actualWidth);
    int actualHeight = this.tip.outerHeight();
    Logger.get().debug("tip height: " + actualHeight);
    
    Logger.get().debug("tip target: " + this.target.asWidget().id());
    Offset pos = this.target.asWidget().offset();
    Logger.get().debug("target top: " + pos.top());
    Logger.get().debug("target left: " + pos.left());
    int height = this.target.asWidget().offsetHeight();
    Logger.get().debug("target height: " + height);
    int width = this.target.asWidget().offsetWidth();
    Logger.get().debug("target width: " + width);

    if(Direction.BOTTOM.equals(this.direction)) {
        top = pos.top() + height;
        left = pos.left() + width / 2 - actualWidth / 2;
    } else if(Direction.TOP.equals(this.direction)){
        top = pos.top() - actualHeight;
        left = pos.left() + width / 2 - actualWidth / 2;
    } else if(Direction.LEFT.equals(this.direction)){
        top = pos.top() + height / 2 - actualHeight / 2;
        left = pos.left() - actualWidth;
    } else if(Direction.RIGHT.equals(this.direction)){
        top = pos.top() + height / 2 - actualHeight / 2;
        left = pos.left() + width;
    }
    this.tip.style().top(top, Unit.PX).left(left, Unit.PX);
  }
  
  public enum Trigger {
    CLICK {

      @Override
      <T extends Tip<T>> void register(UIWidget holder, final T tip) {
        holder.asWidget().on(new ClickHandler() {

          @Override
          public void onClick(ClickEvent event) {
            tip.toggleVisibility();
          }
        });
      }
    },
    HOVER {

      @Override
      <T extends Tip<T>> void register(UIWidget holder, final T tip) {
        holder.asWidget().on(new MouseOverHandler() {

          @Override
          public void onMouseOver(MouseOverEvent event) {
            tip.show();
          }
        });

        holder.asWidget().on(new MouseOutHandler() {

          @Override
          public void onMouseOut(MouseOutEvent event) {
            tip.hide();
          }
        });
      }
    },
    FOCUS {

      @Override
      <T extends Tip<T>> void register(UIWidget holder, final T tip) {
        holder.asWidget().on(new FocusHandler() {

          @Override
          public void onFocus(FocusEvent event) {
            tip.show();
          }
        });

        holder.asWidget().on(new BlurHandler() {

          @Override
          public void onBlur(BlurEvent event) {
            tip.hide();
          }
        });
      }
    },
    MANUAL {

      @Override
      <T extends Tip<T>> void register(UIWidget holder, final T tip) {
        return;
      }
    };

    abstract <T extends Tip<T>> void register(UIWidget holder, T tip);
  }
}