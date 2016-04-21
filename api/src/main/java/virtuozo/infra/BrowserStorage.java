/**
 * Copyright (C) 2004-2014 the original author or authors. See the notice.md file distributed with
 * this work for additional information regarding copyright ownership.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package virtuozo.infra;

import java.util.HashMap;
import java.util.Map;

import virtuozo.infra.events.ValueEvent;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventHandler;

@SuppressWarnings("unchecked")
public class BrowserStorage {

  private static final BrowserStorage instance = new BrowserStorage();

  private EventManager bus = EventManager.create();

  private Map<StoreKey, Object> storageImpl = new HashMap<StoreKey, Object>();

  private BrowserStorage() {}

  public static interface StoreKey { 
    String name();
  }

  public static BrowserStorage get() {
    return BrowserStorage.instance;
  }

  public <T> T retrieve(StoreKey store) {
    return (T) this.storageImpl.get(store);
  }

  public BrowserStorage store(StoreKey store, Object value) {
    Object oldValue = null;
    if (this.contains(store)) {
      oldValue = this.retrieve(store);
    }

    this.storageImpl.put(store, value);
    this.fire(new ValueChanged<Object>(value, oldValue));
    this.fire(store);

    return this;
  }

  public <T> T remove(StoreKey store) {
    T oldValue = (T) this.storageImpl.remove(store);
    this.fire(new ValueChanged<T>(null, oldValue));

    return oldValue;
  }

  public boolean contains(StoreKey store) {
    return this.storageImpl.containsKey(store);
  }

  public BrowserStorage onChange(ValueChangeHandler<ValueChanged<?>> handler) {
    this.bus.add(ValueChangeEvent.getType(), handler);
    return this;
  }

  public BrowserStorage onComplete(StorageCompleteHandler handler) {
    this.bus.add(StorageCompleteEvent.TYPE, handler);
    return this;
  }

  private void fire(ValueChanged<?> value) {
    this.bus.fire(new StorageChangeEvent(value));
  }

  private void fire(StoreKey value) {
    this.bus.fire(new StorageCompleteEvent(value));
  }
  
  public static interface StorageChangedHandler extends EventHandler{
    void onChange(StorageChangeEvent event);
  }
  
  public static class StorageChangeEvent extends ValueEvent<ValueChanged<?>, StorageChangedHandler>{
    public static final Type<StorageChangedHandler> TYPE = new Type<BrowserStorage.StorageChangedHandler>();

    StorageChangeEvent(ValueChanged<?> value) {
      super(value);
    }
    
    @Override
    public Type<StorageChangedHandler> getAssociatedType() {
      return TYPE;
    }
    
    @Override
    protected void dispatch(StorageChangedHandler handler) {
      handler.onChange(this);
    }
  }
  
  public static interface StorageCompleteHandler extends EventHandler{
    void onComplete(StorageCompleteEvent event);
  }
  
  public static class StorageCompleteEvent extends ValueEvent<StoreKey, StorageCompleteHandler> {

    public static final Type<StorageCompleteHandler> TYPE = new Type<StorageCompleteHandler>();
    
    StorageCompleteEvent(StoreKey value) {
      super(value);
    }

    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<StorageCompleteHandler> getAssociatedType() {
      return TYPE;
    }

    @Override
    protected void dispatch(StorageCompleteHandler handler) {
      handler.onComplete(this);
    }
  }

  public static class ValueChanged<T> {

    private T newValue;

    private T oldValue;

    public ValueChanged(T newValue, T oldValue) {
      super();
      this.newValue = newValue;
      this.oldValue = oldValue;
    }

    public T newValue() {
      return newValue;
    }

    public T oldValue() {
      return oldValue;
    }
  }
}