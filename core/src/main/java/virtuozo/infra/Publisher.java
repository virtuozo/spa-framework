package virtuozo.infra;

import virtuozo.infra.EventBus.PublishEvent;

import com.google.gwt.event.shared.GwtEvent.Type;

public class Publisher<S> {
  private S subject;

  private Type<SubscriptionCallback> type;

  Publisher(Type<SubscriptionCallback> type) {
    this.type = type;
  }

  public Publisher<S> with(S subject) {
    this.subject = subject;
    return this;
  }

  public void fire() {
    EventBus.get().fire(new PublishEvent<S>(this.subject, this.type));
  }
}