package hitz.virtuozo.infra;

import com.google.gwt.event.shared.EventHandler;

public interface SubscriptionCallback<S> extends EventHandler {
  void onPublish(S subject);
}