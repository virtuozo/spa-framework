package hitz.virtuozo.application;

import hitz.virtuozo.application.api.View;
import hitz.virtuozo.ui.Composite;
import hitz.virtuozo.ui.api.UIWidget;

public abstract class Presenter<V extends View> {

  private V view;
  
  public Presenter(V view) {
    this.view = view;
  }

  public V view(){
    return this.view;
  }

  final void go(Composite<?> container){
    this.bind();
    UIWidget renderedView = this.view.render();
    container.detachChildren().add(renderedView);
  }
  
  public abstract void bind();
  
  public abstract void unbind();
  
  protected abstract Place place();
}