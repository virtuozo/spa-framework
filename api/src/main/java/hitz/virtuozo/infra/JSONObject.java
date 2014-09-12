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

import java.util.*;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.*;

public class JSONObject {

  private com.google.gwt.json.client.JSONObject wrapped;

  JSONObject() {
    this.wrapped = new com.google.gwt.json.client.JSONObject();
  }

  JSONObject(com.google.gwt.json.client.JSONObject wrapped) {
    super();
    this.wrapped = wrapped;
  }

  JSONObject(JavaScriptObject object) {
    this.wrapped = new com.google.gwt.json.client.JSONObject(object);
  }

  public JavaScriptObject toJavaScriptObject() {
    return this.wrapped.getJavaScriptObject();
  }

  public JSONObject put(String key, JSONObject value) {
    this.wrapped.put(key, value.wrapped);
    return this;
  }

  public JSONObject put(String key, String value) {
    this.wrapped.put(key, new JSONString(value));
    return this;
  }

  public JSONObject put(String key, Number value) {
    this.wrapped.put(key, new JSONNumber(value.doubleValue()));
    return this;
  }

  public JSONObject put(String key, Boolean value) {
    this.wrapped.put(key, JSONBoolean.getInstance(value));
    return this;
  }

  public JSONObject put(String key, Calendar date) {
    return this.put(key, date.toDate());
  }

  public JSONObject put(String key, Date date) {
    return this.put(key, date.getTime());
  }

  public JSONObject put(String key, JSONCollection<?> collection) {
    this.wrapped.put(key, collection.array());
    return this;
  }

  public JSONObject put(String key, JSONExpression expresion) {
    this.wrapped.put(key, expresion);
    return this;
  }

  public boolean containsKey(String key) {
    return this.wrapped.containsKey(key);
  }

  public Set<String> keySet() {
    return this.wrapped.keySet();
  }

  public String get(String key) {
    return this.wrapped.get(key).isString().stringValue();
  }

  public JSONObject getObject(String key) {
    return new JSONObject(this.wrapped.get(key).isObject());
  }

  public Number getNumber(String key) {
    return this.wrapped.get(key).isNumber().doubleValue();
  }

  public Boolean getBoolean(String key) {
    return this.wrapped.get(key).isBoolean().booleanValue();
  }

  public Date getDate(String key) {
    return new Date((long) this.wrapped.get(key).isNumber().doubleValue());
  }

  public Calendar getCalendar(String key) {
    return Calendar.as(this.getDate(key));
  }

  @Override
  public String toString() {
    return this.wrapped.toString();
  }
}