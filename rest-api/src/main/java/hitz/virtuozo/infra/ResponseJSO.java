package hitz.virtuozo.infra;

import hitz.virtuozo.infra.Collection;
import hitz.virtuozo.infra.JSObject;

import com.google.gwt.core.client.JsArray;

public class ResponseJSO<J extends JSObject> extends StatusResponseJSO {

  protected ResponseJSO() {
  }

  public final native JsArray<J> array() /*-{
                                         return this.data == undefined ? new Array() : this.data;
                                         }-*/;

  public final Collection<J> data() {
    return new Collection<J>(this.array());
  }

  public final native int start() /*-{
                                  return this.start;
                                  }-*/;

  public final native int end() /*-{
                                return this.end;
                                }-*/;

  public final native int total() /*-{
                                  return this.total;
                                  }-*/;
}