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

import virtuozo.infra.DataBinding.Attribute;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.json.client.JSONObject;

/**
 * To understand what is happening here, please visit the following address:
 * http://www.gwtproject.org/doc/latest/DevGuideCodingBasicsJSNI.html. Please note that primitive
 * type long is disallowed.
 */
public class JSObject extends JavaScriptObject {

  protected JSObject() {
    super();
  }

  public static JSObject create() {
    return JavaScriptObject.createObject().cast();
  }

  public final JSONObject json() {
    return new JSONObject(this);
  }
  
  public final DataBinding<Boolean> bindAsBoolean(Attribute attribute){
    return new BooleanData(attribute, this);
  }
  
  public final DataBinding<Date> bindAsDate(Attribute attribute){
    return new DateData(attribute, this);
  }
  
  public final DataBinding<Double> bindAsDouble(Attribute attribute){
    return new DoubleData(attribute, this);
  }
  
  public final DataBinding<Float> bindAsFloat(Attribute attribute){
    return new FloatData(attribute, this);
  }
  
  public final DataBinding<Integer> bindAsInteger(Attribute attribute){
    return new IntegerData(attribute, this);
  }
  
  public final <J extends JSObject> DataBinding<J> bindAsObject(Attribute attribute){
    return new ObjectData<J>(attribute, this);
  }
  
  public final DataBinding<String> bindAsString(Attribute attribute){
    return new StringData(attribute, this);
  }
  
  final native boolean has(String key) /*-{
		return this[key] != undefined;
  }-*/;

  final native String getString(String property) /*-{
		return this[property];
  }-*/;

  final native int getInteger(String property) /*-{
		return this[property];
  }-*/;

  final native float getFloat(String property) /*-{
		return this[property];
  }-*/;

  final native double getDouble(String property) /*-{
		return this[property];
  }-*/;

  final native boolean getBoolean(String property) /*-{
		return this[property];
  }-*/;

  final native Date getDate(String property) /*-{
    var value = this[property];
    
    if(value instanceof Date){
      return value;
    }
    
    return new Date(value);
  }-*/;
  
  final native <J extends JSObject> JsArray<J> getArray(String property) /*-{
    return this[property];
  }-*/;
  
  final native <J extends JSObject> J getJsObject(String property) /*-{
		return this[property];
  }-*/;

  final native void set(String property, String value) /*-{
		this[property] = value;
  }-*/;

  final native void set(String property, int value) /*-{
		this[property] = value;
  }-*/;

  final native void set(String property, float value) /*-{
		this[property] = value;
  }-*/;

  final native void set(String property, double value) /*-{
		this[property] = value;
  }-*/;

  final native void set(String property, boolean value) /*-{
		this[property] = value;
  }-*/;

  final native void set(String property, Date value) /*-{
    this[property] = value;
  }-*/;

  final native <J extends JSObject> void set(String property, JsArray<J> value) /*-{
		this[property] = value;
  }-*/;
  
  final native <J extends JSObject> void set(String property, J value) /*-{
		this[property] = value;
  }-*/;
}