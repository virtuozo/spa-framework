package virtuozo.infra;

import com.google.gwt.dom.client.Element;

public class W3cAnimationRegistry implements AnimationRegistry {

  @Override
  public void onStart(Element element, AnimationHandler handler) {
    NativeAnimationRegistry.registerAnimationHandler("animationstart", element, handler);
  }

  @Override
  public void onIterate(Element element, AnimationHandler handler) {
    NativeAnimationRegistry.registerAnimationHandler("animationiteration", element, handler);
  }

  @Override
  public void onEnd(Element element, AnimationHandler handler) {
    NativeAnimationRegistry.registerAnimationHandler("animationend", element, handler);
  }
}