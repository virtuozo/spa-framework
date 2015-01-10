package virtuozo.infra;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.dom.client.KeyCodes;

public class Keyboard {
  private static final Keyboard instance = new Keyboard();

  private List<Integer> nonInputKeys = new ArrayList<Integer>();

  private Keyboard() {
    this.init();
  }

  private void init() {
    this.nonInputKeys.add(KeyCodes.KEY_ALT);
    this.nonInputKeys.add(KeyCodes.KEY_CTRL);
    this.nonInputKeys.add(KeyCodes.KEY_SHIFT);

    this.nonInputKeys.add(KeyCodes.KEY_LEFT);
    this.nonInputKeys.add(KeyCodes.KEY_RIGHT);
    this.nonInputKeys.add(KeyCodes.KEY_UP);
    this.nonInputKeys.add(KeyCodes.KEY_DOWN);

    this.nonInputKeys.add(KeyCodes.KEY_HOME);
    this.nonInputKeys.add(KeyCodes.KEY_END);
    this.nonInputKeys.add(KeyCodes.KEY_PAGEUP);
    this.nonInputKeys.add(KeyCodes.KEY_PAGEDOWN);
  }

  public static Keyboard get() {
    return Keyboard.instance;
  }

  public boolean altKey(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ALT;
  }

  public boolean backspace(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_BACKSPACE;
  }

  public boolean capslock(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_CAPS_LOCK;
  }
  
  public boolean contextMenu(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_CONTEXT_MENU;
  }

  public boolean control(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_CTRL;
  }

  public boolean delete(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_DELETE;
  }
  
  public boolean down(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_DOWN;
  }
  
  public boolean end(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_END;
  }

  public boolean enter(DomEvent<?> event) {
    int key = event.getNativeEvent().getKeyCode();
    return key == KeyCodes.KEY_ENTER || key == KeyCodes.KEY_MAC_ENTER;
  }
  
  public boolean erase(DomEvent<?> event){
    return this.backspace(event) || this.delete(event);
  }

  public boolean escape(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE;
  }
  
  public boolean f1(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F1;
  }
  
  public boolean f2(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F2;
  }
  
  public boolean f3(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F3;
  }
  
  public boolean f4(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F4;
  }
  
  public boolean f5(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F5;
  }
  
  public boolean f6(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F6;
  }
  
  public boolean f7(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F7;
  }
  
  public boolean f8(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F8;
  }
  
  public boolean f9(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F9;
  }
  
  public boolean f10(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F10;
  }
  
  public boolean f11(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F11;
  }
  
  public boolean f12(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_F12;
  }
  
  public boolean home(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_HOME;
  }
  
  public boolean insert(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_INSERT;
  }
  
  public boolean left(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_LEFT;
  }
  
  public boolean nonInputKey(DomEvent<?> event) {
    return this.nonInputKeys.contains(event.getNativeEvent().getKeyCode());
  }

  public boolean number(DomEvent<?> event) {
    Integer key = event.getNativeEvent().getKeyCode();

    return key == KeyCodes.KEY_NUM_ZERO || key == KeyCodes.KEY_NUM_ONE || key == KeyCodes.KEY_NUM_TWO || key == KeyCodes.KEY_NUM_THREE || key == KeyCodes.KEY_NUM_FOUR || key == KeyCodes.KEY_NUM_FIVE
        || key == KeyCodes.KEY_NUM_SIX || key == KeyCodes.KEY_NUM_SEVEN || key == KeyCodes.KEY_NUM_EIGHT || key == KeyCodes.KEY_NUM_NINE || key == KeyCodes.KEY_ZERO || key == KeyCodes.KEY_ONE
        || key == KeyCodes.KEY_TWO || key == KeyCodes.KEY_THREE || key == KeyCodes.KEY_FOUR || key == KeyCodes.KEY_FIVE || key == KeyCodes.KEY_SIX || key == KeyCodes.KEY_SEVEN
        || key == KeyCodes.KEY_EIGHT || key == KeyCodes.KEY_NINE;
  }
  
  public boolean pagedown(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_PAGEDOWN;
  }
  
  public boolean pageup(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_PAGEUP;
  }
  
  public boolean right(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_RIGHT;
  }
  
  public boolean scrollLock(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SCROLL_LOCK;
  }
  
  public boolean shift(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_SHIFT;
  }
  
  public boolean tab(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_TAB;
  }

  public boolean up(DomEvent<?> event) {
    return event.getNativeEvent().getKeyCode() == KeyCodes.KEY_UP;
  }
}