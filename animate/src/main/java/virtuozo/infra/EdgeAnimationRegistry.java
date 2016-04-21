package virtuozo.infra;

import com.google.gwt.dom.client.Element;

public class EdgeAnimationRegistry implements AnimationRegistry {

  @Override
  public void onStart(Element element, AnimationHandler handler) {
    NativeAnimationRegistry.registerAnimationHandler("MSAnimationStart", element, handler);
  }

  @Override
  public void onIterate(Element element, AnimationHandler handler) {
    NativeAnimationRegistry.registerAnimationHandler("MSAnimationIteration", element, handler);
  }

  @Override
  public void onEnd(Element element, AnimationHandler handler) {
    NativeAnimationRegistry.registerAnimationHandler("MSAnimationEnd", element, handler);
  }
}