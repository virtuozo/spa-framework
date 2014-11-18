package hitz.virtuozo.infra;

import hitz.virtuozo.ui.Composite;
import hitz.virtuozo.ui.api.DetachHandler;
import hitz.virtuozo.ui.api.UIComponent;

import com.google.gwt.event.logical.shared.AttachEvent;

public abstract class Presenter<V extends View> {

  private V view;
  
  public Presenter(V view) {
    this.view = view;
  }

  public V view(){
    return this.view;
  }

  public final void go(Composite<?> container){
    this.bind();
    UIComponent renderedView = this.view.render();
    container.detachChildren().add(renderedView);
    renderedView.asComponent().onDetach(new DetachHandler() {
      @Override
      protected void onDetach(AttachEvent event) {
        Presenter.this.unbind();
      }
    });
  }
  
  protected void bind(){}
  
  protected void unbind(){}
}