package virtuozo.infra.data;

import virtuozo.infra.handlers.ValueChangeHandler;

public class ValueBinding<T> implements DataBinding<T> {
  private T value;

  private ValueChangeHandler<T> handler;
  
  public ValueBinding() {
    super();
  }
  
  public ValueBinding(T value) {
    this.value = value;
  }

  @Override
  public T get() {
    return this.value;
  }

  @Override
  public T get(T defaultValue) {
    if(this.value == null){
      return defaultValue;
    }
    
    return this.value;
  }

  @Override
  public void set(T value) {
    this.handle(this.value, value);
    this.value = value;
  }
  
  public void onChange(ValueChangeHandler<T> handler){
    this.handler = handler;
  }
  
  private void handle(T oldValue, T newValue){
    if(this.handler !=null){
      this.handler.onChange(oldValue, newValue);
    }
  }
}