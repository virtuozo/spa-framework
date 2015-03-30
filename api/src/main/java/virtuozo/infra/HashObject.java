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

import java.util.Date;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.json.client.JSONObject;

/**
 * To understand what is happening here, please visit the following address:
 * http://www.gwtproject.org/doc/latest/DevGuideCodingBasicsJSNI.html. Please note that primitive
 * type long is disallowed.
 */
public class HashObject extends JavaScriptObject {

  protected HashObject() {
    super();
  }

  public static interface Property {
    String name();
  }

  public static HashObject create() {
    return JavaScriptObject.createObject().cast();
  }

  public final JSONObject json() {
    return new JSONObject(this);
  }
  
  public final boolean hasProperty(Property property){
    return this.hasProperty(property.name());
  }

  private final native boolean hasProperty(String key) /*-{
		return this[key] != undefined;
  }-*/;

  private final native String stringOf(String property) /*-{
		return this[property];
  }-*/;

  private final native int integerOf(String property) /*-{
		return this[property];
  }-*/;

  private final native float floatOf(String property) /*-{
		return this[property];
  }-*/;

  private final native double doubleOf(String property) /*-{
		return this[property];
  }-*/;

  private final native boolean booleanOf(String property) /*-{
		return this[property];
  }-*/;

  private final native Date dateOf(String property) /*-{
    var value = this[property];
    
    if(value instanceof Date){
      return this[property];
    }
    
    return new Date(value);
  }-*/;
  
  private final native <H extends HashObject> JsArray<H> arrayOf(String property) /*-{
    return this[property];
  }-*/;
  
  private final native <H extends HashObject> H hashOf(String property) /*-{
		return this[property];
  }-*/;

  private final native void set(String property, String value) /*-{
		this[property] = value;
  }-*/;

  private final native void set(String property, int value) /*-{
		this[property] = value;
  }-*/;

  private final native void set(String property, float value) /*-{
		this[property] = value;
  }-*/;

  private final native void set(String property, double value) /*-{
		this[property] = value;
  }-*/;

  private final native void set(String property, boolean value) /*-{
		this[property] = value;
  }-*/;

  private final native void set(String property, Date value) /*-{
    this[property] = value;
  }-*/;

  private final native <H extends HashObject> void set(String property, JsArray<H> value) /*-{
		this[property] = value;
  }-*/;
  
  private final native <H extends HashObject> void set(String property, H value) /*-{
		this[property] = value;
  }-*/;
}