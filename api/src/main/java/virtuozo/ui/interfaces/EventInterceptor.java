package virtuozo.ui.interfaces;

import com.google.gwt.user.client.Event;

public interface EventInterceptor {
  boolean shouldFire(Event event);
}
