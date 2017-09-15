package virtuozo.infra;

import virtuozo.infra.BrowserStorage.StoreKey;
import virtuozo.infra.data.JSObject;
import virtuozo.infra.events.FailureEvent;

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
      FailureEvent.get().publish().with(exception).fire();
    }
  }
}