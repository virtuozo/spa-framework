package hitz.virtuozo.infra;

import hitz.virtuozo.infra.BrowserStorage;
import hitz.virtuozo.infra.Collection;
import hitz.virtuozo.infra.JSObject;
import hitz.virtuozo.infra.BrowserStorage.StoreKey;
import hitz.virtuozo.infra.api.ResponseCallback;
import hitz.virtuozo.infra.api.SingleResponseCallback;

public class AsyncBrowserStorage {

  private static final AsyncBrowserStorage instance = new AsyncBrowserStorage();

  private AsyncBrowserStorage() {}

  public static AsyncBrowserStorage get() {
    return AsyncBrowserStorage.instance;
  }

  public <J extends JSObject> SingleResponseCallback<J> asyncSingleStore(StoreKey store) {
    return new AsyncSingleStore<J>(store);
  }

  public <J extends JSObject> ResponseCallback<J> asyncStore(StoreKey store) {
    return new AsyncStore<J>(store);
  }

  class AsyncSingleStore<J extends JSObject> extends SingleResponseCallback<J> {

    private StoreKey store;

    AsyncSingleStore(StoreKey store) {
      super();
      this.store = store;
    }

    protected void onSuccess(J response) {
      BrowserStorage.get().store(this.store, response);
      BrowserStorage.get().fire(this.store);
    }
  }

  class AsyncStore<J extends JSObject> extends ResponseCallback<J> {

    private StoreKey store;

    AsyncStore(StoreKey store) {
      super();
      this.store = store;
    }

    protected void onSuccess(Collection<J> response) {
      BrowserStorage.get().store(this.store, response);
      BrowserStorage.get().fire(this.store);
    }
  }
}