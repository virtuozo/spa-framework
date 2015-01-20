package virtuozo.ui;

import java.util.ArrayList;
import java.util.List;

import virtuozo.infra.Item;
import virtuozo.ui.interfaces.UIInput;

import com.google.gwt.event.dom.client.ChangeHandler;

public class MultiCheckbox extends Component<MultiCheckbox> implements UIInput<MultiCheckbox, List<String>> {
  private ChangeHandler handler;
  
  private Type type;
  
  public static MultiCheckbox create() {
    return new MultiCheckbox(Type.VERTICAL);
  }
  
  public static MultiCheckbox inline() {
    return new MultiCheckbox(Type.INLINE);
  }
  
  private MultiCheckbox(Type type) {
    super(Elements.div());
    this.type = type;
  }
  
  public MultiCheckbox addAll(Iterable<Item> items){
    for(Item item : items){
      this.add(item);
    }
    return this;
  }
  
  public MultiCheckbox add(Item item) {
    Checkbox input = type.create();
    
    if(this.handler != null){
      input.onChange(this.handler);
    }
    
    return this.addChild(input.text(item.value()).value(item.key()));
  }
  
  @Override
  public MultiCheckbox clear() {
    for(Checkbox child : this.children()) {
      child.clear();
    }
    return this;
  }

  @Override
  public MultiCheckbox value(List<String> value) {
    for(Checkbox child : this.children()) {
      if(value.contains(child.value())){
        child.check();
        continue;
      }
      child.uncheck();
    }
    return this;
  }

  @Override
  public List<String> value() {
    List<String> values = new ArrayList<String>();
    for(Checkbox child : this.children()) {
      if(child.checked()){
        values.add(child.value());
      }
    }
    return values;
  }
  
  @Override
  public MultiCheckbox enable() {
    for(Checkbox child : this.children()) {
      child.enable();
    }
    return this;
  }

  @Override
  public MultiCheckbox disable() {
    for(Checkbox child : this.children()) {
      child.disable();
    }
    return this;
  }

  @Override
  public boolean disabled() {
    for(Checkbox child : this.children()) {
      if(child.disabled()) {
        return true;
      }
    }
    return false;
  }

  @Override
  public MultiCheckbox onChange(ChangeHandler handler) {
    this.handler = handler;
    for(Checkbox child : this.children()) {
      child.onChange(this.handler);
    }    
    return this;
  }
  
  @Override
  public MultiCheckbox tabIndex(int index) {
    for(Checkbox child : this.children()){
      child.tabIndex(index);
    }
    return this;
  }
  
  protected Iterable<Checkbox> children() {
    return this.<Checkbox>childrenComponents();
  }
  
  enum Type{
    INLINE{
      @Override
      Checkbox create() {
        return Checkbox.inline();
      }
    }, VERTICAL{
      @Override
      Checkbox create() {
        return Checkbox.create();
      }
    };
    
    abstract Checkbox create();
  }
}