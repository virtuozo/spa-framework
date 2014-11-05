package hitz.virtuozo.application;

import hitz.virtuozo.infra.HashObject;

public class RequestJSO<H extends HashObject> extends HashObject {

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