package hitz.virtuozo.infra;

import hitz.virtuozo.infra.JSObject;

public class SingleResponseJSO<J extends JSObject> extends StatusResponseJSO {

  protected SingleResponseJSO() {
  }

  public final native J data() /*-{
                               return this.data;
                               }-*/;
}