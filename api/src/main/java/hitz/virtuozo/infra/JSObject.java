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

import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.json.client.JSONObject;

/**
 * To understand what is happening here, please visit the following address:
 * http://www.gwtproject.org/doc/latest/DevGuideCodingBasicsJSNI.html. Please note that
 * primitive type long is disallowed.
 */
public abstract class JSObject extends JavaScriptObject {

  protected JSObject() {
    super();
  }
  
  public static <J extends JavaScriptObject> J create() {
    return JavaScriptObject.createObject().cast();
  }

  public final JSONObject json() {
    return new JSONObject(this);
  }

  protected final native boolean hasKey(String key) /*-{
		return this[key] != undefined;
  }-*/;

  protected final native JsArrayString keys() /*-{
		var a = new Array();
		for ( var e in this) {
			a.push(e);
		}
		return a;
  }-*/;

  public final native String get(String property) /*-{
		return this[property];
  }-*/;

  public final native int getInt(String property) /*-{
		return this[property];
  }-*/;

  public final native float getFloat(String property) /*-{
		return this[property];
  }-*/;

  public final native double getDouble(String property) /*-{
		return this[property];
  }-*/;

  public final native boolean getBoolean(String property) /*-{
		return this[property];
  }-*/;

  protected final native <T extends JavaScriptObject> JsArray<T> getArray(String property) /*-{
		return this[property];
  }-*/;

  public final Date getDate(String property) {
    return this.get(property, new Date(), DateFormat.ISO_8601);
  }

  protected final String get(String property, String defaultValue) {
    if (!this.hasKey(property)) {
      return defaultValue;
    }

    return this.get(property);
  };

  protected final Integer get(String property, Integer defaultValue) {
    if (!this.hasKey(property)) {
      return defaultValue;
    }

    return this.getInt(property);
  }

  protected final int get(String property, int defaultValue) {
    if (!this.hasKey(property)) {
      return defaultValue;
    }

    return this.getInt(property);
  };

  protected final Float get(String property, Float defaultValue) {
    if (!this.hasKey(property)) {
      return defaultValue;
    }

    return this.getFloat(property);
  }

  protected final float get(String property, float defaultValue) {
    if (!this.hasKey(property)) {
      return defaultValue;
    }

    return this.getFloat(property);
  };

  protected final Double get(String property, Double defaultValue) {
    if (!this.hasKey(property)) {
      return defaultValue;
    }

    return this.getDouble(property);
  }

  protected final double get(String property, double defaultValue) {
    if (!this.hasKey(property)) {
      return defaultValue;
    }

    return this.getDouble(property);
  };

  protected final Boolean get(String property, Boolean defaultValue) {
    if (!this.hasKey(property)) {
      return defaultValue;
    }

    return this.getBoolean(property);
  }

  protected final boolean get(String property, boolean defaultValue) {
    if (!this.hasKey(property)) {
      return defaultValue;
    }

    return this.getBoolean(property);
  };

  protected final <T extends JavaScriptObject> JsArray<T> get(String property, JsArray<T> defaultValue) {
    if (!this.hasKey(property)) {
      return defaultValue;
    }

    return this.getArray(property);
  };

  protected final <T extends JavaScriptObject> T get(String property, T defaultValue) {
    if (!this.hasKey(property)) {
      return defaultValue;
    }

    return this.getJavaScriptObject(property);
  }

  protected final native <T extends JavaScriptObject> T getJavaScriptObject(String property) /*-{
		return this[property];
  }-*/;

  protected final Date get(String property, Date defaultValue) {
    return this.get(property, defaultValue, DateFormat.ISO_8601);
  }

  protected final Date get(String property, Date defaultValue, DateFormat format) {
    return format.unformat(this.get(property, format.format(defaultValue)));
  }

  protected final native void set(String property, String value) /*-{
		this[property] = value;
  }-*/;

  protected final native void set(String property, int value) /*-{
		this[property] = value;
  }-*/;

  protected final native void set(String property, float value) /*-{
		this[property] = value;
  }-*/;

  protected final native void set(String property, double value) /*-{
		this[property] = value;
  }-*/;

  protected final native void set(String property, boolean value) /*-{
		this[property] = value;
  }-*/;

  protected final native <T extends JavaScriptObject> void set(String property, JsArray<T> value) /*-{
		this[property] = value;
  }-*/;

  protected final native <T extends JavaScriptObject> void set(String property, T value) /*-{
		this[property] = value;
  }-*/;

  protected final void set(String property, Date value) {
    this.set(property, value, DateFormat.ISO_8601);
  }

  protected final void set(String property, Date defaultValue, DateFormat format) {
    this.set(property, format.format(defaultValue));
  }
}