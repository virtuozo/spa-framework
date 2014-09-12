package hitz.virtuozo.ui.fx;

import hitz.virtuozo.infra.EventBus;
import hitz.virtuozo.infra.api.EventHandler;
import hitz.virtuozo.infra.api.EventType;
import hitz.virtuozo.ui.Event;
import hitz.virtuozo.ui.Style;
import hitz.virtuozo.ui.api.UIWidget;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.core.shared.GWT;

public abstract class Effect<E extends Effect<E>> {
  private UIWidget target;
  
  private EventBus bus = new EventBus();
  
  private Animation fx = new Animation() {
    
    @Override
    protected void onUpdate(double progress) {
      GWT.log("Effect progress: " + progress);
      Effect.this.onUpdate(progress);
    }
    
    protected void onCancel() {
      Effect.this.onCancel();
      Effect.this.bus.fire(new Event<Void>(Events.CANCEL, Effect.this.target));
    }
    
    protected void onComplete() {
      Effect.this.onComplete();
      Effect.this.bus.fire(new Event<Void>(Events.COMPLETE, Effect.this.target));
    }
    
    protected void onStart() {
      Effect.this.onStart();
      Effect.this.bus.fire(new Event<Void>(Events.START, Effect.this.target));
    }
  };
  
  public Effect(UIWidget target) {
    super();
    this.target = target;
  }
  
  public E onCancel(EventHandler<Void> handler){
    this.bus.add(Events.CANCEL, handler);
    return (E) this;
  }
  
  public E onComplete(EventHandler<Void> handler){
    this.bus.add(Events.COMPLETE, handler);
    return (E) this;
  }
  
  public E onStart(EventHandler<Void> handler){
    this.bus.add(Events.START, handler);
    return (E) this;
  }
  
  public E run(int duration){
    this.fx.run(duration);
    return (E) this;
  }
  
  protected Style style(){
    return this.target.asWidget().style();
  }

  protected UIWidget target() {
    return target;
  }
  
  protected E onCancel(){
    return (E) this;
  }
  
  protected E onComplete(){
    return (E) this;
  }
  
  protected E onStart(){
    return (E) this;
  }
  
  protected abstract E onUpdate(double progress);
  
  static enum Events implements EventType{
    CANCEL, COMPLETE, START;
  }
}