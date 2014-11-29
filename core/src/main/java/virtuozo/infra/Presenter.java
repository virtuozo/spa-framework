package virtuozo.infra;

import virtuozo.ui.api.DetachChildrenEvent;
import virtuozo.ui.api.HasComponents;
import virtuozo.ui.api.DetachChildrenEvent.DetachChildrenHandler;

public abstract class Presenter<V extends View> {

  private V view;
  
  private DetachChildrenHandler handler = new DetachChildrenHandler(){
    @Override
    public void onDetachChildren(DetachChildrenEvent event) {
      Presenter.this.unbind();
    }
  };
  
  public Presenter(V view) {
    this.view = view;
  }

  public V view(){
    return this.view;
  }

  public final void go(HasComponents<?, ?> container){
    this.bind();
    this.view.render(container.detachChildren());
    container.onDetachChildren(this.handler);
  }
  
  protected void bind(){}
  
  protected void unbind(){}
  
}