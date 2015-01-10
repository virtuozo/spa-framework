package virtuozo.ui;

import virtuozo.infra.Keyboard;

import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;

public final class InputNumber extends InputGroup {

  public static InputNumber create(){
    return new InputNumber();
  }
  
  private InputNumber() {
    super(InputText.create());
    this.onKeyPress(new KeyPressHandler() {

      @Override
      public void onKeyPress(KeyPressEvent event) {
        if (Keyboard.get().nonInputKey(event) || Keyboard.get().number(event) || "-".equals(String.valueOf(event.getCharCode()))) {
          return;
        }

        event.preventDefault();
      }
    });
  }
}