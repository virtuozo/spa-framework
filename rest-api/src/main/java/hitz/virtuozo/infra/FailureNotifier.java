package hitz.virtuozo.infra;

import hitz.virtuozo.infra.ResponseStatus;
import hitz.virtuozo.infra.api.FailureHandler;

import java.util.ArrayList;

import org.fusesource.restygwt.client.Method;

import com.google.gwt.http.client.Response;

public final class FailureNotifier {

  private static final FailureNotifier instance = new FailureNotifier();

  private ArrayList<FailureHandler> handlers = new ArrayList<FailureHandler>();

  private FailureNotifier() {
  }

  public static FailureNotifier get() {
    return FailureNotifier.instance;
  }

  public void onFailure(FailureHandler handler) {
    this.handlers.add(handler);
  }

  public void fail(Method method, Throwable exception) {
    Response response = method.getResponse();

    if (response != null) {
      this.fail(ResponseStatus.valueOf(response.getStatusCode()), null);
      return;
    }

    this.fail(ResponseStatus.FAILURE, null);
  }

  public void fail(ResponseStatus status, String messageCode) {
    for (FailureHandler handler : this.handlers) {
      handler.onFailure(status, messageCode);
    }
  }
}