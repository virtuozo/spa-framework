package virtuozo.infra.events;

import virtuozo.infra.events.TextChangeEvent.TextChangeHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class TextChangeEvent extends GwtEvent<TextChangeHandler> {
  public static final Type<TextChangeHandler> TYPE = new Type<TextChangeHandler>();
  
  private String text;
  
  @Override
  public Type<TextChangeHandler> getAssociatedType() {
    return TYPE;
  }
  
  public TextChangeEvent(String text) {
    super();
    this.text = text;
  }
  
  public String text() {
    return text;
  }

  @Override
  protected void dispatch(TextChangeHandler handler) {
    handler.onChange(this);
  }
  
  public static interface TextChangeHandler extends EventHandler {
    void onChange(TextChangeEvent event);
  }

}
