package hitz.virtuozo.application;

import hitz.virtuozo.infra.HashCollection;
import hitz.virtuozo.infra.HashObject;

import com.google.gwt.core.client.JsArray;

public class ResponseJSO<H extends HashObject> extends StatusResponseJSO {

  protected ResponseJSO() {
  }

  public final HashCollection<H> collect() {
    return new HashCollection<H>(this.array());
  }
  
  public final native H get()/*-{
    return this.data == undefined ? new Object() : this.data;
  }-*/;

  public final native int start() /*-{
		return this.start;
  }-*/;

  public final native int end() /*-{
		return this.end;
  }-*/;

  public final native int total() /*-{
		return this.total;
  }-*/;
  
  private final native JsArray<H> array() /*-{
    return this.data == undefined ? new Array() : this.data;
  }-*/;
}