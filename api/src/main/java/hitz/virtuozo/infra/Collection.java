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

import com.google.gwt.core.client.*;

public class Collection<J extends JavaScriptObject> implements Iterable<J>, Iterator<J> {

  private JsArray<J> array;

  int index;

  public Collection(JsArray<J> array) {
    this.array = array;
  }

  @Override
  public Iterator<J> iterator() {
    this.index = 0;
    return this;
  }

  @Override
  public boolean hasNext() {
    boolean hasNext = index < array.length();
    if (!hasNext) {
      this.index = 0;
    }

    return hasNext;
  }

  @Override
  public J next() {
    return this.array.get(this.index++);
  }

  @Override
  public void remove() {
    this.array.set(this.index, null);
  }

  public JsArray<J> array() {
    return this.array;
  }
}