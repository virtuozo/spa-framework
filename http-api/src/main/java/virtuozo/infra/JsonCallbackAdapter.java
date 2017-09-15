package virtuozo.infra;

import virtuozo.infra.events.FailureEvent;

public abstract class JsonCallbackAdapter implements JsonCallback {

  @Override
  public void onFailure(AsyncException exception) {
    FailureEvent.get().publish().with(exception).fire();
  }
}
