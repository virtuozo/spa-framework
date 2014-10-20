package hitz.virtuozo.infra.api;

import hitz.virtuozo.infra.JSCollection;
import hitz.virtuozo.infra.JSObject;
import hitz.virtuozo.infra.ResponseJSO;
import hitz.virtuozo.infra.ResponseStatus;

import org.fusesource.restygwt.client.Method;

public abstract class ResponseCallback<J extends JSObject> extends DefaultCallback<ResponseJSO<J>> {

  @Override
  public final void onSuccess(Method method, ResponseJSO<J> response) {
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

  protected abstract void onSuccess(JSCollection<J> response);

  protected void onEndPooling(JSCollection<J> response) {
  }
}