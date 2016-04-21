package virtuozo.infra;

import com.google.gwt.dom.client.Element;

public class NativeAnimationRegistry {

  public static native void registerAnimationHandler(final String key, final Element element, final AnimationHandler handler)/*-{
		var callback = function() {
			handler.@virtuozo.infra.AnimationHandler::onAnimate()();
		}
		element.addEventListener(key, callback, false);
  }-*/;
}