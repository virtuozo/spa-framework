package virtuozo.infra.events;

import virtuozo.infra.events.EventBus.PublishEvent;

import com.google.gwt.event.shared.GwtEvent.Type;

public class Publisher<S> {
  private S subject;

  private Type<SubscriptionCallback> type;

  Publisher(Type<SubscriptionCallback> type) {
    this.type = type;
  }

  public Subject with(S subject) {
    this.subject = subject;
    return new Subject();
  }

  public class Subject{
    public void fire() {
      EventBus.create().fire(new PublishEvent<S>(Publisher.this.subject, Publisher.this.type));
    }
  }
}