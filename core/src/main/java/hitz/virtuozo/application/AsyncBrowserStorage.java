package hitz.virtuozo.application;

import hitz.virtuozo.infra.BrowserStorage;
import hitz.virtuozo.infra.BrowserStorage.StoreKey;
import hitz.virtuozo.infra.HashObject;
import hitz.virtuozo.infra.api.HashCallback;
import hitz.virtuozo.infra.api.RestException;

public class AsyncBrowserStorage {

  private static final AsyncBrowserStorage instance = new AsyncBrowserStorage();

  private AsyncBrowserStorage() {}

  public static AsyncBrowserStorage get() {
    return AsyncBrowserStorage.instance;
  }

  public <H extends HashObject> HashCallback<H> asyncStore(StoreKey store) {
    return new AsyncStore<H>(store);
  }

  class AsyncStore<H extends HashObject> implements HashCallback<H> {

    private StoreKey store;

    AsyncStore(StoreKey store) {
      super();
      this.store = store;
    }

    public void onSuccess(H response) {
      BrowserStorage.get().store(this.store, response);
    }
    
    @Override
    public void onFailure(RestException exception) {
      
    }
  }
}