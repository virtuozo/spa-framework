package virtuozo.ui.events;

import virtuozo.infra.Keyboard;

import com.google.gwt.event.dom.client.KeyCodeEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;

public class MaskHandler implements KeyDownHandler{

  private MaskValidator validator;
  
  public MaskHandler(MaskValidator validator) {
    this.validator = validator;
  }

  @Override
  public void onKeyDown(KeyDownEvent event) {
    if(Keyboard.get().nonInputKey(event) || Keyboard.get().backspace(event) || Keyboard.get().delete(event)) {
      return;
    }
    
    if(this.validator.accept(event)){
      return;
    }
    
    event.preventDefault();
  }
  
  public static interface MaskValidator {
    boolean accept(KeyCodeEvent<?> event);
  }

}
