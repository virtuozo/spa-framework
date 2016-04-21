package virtuozo.interfaces;

import virtuozo.infra.Keyboard;
import virtuozo.infra.handlers.HasKeyHandlers;

import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;


public class NumberInputPrevent {
  public static NumberInputPrevent create(){
    return new NumberInputPrevent();
  }
  
  private NumberInputPrevent() {
    super();
  }
  
  public NumberInputPrevent attachTo(HasKeyHandlers<?> target){
    target.onKeyPress(new KeyPressHandler() {
      
      @Override
      public void onKeyPress(KeyPressEvent event) {
        if (Keyboard.get().erase(event)) {
          return;
        }
        
        if (Keyboard.get().number(event)) {
          return;
        }
        
        if(event.getCharCode() == '-'){
          return;
        }

        event.preventDefault();
      }
    });
    return this;
  }
}