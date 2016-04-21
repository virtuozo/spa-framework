package virtuozo.infra;

import com.google.gwt.dom.client.Element;

public interface AnimationRegistry {
  void onStart(Element element, AnimationHandler handler);
  
  void onIterate(Element element, AnimationHandler handler);
  
  void onEnd(Element element, AnimationHandler handler);
}