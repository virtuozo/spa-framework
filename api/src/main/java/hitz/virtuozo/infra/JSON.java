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
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONExpression;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

public class JSON {

  public static JSONCollection<String> asExpressionCollection() {
    return new JSONCollection<String>() {

      @Override
      JSONValue toJSONValue(String value) {
        return new JSONExpression(value);
      }

      @Override
      String toValue(JSONValue value) {
        return value.isString().stringValue();
      }
    };
  }

  public static JSONCollection<String> asStringCollection(String json) {
    JSONCollection<String> collection = asStringCollection();
    collection.setArray(JSONParser.parseLenient(json).isArray());

    return collection;
  }

  public static JSONCollection<String> asStringCollection() {
    return new JSONCollection<String>() {

      @Override
      JSONValue toJSONValue(String value) {
        return new JSONString(value);
      }

      @Override
      String toValue(JSONValue value) {
        return value.isString().stringValue();
      }
    };
  }

  public static JSONCollection<Boolean> asBooleanCollection(String json) {
    JSONCollection<Boolean> collection = asBooleanCollection();
    collection.setArray(JSONParser.parseLenient(json).isArray());

    return collection;
  }

  public static JSONCollection<Boolean> asBooleanCollection() {
    return new JSONCollection<Boolean>() {

      @Override
      JSONValue toJSONValue(Boolean value) {
        return JSONBoolean.getInstance(value);
      }

      @Override
      Boolean toValue(JSONValue value) {
        return value.isBoolean().booleanValue();
      }
    };
  }

  public static JSONCollection<Number> asNumberCollection(String json) {
    JSONCollection<Number> collection = asNumberCollection();
    collection.setArray(JSONParser.parseLenient(json).isArray());

    return collection;
  }

  public static JSONCollection<Number> asNumberCollection() {
    return new JSONCollection<Number>() {

      @Override
      JSONValue toJSONValue(Number value) {
        return new JSONNumber(value.doubleValue());
      }

      @Override
      Number toValue(JSONValue value) {
        return value.isNumber().doubleValue();
      }
    };
  }

  public static JSONObject asObject(JavaScriptObject object) {
    return new JSONObject(object);
  }

  public static JSONObject asObject() {
    return new JSONObject();
  }

  public static JSONObject asObject(String json) {
    return new JSONObject(JSONParser.parseLenient(json).isObject());
  }
}