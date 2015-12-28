package virtuozo.infra;

import virtuozo.infra.HttpStatusCode;
import virtuozo.infra.Logger;
import virtuozo.infra.api.AsyncCallback;
import virtuozo.infra.api.AsyncException;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;

abstract class CallbackProxy<T> implements RequestCallback {

  private AsyncCallback<T> callback;

  private HttpMethod method;

  CallbackProxy(HttpMethod method, AsyncCallback<T> callback) {
    super();
    this.method = method;
    this.callback = callback;
  }

  @Override
  public void onResponseReceived(Request request, Response response) {
    if (response == null) {
      this.callback.onFailure(new AsyncException(HttpStatusCode.TIMEOUT));
      return;
    }
    if (isFailedStatus(response)) {
      Logger.get().debug("Service returned a failure status code: " + response.getStatusCode());
      callback.onFailure(new AsyncException(response.getStatusText(), HttpStatusCode.valueOf(response.getStatusCode())));
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
      callback.onFailure(new AsyncException(e));
    }
  }

  @Override
  public void onError(Request request, Throwable exception) {
    this.callback.onFailure(new AsyncException(exception));
  }

  private boolean isFailedStatus(Response response) {
    return !this.method.isExpected(response.getStatusCode());
  }

  protected abstract T parse(String content);
}