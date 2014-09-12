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

import java.util.Iterator;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONValue;

public abstract class JSONCollection<V> implements Iterable<V>, Iterator<V> {

  private JSONArray array;

  int index;

  JSONCollection() {
    this(new JSONArray());
  }

  public JSONCollection(JSONArray array) {
    super();
    this.array = array;
  }

  void setArray(JSONArray array) {
    this.array = array;
  }

  public JSONCollection<V> add(V value) {
    this.array.set(this.array.size(), this.toJSONValue(value));
    return this;
  }

  public V get(int index) {
    return this.toValue(this.array.get(index));
  }

  public JSONArray array() {
    return this.array;
  }

  public int size() {
    return this.array.size();
  }

  @Override
  public String toString() {
    return this.array.toString();
  }

  @Override
  public Iterator<V> iterator() {
    return this;
  }

  @Override
  public boolean hasNext() {
    boolean hasNext = this.index < this.size();

    if (!hasNext) {
      this.index = 0;
    }

    return hasNext;
  }

  @Override
  public V next() {
    return this.get(this.index++);
  }

  @Override
  public void remove() {
    return;
  }

  abstract JSONValue toJSONValue(V value);

  abstract V toValue(JSONValue value);
}