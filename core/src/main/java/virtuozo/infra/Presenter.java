package virtuozo.infra;

import virtuozo.interfaces.HasComponents;

public abstract class Presenter<V extends View> {

  private V view;
  
  protected Presenter(V view) {
    this.view = view;
    view.bind();
    this.setup();
  }
  
  protected void setup(){
    return;
  }

  public V view(){
    return this.view;
  }

  public final void go(HasComponents<?, ?> container){
    this.bind();
    this.view.render(container.detachChildren());
  }
  
  public final void detach(){
    this.view.unbind();
    this.unbind();
  } 
  
  protected void bind(){}
  
  protected void unbind(){}
  
}