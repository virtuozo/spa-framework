package virtuozo.infra;

import virtuozo.infra.events.FailureEvent;

import com.google.gwt.core.client.JavaScriptObject;

public abstract class JSOCallbackAdapter<J extends JavaScriptObject> implements JSOCallback<J> {
  @Override
  public void onFailure(AsyncException exception) {
    FailureEvent.get().publish().with(exception).fire();
  }
}