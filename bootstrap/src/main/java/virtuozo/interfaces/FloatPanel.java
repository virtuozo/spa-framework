package virtuozo.interfaces;

import virtuozo.infra.events.HideEvent;
import virtuozo.infra.events.ShowEvent;
import virtuozo.infra.events.HideEvent.HideHandler;
import virtuozo.infra.events.ShowEvent.ShowHandler;
import virtuozo.infra.events.ToggleEvent.ToggleHandler;
import virtuozo.infra.handlers.DetachHandler;
import virtuozo.infra.handlers.HasMouseHandlers;
import virtuozo.interfaces.HTML;
import virtuozo.interfaces.Tag;
import virtuozo.interfaces.css.Direction;

import com.google.gwt.dom.client.DivElement;
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
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.ui.Offset;

@SuppressWarnings("unchecked")
public abstract class FloatPanel<T extends FloatPanel<T>> implements HasMouseHandlers<T>, HasVisibility<T> {

  private Tag<DivElement> tip = Tag.asDiv();

  private UIComponent target;

  private Direction direction;

  protected FloatPanel() {
    HTML.body().add(this.tip.hide());

    this.tip.onShow(new ShowHandler() {

      @Override
      public void onShow(ShowEvent event) {
        FloatPanel.this.tip.css("in");
        FloatPanel.this.positioning();
      }
    }).onHide(new HideHandler() {

      @Override
      public void onHide(HideEvent event) {
        FloatPanel.this.tip.css().remove("in");
      }
    });
  }

  protected T add(UIComponent widget) {
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

  public T trigger(UIComponent holder, Trigger... triggers) {
    this.target = holder;
    this.target.asComponent().onDetach(new DetachHandler() {
      
      @Override
      protected void onDetach(AttachEvent event) {
        FloatPanel.this.tip.detach();
      }
    });
    
    if(triggers == null){
      Trigger.MANUAL.register(holder, (T) this);
      return (T) this;
    }
    
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
    double top = 0;
    double left = 0;

    double actualWidth = this.tip.measurement().outerWidth();
    double actualHeight = this.tip.measurement().outerHeight();

    Offset pos = this.target.asComponent().offset();
    double height = this.target.asComponent().measurement().outerHeight();
    double width = this.target.asComponent().measurement().outerWidth();

    if (Direction.BOTTOM.equals(this.direction)) {
      top = pos.top() + height;
      left = pos.left() + width / 2 - actualWidth / 2;
      return new Offset(left, top);
    } 
    if (Direction.TOP.equals(this.direction)) {
      top = pos.top() - actualHeight;
      left = pos.left() + width / 2 - actualWidth / 2;
      return new Offset(left, top);
    } 
    if (Direction.LEFT.equals(this.direction)) {
      top = pos.top() + height / 2 - actualHeight / 2;
      left = pos.left() - actualWidth;
      return new Offset(left, top);
    }
    
    top = pos.top() + height / 2 - actualHeight / 2;
    left = pos.left() + width;
    return new Offset(left, top);
  }

  public enum Trigger {
    CLICK {

      @Override
      <T extends FloatPanel<T>> void register(UIComponent holder, final T tip) {
        holder.asComponent().on(new ClickHandler() {

          @Override
          public void onClick(ClickEvent event) {
            tip.toggleVisibility();
          }
        });
      }
    },
    HOVER {

      @Override
      <T extends FloatPanel<T>> void register(UIComponent holder, final T tip) {
        holder.asComponent().on(new MouseOverHandler() {

          @Override
          public void onMouseOver(MouseOverEvent event) {
            tip.show();
          }
        });

        holder.asComponent().on(new MouseOutHandler() {

          @Override
          public void onMouseOut(MouseOutEvent event) {
            tip.hide();
          }
        });
      }
    },
    FOCUS {

      @Override
      <T extends FloatPanel<T>> void register(UIComponent holder, final T tip) {
        holder.asComponent().on(new FocusHandler() {

          @Override
          public void onFocus(FocusEvent event) {
            tip.show();
          }
        });

        holder.asComponent().on(new BlurHandler() {

          @Override
          public void onBlur(BlurEvent event) {
            tip.hide();
          }
        });
      }
    },
    MANUAL {

      @Override
      <T extends FloatPanel<T>> void register(UIComponent holder, final T tip) {
        return;
      }
    };

    abstract <T extends FloatPanel<T>> void register(UIComponent holder, T tip);
  }
}