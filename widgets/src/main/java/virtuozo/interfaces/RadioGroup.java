package virtuozo.interfaces;

import virtuozo.infra.Elements;
import virtuozo.infra.Item;
import virtuozo.infra.events.CssChangeEvent;
import virtuozo.infra.events.CssChangeEvent.CssChangeHandler;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.RadioButton;
import virtuozo.interfaces.UIInput;

import com.google.gwt.event.dom.client.ChangeHandler;

public class RadioGroup extends Component<RadioGroup> implements UIInput<RadioGroup, String> {
  private ChangeHandler handler;
  
  private Type type;
  
  private String name;
  
  public static RadioGroup create(String name) {
    return new RadioGroup(name, Type.VERTICAL);
  }
  
  public static RadioGroup inline(String name) {
    return new RadioGroup(name, Type.INLINE);
  }
  
  private RadioGroup(String name, Type type) {
    super(Elements.div());
    this.name = name;
    this.type = type;
    this.onCssChange(new CssChangeHandler() {
      @Override
      public void onChange(CssChangeEvent e) {
        String name = "form-control";
        if (css().contains(name)) {
          css().remove(name);
        }
      }
    });
  }
  
  public RadioGroup addAll(Iterable<Item> items){
    for(Item item : items){
      this.add(item);
    }
    return this;
  }
  
  public RadioGroup add(Item item) {
    RadioButton input = type.create(this.name);
    
    if(this.handler != null){
      input.onChange(this.handler);
    }
    
    return this.addChild(input.text(item.value()).value(item.key()));
  }
  
  @Override
  public RadioGroup value(String value) {
    for(RadioButton child : this.children()){
      if(value.equals(child.value())){
        child.check();
        break;
      }
    }
    return this;
  }

  @Override
  public String value() {
    for(RadioButton child : this.children()){
      if(child.checked()){
        return child.value();
      }
    }
    return null;
  }

  @Override
  public RadioGroup onChange(ChangeHandler handler) {
    this.handler = handler;
    for(RadioButton child : this.children()){
      child.onChange(handler);
    }
    return this;
  }

  @Override
  public RadioGroup enable() {
    for(RadioButton child : this.children()){
      child.enable();
    }
    return this;
  }

  @Override
  public RadioGroup disable() {
    for(RadioButton child : this.children()){
      child.disable();
    }
    return this;
  }

  @Override
  public boolean disabled() {
    for(RadioButton child : this.children()){
      return child.disabled();
    }
    return false;
  }

  @Override
  public RadioGroup clear() {
    for(RadioButton child : this.children()){
      child.clear();
    }
    return this;
  }

  @Override
  public RadioGroup tabIndex(int index) {
    for(RadioButton child : this.children()){
      child.tabIndex(index);
    }
    return this;
  }
  
  protected Iterable<RadioButton> children() {
    return this.<RadioButton>childrenComponents();
  }
  
  enum Type{
    INLINE{
      @Override
      RadioButton create(String name) {
        return RadioButton.inline(name);
      }
    }, VERTICAL{
      @Override
      RadioButton create(String name) {
        return RadioButton.create(name);
      }
    };
    
    abstract RadioButton create(String name);
  }
}