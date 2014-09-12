package hitz.virtuozo.infra;

import hitz.virtuozo.infra.JSObject;

public class RequestJSO<J extends JSObject> extends JSObject {

  protected RequestJSO() {
  }

  public final native RequestJSO<J> data(J data)/*-{
                                                this.data = data;
                                                return this;
                                                }-*/;

  public final native RequestJSO<J> start(int start)/*-{
                                                    this.start = start;
                                                    return this;
                                                    }-*/;

  public final native RequestJSO<J> end(int end)/*-{
                                                this.end = end;
                                                return this;
                                                }-*/;
}