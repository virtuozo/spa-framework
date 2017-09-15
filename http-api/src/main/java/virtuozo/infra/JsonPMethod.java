package virtuozo.infra;

import virtuozo.infra.HttpClient.Endpoint;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.jsonp.client.JsonpRequestBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class JsonPMethod {

  private JsonpRequestBuilder jsonpBuilder = new JsonpRequestBuilder();

  private Endpoint path;

  JsonPMethod(Endpoint path) {
    this.path = path;
  }

  public JsonPMethod callbackParam(String callbackParam) {
    jsonpBuilder.setCallbackParam(callbackParam);
    return this;
  }

  public JsonPMethod failureCallbackParam(String failureCallbackParam) {
    jsonpBuilder.setFailureCallbackParam(failureCallbackParam);
    return this;
  }

  public JsonPMethod timeout(int timeout) {
    jsonpBuilder.setTimeout(timeout);
    return this;
  }
  
  public <J extends JavaScriptObject> void send(final JSOCallback<J> callback) {
    jsonpBuilder.requestObject(HttpMethod.url(this.path), new AsyncCallback<JavaScriptObject>() {
      @Override
      public void onSuccess(JavaScriptObject result) {
        callback.onSuccess((J) result);
      }

      @Override
      public void onFailure(Throwable caught) {
        callback.onFailure(new AsyncException(caught));
      }
    });
  }

  public JsonPMethod send(final JsonCallback callback) {
    jsonpBuilder.requestObject(HttpMethod.url(this.path), new AsyncCallback<JavaScriptObject>() {
      @Override
      public void onSuccess(JavaScriptObject result) {
        callback.onSuccess(new JSONObject(result));
      }

      @Override
      public void onFailure(Throwable caught) {
        callback.onFailure(new AsyncException(caught));
      }
    });
    return this;
  }

  public JsonPMethod send(final TextCallback callback) {
    jsonpBuilder.requestString(HttpMethod.url(this.path), new AsyncCallback<String>() {
      @Override
      public void onSuccess(String result) {
        callback.onSuccess(result);
      }

      @Override
      public void onFailure(Throwable caught) {
        callback.onFailure(new AsyncException(caught));
      }
    });
    return this;
  }

  public <J extends JavaScriptObject> JsonPMethod send(final AsyncCallback<J> callback) {
    jsonpBuilder.requestObject(this.path.toString(), callback);
    return this;
  }
}