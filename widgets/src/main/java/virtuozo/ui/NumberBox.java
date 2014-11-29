package virtuozo.ui;

import virtuozo.infra.Keyboard;
import virtuozo.ui.InputGroup;
import virtuozo.ui.InputText;

import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;

public final class NumberBox extends InputGroup {

  public NumberBox() {
    super(new InputText());
    this.onKeyPress(new KeyPressHandler() {

      @Override
      public void onKeyPress(KeyPressEvent event) {
        int keyCode = event.getNativeEvent().getKeyCode();
        if (Keyboard.get().controlKey(keyCode)) {
          return;
        }

        if (!"-0123456789".contains(String.valueOf(event.getCharCode()))) {
          event.preventDefault();
        }
      }
    });
  }
  }