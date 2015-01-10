package virtuozo.ui;

import java.util.Date;

import virtuozo.infra.DateFormat;
import virtuozo.infra.Keyboard;
import virtuozo.infra.Logger;
import virtuozo.ui.events.MaskHandler;
import virtuozo.ui.events.MaskHandler.MaskValidator;
import virtuozo.ui.interfaces.HasKeyHandlers;

import com.google.gwt.event.dom.client.KeyCodeEvent;

public class DateMask {
  private DateFormat format;

  private DateMask(DateFormat format) {
    super();
    this.format = format;
  }
  
  public static DateMask create(DateFormat format){
    return new DateMask(format);
  }
  
  public DateMask attachTo(HasKeyHandlers<?> target){
    final String pattern = this.format.pattern();
    
    MaskValidator validator = new MaskValidator() {
      
      @Override
      public boolean accept(KeyCodeEvent<?> event) {
        if(Keyboard.get().number(event)){
          Logger.get().error(pattern);
          return true;
        }
        
        return false;
      }
    };
    
    target.onKeyDown(new MaskHandler(validator));
    
    return this;
  }
  
  public String apply(Date value){
    return this.format.format(value);
  }
}