package virtuozo.interfaces;

import virtuozo.infra.AnimationHandler;
import virtuozo.infra.AnimationRegistry;

import com.google.gwt.core.shared.GWT;

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
  
  private static final String standardClass = "animated";
  
  public void execute(final Component<?> component){
    registry.onEnd(component.element(), new AnimationHandler(){
      @Override
      public void onAnimate() {
        clear(component);
      }
    });
    
    component.css().append(standardClass).append(this.name());
  }
  
  private void clear(Component<?> component){
    component.css().remove(standardClass);
    component.css().remove(this.name());
  }
}