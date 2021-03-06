package virtuozo.infra.events;

import com.google.gwt.event.shared.GwtEvent.Type;

public class Subscriber<S> {
  private Type<SubscriptionCallback> type;

  Subscriber(Type<SubscriptionCallback> type) {
    super();
    this.type = type;
  }

  public void to(SubscriptionCallback<S> callback) {
    EventBus.create().add(this.type, callback);
  }
}