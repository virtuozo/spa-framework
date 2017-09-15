package virtuozo.interfaces;

import virtuozo.infra.AnimationHandler;
import virtuozo.infra.AnimationRegistry;
import virtuozo.infra.Async;
import virtuozo.infra.ScrollSpy;
import virtuozo.infra.events.ScrollSpyEvent;
import virtuozo.infra.events.ScrollSpyEvent.ScrollSpyHandler;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Visibility;

public enum Animate {
  bounce,
  bounceIn,
  bounceInDown,
  bounceInLeft,
  bounceInRight,
  bounceInUp,
  bounceOut,
  bounceOutDown,
  bounceOutLeft,
  bounceOutRight,
  bounceOutUp,

  fadeIn,
  fadeInDown,
  fadeInDownBig,
  fadeInLeft,
  fadeInLeftBig,
  fadeInRight,
  fadeInRightBig,
  fadeInUp,
  fadeInUpBig,
  fadeOut,
  fadeOutDown,
  fadeOutDownBig,
  fadeOutLeft,
  fadeOutLeftBig,
  fadeOutRight,
  fadeOutRightBig,
  fadeOutUp,
  fadeOutUpBig,

  flash,

  flip,
  flipInX,
  flipInY,
  flipOutX,
  flipOutY,

  hinge,

  jello,

  lightSpeedIn,
  lightSpeedOut,

  pulse,

  rollIn,
  rollOut,

  rotateIn,
  rotateInDownLeft,
  rotateInDownRight,
  rotateInUpLeft,
  rotateInUpRight,
  rotateOut,
  rotateOutDownLeft,
  rotateOutDownRight,
  rotateOutUpLeft,
  rotateOutUpRight,
  
  rubberBand,

  shake,

  slideInUp,
  slideInDown,
  slideInLeft,
  slideInRight,
  slideOutUp,
  slideOutDown,
  slideOutLeft,
  slideOutRight,

  swing,
  tada,
  wobble,

  zoomIn,
  zoomInDown,
  zoomInLeft,
  zoomInRight,
  zoomInUp,
  zoomOut,
  zoomOutDown,
  zoomOutLeft,
  zoomOutRight,
  zoomOutUp;
  
  
  private static final String ANIMATED_CLASS = "animated";
  
  private static final String INFINITE_CLASS = "infinite";
  
  public AnimationTrigger execute(final Component<?> component){
    this.show(component);
    AnimationTrigger trigger = new AnimationTrigger(component.element()).onEnd(new AnimationHandler(){
      @Override
      public void onAnimate() {
        clear(component);
      }
    });
    component.css().append(ANIMATED_CLASS).append(this.name());
    
    return trigger;
  }
  
  public void loop(Component<?> component){
    component.css().append(ANIMATED_CLASS).append(INFINITE_CLASS).append(this.name());
  }
  
  public void reveal(final Component<?> component, int delayMs){
    this.hide(component);
    Async.get().execute(new Runnable() {
      @Override
      public void run() {
        execute(component.show());
      }
    }, delayMs);
  }
  
  public void reveal(final Component<?> component){
    final ScrollSpy spy = ScrollSpy.get();
    
    this.hide(component);
    spy.spy(component, new ScrollSpyHandler() {
      @Override
      public void onScroll(ScrollSpyEvent event) {
        if(event.visible()){
          execute(component);
          spy.unspy(event.index());
        }
      }
    });
  }
  
  private void clear(Component<?> component){
    component.css().remove(ANIMATED_CLASS);
    component.css().remove(this.name());
  }
  
  private void show(Component<?> component){
    component.style().visibility(Visibility.VISIBLE);
  }
  
  private void hide(Component<?> component){
    component.style().visibility(Visibility.HIDDEN);
  }
  
  public class AnimationTrigger {
    private Element element;
    
    private AnimationRegistry registry = GWT.create(AnimationRegistry.class);
    
    public AnimationTrigger(Element element) {
      this.element = element;
    }

    public AnimationTrigger onStart(AnimationHandler handler){
      this.registry.onStart(this.element, handler);
      return this;
    }
    
    public AnimationTrigger onIterate(AnimationHandler handler){
      this.registry.onIterate(this.element, handler);
      return this;
    }
    
    public AnimationTrigger onEnd(AnimationHandler handler){
      this.registry.onEnd(this.element, handler);
      return this;
    }
  }
}