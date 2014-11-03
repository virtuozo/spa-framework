package hitz.virtuozo.ui;

import hitz.virtuozo.infra.api.HasMouseHandlers;
import hitz.virtuozo.infra.api.HasVisibility;
import hitz.virtuozo.infra.api.HideEvent;
import hitz.virtuozo.infra.api.HideEvent.HideHandler;
import hitz.virtuozo.infra.api.ShowEvent;
import hitz.virtuozo.infra.api.ShowEvent.ShowHandler;
import hitz.virtuozo.infra.api.ToggleEvent.ToggleHandler;
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
abstract class FloatPanel<T extends FloatPanel<T>> implements HasMouseHandlers<T>, HasVisibility<T> {

  private Tag<DivElement> tip = Tag.asDiv();

  private UIWidget target;

  private Direction direction;

  public FloatPanel() {
    HTML.body().add(this.tip.hide());

    this.tip.onShow(new ShowHandler() {

      @Override
      public void onShow(ShowEvent event) {
        FloatPanel.this.tip.css("in").style().display(Display.BLOCK);
        FloatPanel.this.positioning();
      }
    }).onHide(new HideHandler() {

      @Override
      public void onHide(HideEvent event) {
        FloatPanel.this.tip.css().remove("in");
        FloatPanel.this.tip.style().clearDisplay();
      }
    });
  }

  protected T add(UIWidget widget) {
    this.tip.add(widget);
    return (T) this;
  }

  protected T css(String... classes) {
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
  public T onHide(HideHandler handler) {
    this.tip.onHide(handler);
    return (T) this;
  }

  @Override
  public T onShow(ShowHandler handler) {
    this.tip.onShow(handler);
    return (T) this;
  }

  @Override
  public T onToggleVisibility(ToggleHandler handler) {
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
    Offset position = this.offset();
    this.tip.style().top(position.top(), Unit.PX).left(position.left(), Unit.PX);
  }

  private Offset offset() {
    int top = 0;
    int left = 0;

    int actualWidth = this.tip.outerWidth();
    int actualHeight = this.tip.outerHeight();

    Offset pos = this.target.asWidget().offset();
    int height = this.target.asWidget().offsetHeight();
    int width = this.target.asWidget().offsetWidth();

    if (Direction.BOTTOM.equals(this.direction)) {
      top = pos.top() + height;
      left = pos.left() + width / 2 - actualWidth / 2;
      return new Offset(top, left);
    } 
    if (Direction.TOP.equals(this.direction)) {
      top = pos.top() - actualHeight;
      left = pos.left() + width / 2 - actualWidth / 2;
      return new Offset(top, left);
    } 
    if (Direction.LEFT.equals(this.direction)) {
      top = pos.top() + height / 2 - actualHeight / 2;
      left = pos.left() - actualWidth;
      return new Offset(top, left);
    }
    
    top = pos.top() + height / 2 - actualHeight / 2;
    left = pos.left() + width;
    return new Offset(top, left);
  }

  public enum Trigger {
    CLICK {

      @Override
      <T extends FloatPanel<T>> void register(UIWidget holder, final T tip) {
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
      <T extends FloatPanel<T>> void register(UIWidget holder, final T tip) {
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
      <T extends FloatPanel<T>> void register(UIWidget holder, final T tip) {
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
      <T extends FloatPanel<T>> void register(UIWidget holder, final T tip) {
        return;
      }
    };

    abstract <T extends FloatPanel<T>> void register(UIWidget holder, T tip);
  }
}