package hitz.virtuozo.infra.api;

import hitz.virtuozo.infra.JSObject;
import hitz.virtuozo.infra.ResponseStatus;
import hitz.virtuozo.infra.SingleResponseJSO;

import org.fusesource.restygwt.client.Method;

public abstract class SingleResponseCallback<J extends JSObject> extends DefaultCallback<SingleResponseJSO<J>> {

  @Override
  public final void onSuccess(Method method, SingleResponseJSO<J> response) {
    ResponseStatus status = response.status();

    switch (status) {
      case SUCCESS:
        this.onSuccess(response.data());
        return;
      case END_POOLING:
        this.onEndPooling(response.data());
        return;
      default:
        this.messageCode(response.messageCode());
        this.onFailure(status);
    }
  }

  protected abstract void onSuccess(J response);

  protected void onEndPooling(J response) {
  }
}