package virtuozo.infra.events;

public class FailureEvent extends Event<Exception> {
  private static final FailureEvent instance = new FailureEvent();
  
  public static FailureEvent get() {
    return instance;
  }
}