package virtuozo.infra;

import virtuozo.ui.api.HasComponents;

public abstract class Presenter<V extends View> {

  private V view;
  
  public Presenter(V view) {
    this.view = view;
  }

  public V view(){
    return this.view;
  }

  public final void go(HasComponents<?, ?> container){
    this.bind();
    this.view.render(container.detachChildren());
  }
  
  final void detach(){
    this.view.detach();
    this.unbind();
  } 
  
  protected void bind(){}
  
  protected void unbind(){}
  
}