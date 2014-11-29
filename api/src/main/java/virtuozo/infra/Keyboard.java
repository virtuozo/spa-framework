package virtuozo.infra;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.KeyCodeEvent;
import com.google.gwt.event.dom.client.KeyCodes;

public class Keyboard {
  private static final Keyboard instance = new Keyboard();
  
  private List<Integer> nonInputKeys = new ArrayList<Integer>();
  
  private Keyboard() { 
    this.init();
  }
  
  private void init(){
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

  public boolean backspace(KeyCodeEvent<?> event){
    return this.backspace(event.getNativeKeyCode());
  }
  
  public boolean backspace(Integer key){
    return key == KeyCodes.KEY_BACKSPACE;
  }
  
  public boolean nonInputKey(KeyCodeEvent<?> event){
    return this.controlKey(event.getNativeKeyCode());
  }

  public boolean controlKey(Integer key){
    return this.nonInputKeys.contains(key);
  }
  
  public boolean delete(KeyCodeEvent<?> event){
    return this.delete(event.getNativeKeyCode());
  }
  
  public boolean delete(Integer key){
    return key == KeyCodes.KEY_DELETE;
  }
  
  public boolean enter(KeyCodeEvent<?> event){
    return this.enter(event.getNativeKeyCode());
  }
  
  public boolean enter(Integer key){
    return key == KeyCodes.KEY_ENTER;
  }
  
  public boolean escape(KeyCodeEvent<?> event){
    return this.escape(event.getNativeKeyCode());
  }
  
  public boolean escape(Integer key){
    return key == KeyCodes.KEY_ESCAPE;
  }
  
  public boolean tab(KeyCodeEvent<?> event){
    return this.tab(event.getNativeKeyCode());
  }
  
  public boolean tab(Integer key){
    return key == KeyCodes.KEY_TAB;
  }
}