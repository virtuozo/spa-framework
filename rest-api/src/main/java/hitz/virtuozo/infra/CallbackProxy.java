package hitz.virtuozo.infra;

import hitz.virtuozo.infra.api.RestCallback;
import hitz.virtuozo.infra.api.RestException;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;

abstract class CallbackProxy<T> implements RequestCallback {

  private RestCallback<T> callback;

  private RestMethod method;

  CallbackProxy(RestMethod method, RestCallback<T> callback) {
    super();
    this.method = method;
    this.callback = callback;
  }

  @Override
  public void onResponseReceived(Request request, Response response) {
    if (response == null) {
      this.callback.onFailure(new RestException(HttpStatusCode.TIMEOUT));
      return;
    }
    if (isFailedStatus(response)) {
      Logger.get().debug("Service returned a failure status code: " + response.getStatusCode());
      callback.onFailure(new RestException(response.getStatusText(), HttpStatusCode.valueOf(response.getStatusCode())));
      return;
    }

    try {
      T value = null;
      String content = response.getText();
      if (content != null && content.length() > 0) {
        value = parse(content);
      }
      callback.onSuccess(value);
    } catch (Throwable e) {
      Logger.get().error("Could not parse response", e);
      callback.onFailure(new RestException(e));
    }
  }

  @Override
  public void onError(Request request, Throwable exception) {
    this.callback.onFailure(new RestException(exception));
  }

  private boolean isFailedStatus(Response response) {
    return !this.method.isExpected(response.getStatusCode());
  }

  protected abstract T parse(String content);
}