package hitz.virtuozo.infra;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.KeyCodes;

public class Keyboard {
  private static final Keyboard instance = new Keyboard();
  
  private final List<Integer> controlKeys = new ArrayList<Integer>();
  
  private Keyboard() { 
    this.init();
  }
  
  private void init(){
   this.controlKeys.add(KeyCodes.KEY_ALT);
   this.controlKeys.add(KeyCodes.KEY_CTRL);
   this.controlKeys.add(KeyCodes.KEY_BACKSPACE);
   this.controlKeys.add(KeyCodes.KEY_DELETE);
   this.controlKeys.add(KeyCodes.KEY_LEFT);
   this.controlKeys.add(KeyCodes.KEY_RIGHT);
   this.controlKeys.add(KeyCodes.KEY_SHIFT);
   this.controlKeys.add(KeyCodes.KEY_TAB);
   this.controlKeys.add(KeyCodes.KEY_ENTER);
   this.controlKeys.add(KeyCodes.KEY_UP);
   this.controlKeys.add(KeyCodes.KEY_DOWN);
   this.controlKeys.add(KeyCodes.KEY_LEFT);
   this.controlKeys.add(KeyCodes.KEY_RIGHT);
   this.controlKeys.add(KeyCodes.KEY_PAGEDOWN);
   this.controlKeys.add(KeyCodes.KEY_PAGEUP);
   this.controlKeys.add(KeyCodes.KEY_HOME);
   this.controlKeys.add(KeyCodes.KEY_END);
   this.controlKeys.add(KeyCodes.KEY_ESCAPE);
  }
  
  public static Keyboard get() {
    return Keyboard.instance;
  }
  
  public boolean controlKey(Integer key){
    return this.controlKeys.contains(key);
  }
}