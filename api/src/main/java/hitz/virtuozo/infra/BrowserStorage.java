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
package hitz.virtuozo.infra;

import hitz.virtuozo.infra.api.EventHandler;
import hitz.virtuozo.infra.api.EventType;
import hitz.virtuozo.ui.Event;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class BrowserStorage {

  private static final BrowserStorage instance = new BrowserStorage();

  private EventBus eventBus = new EventBus();

  private Map<StoreKey, Object> storageImpl = new HashMap<StoreKey, Object>();

  private BrowserStorage() {
  }

  public static interface StoreKey { }

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
    this.fire(new PairedValue<Object>(value, oldValue));
    this.fire(store);

    return this;
  }

  public <T> T remove(StoreKey store) {
    T oldValue = (T) this.storageImpl.remove(store);
    this.fire(new PairedValue<T>(null, oldValue));

    return oldValue;
  }

  public boolean contains(StoreKey store) {
    return this.storageImpl.containsKey(store);
  }

  public BrowserStorage onChange(EventHandler<PairedValue<?>> handler) {
    this.eventBus.add(Type.CHANGE, handler);
    return this;
  }

  public BrowserStorage onComplete(EventHandler<StoreKey> handler) {
    this.eventBus.add(Type.COMPLETE, handler);
    return this;
  }

  public BrowserStorage removeHandler(EventType type) {
    this.eventBus.remove(type);
    return this;
  }

  private void fire(PairedValue<?> value) {
    this.eventBus.fire(new Event<PairedValue<?>>(Type.CHANGE, value));
  }

  void fire(StoreKey value) {
    this.eventBus.fire(new Event<StoreKey>(Type.COMPLETE, value));
  }

  public static class PairedValue<T> {

    private T newValue;

    private T oldValue;

    public PairedValue(T newValue, T oldValue) {
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

  enum Type implements EventType {
    CHANGE, COMPLETE;
  }
}