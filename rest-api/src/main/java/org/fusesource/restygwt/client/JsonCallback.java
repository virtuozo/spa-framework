package org.fusesource.restygwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.*;

abstract class JsonCallback<T> implements com.google.gwt.http.client.RequestCallback {

  protected final Method method;

  protected MethodCallback<T> callback;

  public JsonCallback(Method method, MethodCallback<T> callback) {
    this.method = method;
    this.callback = callback;
  }

  final public void onError(Request request, Throwable exception) {
    this.method.request = request;
    callback.onFailure(this.method, exception);
  }

  final public void onResponseReceived(Request request, Response response) {
    this.method.request = request;
    this.method.response = response;
    if (response == null) {
      callback.onFailure(this.method, new FailedStatusCodeException("TIMEOUT", 999));
      return;
    } 
    if (isFailedStatus(response)) {
      callback.onFailure(this.method, new FailedStatusCodeException(response.getStatusText(), response.getStatusCode()));
      return;
    } 
    T value;
    try {
      GWT.log("Received http response for request: " + this.method.builder.getHTTPMethod() + " " + this.method.builder.getUrl(), null);
      String content = response.getText();
      value = null;
      if (content != null && content.length() > 0) {
        value = parseResult();
      }
    } catch (Throwable e) {
      GWT.log("Could not parse response: " + e, e);
      callback.onFailure(this.method, e);
      return;
    }

    callback.onSuccess(this.method, value);
  }

  protected boolean isFailedStatus(Response response) {
    return !this.method.isExpected(response.getStatusCode());
  }

  abstract protected T parseResult() throws Exception;
}