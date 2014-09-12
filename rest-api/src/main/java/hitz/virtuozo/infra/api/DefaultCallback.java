package hitz.virtuozo.infra.api;

import hitz.virtuozo.infra.FailureNotifier;
import hitz.virtuozo.infra.JSObject;
import hitz.virtuozo.infra.ResponseStatus;

import org.fusesource.restygwt.client.FailedStatusCodeException;
import org.fusesource.restygwt.client.JSOCallback;
import org.fusesource.restygwt.client.Method;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.http.client.Response;

public abstract class DefaultCallback<J extends JSObject> implements JSOCallback<J> {

  private String messageCode;

  public String messageCode() {
    return messageCode;
  }

  public DefaultCallback<J> messageCode(String messageCode) {
    this.messageCode = messageCode;
    return this;
  }

  @Override
  public final void onFailure(Method method, Throwable exception) {
    ResponseStatus status = ResponseStatus.FAILURE;

    Response response = method.getResponse();
    if (response != null) {
      status = ResponseStatus.valueOf(response.getStatusCode());
      GWT.log(response.getText(), exception);
    } else if (exception instanceof FailedStatusCodeException) {
      FailedStatusCodeException failedException = (FailedStatusCodeException) exception;
      int statusCode = failedException.getStatusCode();
      status = ResponseStatus.valueOf(statusCode);
      GWT.log(failedException.getMessage(), exception);
    } else {
      GWT.log("callback Failure", exception);
    }

    this.onFailure(status);
  }

  protected void onFailure(ResponseStatus status) {
    FailureNotifier.get().fail(status, this.messageCode());
  }
}