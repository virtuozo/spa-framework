package virtuozo.application;

import virtuozo.infra.HashObject;
import virtuozo.interfaces.api.Request;

public class RequestJSO<H extends HashObject> extends HashObject implements Request<RequestJSO<H>, H> {

  protected RequestJSO() {
  }

  public final native RequestJSO<H> data(H data)/*-{
		this.data = data;
		return this;
  }-*/;

  public final native RequestJSO<H> start(int start)/*-{
		this.start = start;
		return this;
  }-*/;

  public final native RequestJSO<H> end(int end)/*-{
		this.end = end;
		return this;
  }-*/;
}