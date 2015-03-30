package virtuozo.ui;

import virtuozo.infra.Keyboard;
import virtuozo.ui.interfaces.HasKeyHandlers;

import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;

public class DateInputPrevent {
  public static DateInputPrevent create() {
    return new DateInputPrevent();
  }

  public DateInputPrevent() {
    super();
  }

  public DateInputPrevent attachTo(HasKeyHandlers<?> target) {
    target.onKeyPress(new KeyPressHandler() {

      @Override
      public void onKeyPress(KeyPressEvent event) {
        if (Keyboard.get().erase(event)) {
          return;
        }
        if (Keyboard.get().number(event)) {
          return;
        }
        if (event.getCharCode() == '/') {
          return;
        }
        if (event.getCharCode() == ':') {
          return;
        }
        event.preventDefault();
      }
    });

    return this;
  }
}