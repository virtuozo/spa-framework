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

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;

public class LightMap extends JavaScriptObject {

  protected LightMap() {
  }

  public static final native LightMap create()/*-{
		return new Object();
  }-*/;

  public final int size() {
    return this.arrayOfKeys().length();
  }

  public final boolean isEmpty() {
    return this.size() == 0;
  }

  public final native boolean containsKey(String key) /*-{
		return this[key] != undefined;
  }-*/;

  public final native boolean containsValue(String value) /*-{
		for ( var key in this) {
			if (this[key] === value) {
				return true;
			}
		}

		return false;
  }-*/;

  public final native void remove(String key) /*-{
		delete this[key];
  }-*/;

  public final native void putAll(LightMap map) /*-{
		for ( var key in map) {
			this[key] = map[key];
		}
  }-*/;

  public final void putAll(Collection<Entry> entries) {
    for (Entry entry : entries) {
      this.put(entry);
    }
  }

  public final void put(Entry entry) {
    this.put(entry.key(), entry.value());
  }

  public final native void put(String key, String value) /*-{
		this[key] = value;
  }-*/;

  public final native String get(String key) /*-{
		return this[key];
  }-*/;

  public final native LightMap clear() /*-{
		for ( var key in this) {
			delete this[key];
		}

		return this;
  }-*/;

  public final Iterable<String> keys() {
    return JSIterable.from(this.arrayOfKeys());
  }

  private final native JsArrayString arrayOfKeys() /*-{
		var a = new Array();
		for ( var e in this) {
			a.push(e);
		}
		return a;
  }-*/;
}