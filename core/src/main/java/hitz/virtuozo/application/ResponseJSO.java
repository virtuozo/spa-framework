package hitz.virtuozo.application;

import hitz.virtuozo.infra.HashCollection;
import hitz.virtuozo.infra.HashObject;
import hitz.virtuozo.infra.HttpStatusCode;
import hitz.virtuozo.interfaces.api.Response;

import com.google.gwt.core.client.JsArray;

@SuppressWarnings("unchecked")
public class ResponseJSO<H extends HashObject> extends HashObject implements Response<ResponseJSO<H>, H> {
  
  protected ResponseJSO() {
  }

  public final HashCollection<H> collect() {
    return new HashCollection<H>(this.array());
  }
  
  public final H get() {
    return this.getJavaScriptObject("data");
  }

  public final int start(){
		return this.getInt("start");
  }

  public final int end() {
    return this.getInt("end");
  }

  public final int total() {
    return this.getInt("total");
  }
  
  public final HttpStatusCode status() {
    return HttpStatusCode.valueOf(this.getInt("status"));
  }

  public final String messageCode() {
    
    return this.get("messageCode");
  }
  
  private final JsArray<H> array(){
    return this.<H>getArray("data");
  }
}