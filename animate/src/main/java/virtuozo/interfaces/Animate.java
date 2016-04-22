package virtuozo.interfaces;

import virtuozo.infra.AnimationHandler;
import virtuozo.infra.AnimationRegistry;
import virtuozo.infra.Async;
import virtuozo.infra.ScrollSpy;
import virtuozo.infra.events.ScrollSpyEvent;
import virtuozo.infra.events.ScrollSpyEvent.ScrollSpyHandler;
import virtuozo.infra.handlers.AttachHandler;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.AttachEvent;

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
  
  private AnimationRegistry registry = GWT.create(AnimationRegistry.class);
  
  private static final String ANIMATED_CLASS = "animated";
  
  private static final String INFINITE_CLASS = "infinite";
  
  public void execute(final Component<?> component){
    registry.onEnd(component.element(), new AnimationHandler(){
      @Override
      public void onAnimate() {
        clear(component);
      }
    });
    
    component.css().append(ANIMATED_CLASS).append(this.name());
  }
  
  public void loop(Component<?> component){
    component.css().append(ANIMATED_CLASS).append(INFINITE_CLASS).append(this.name());
  }
  
  public void reveal(final Component<?> component){
    final ScrollSpy spy = ScrollSpy.create();
    
    spy.spy(component, new ScrollSpyHandler() {
      @Override
      public void onScroll(ScrollSpyEvent event) {
        if(event.isInRange()){
          revealNow(component, spy);
        }
      }
    });

    component.onAttach(new AttachHandler() {
      @Override
      protected void onAttach(AttachEvent event) {
        Async.get().execute(new Runnable(){
          @Override
          public void run() {
            revealNow(component, spy);
          }
        });
      }
    });
  }
  
  private void revealNow(final Component<?> component, final ScrollSpy spy) {
    execute(component);
    spy.unspy(component);
  }
  
  private void clear(Component<?> component){
    component.css().remove(ANIMATED_CLASS);
    component.css().remove(this.name());
  }
}