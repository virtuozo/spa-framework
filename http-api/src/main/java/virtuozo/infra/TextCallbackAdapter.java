package virtuozo.infra;

import virtuozo.infra.events.FailureEvent;

public abstract class TextCallbackAdapter implements TextCallback{

  @Override
  public void onFailure(AsyncException exception) {
    FailureEvent.get().publish().with(exception).fire();
  }
}
