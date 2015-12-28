package virtuozo.infra;

import virtuozo.infra.JSObject;
import virtuozo.infra.api.JSOCallback;
import virtuozo.infra.api.AsyncException;
import virtuozo.ui.BrowserStorage;
import virtuozo.ui.BrowserStorage.StoreKey;

public class AsyncBrowserStorage {

  private static final AsyncBrowserStorage instance = new AsyncBrowserStorage();

  public static AsyncBrowserStorage get() {
    return AsyncBrowserStorage.instance;
  }
  
  private AsyncBrowserStorage() {
    super();
  }

  public <J extends JSObject> JSOCallback<J> asyncStore(StoreKey store) {
    return new AsyncStore<J>(store);
  }

  class AsyncStore<J extends JSObject> implements JSOCallback<J> {

    private StoreKey store;

    AsyncStore(StoreKey store) {
      super();
      this.store = store;
    }

    public void onSuccess(J response) {
      BrowserStorage.get().store(this.store, response);
    }
    
    @Override
    public void onFailure(AsyncException exception) {
      
    }
  }
}