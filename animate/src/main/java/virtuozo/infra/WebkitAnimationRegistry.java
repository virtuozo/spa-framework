package virtuozo.infra;

import com.google.gwt.dom.client.Element;

public class WebkitAnimationRegistry implements AnimationRegistry {

  @Override
  public void onStart(Element element, AnimationHandler handler) {
    NativeAnimationRegistry.registerAnimationHandler("webkitAnimationStart", element, handler);
  } 

  @Override
  public void onIterate(Element element, AnimationHandler handler) {
    NativeAnimationRegistry.registerAnimationHandler("webkitAnimationIteration", element, handler);
  }

  @Override
  public void onEnd(Element element, AnimationHandler handler) {
    NativeAnimationRegistry.registerAnimationHandler("webkitAnimationEnd", element, handler);
  }
}