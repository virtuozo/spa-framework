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

package com.google.gwt.json.client;

import com.google.gwt.core.client.JavaScriptObject;

public class JSONExpression extends JSONValue {

  private String value;

  public JSONExpression(String expression) {
    super();
    this.value = expression;
  }

  @Override
  public String toString() {
    return this.value;
  }

  @Override
  native JavaScriptObject getUnwrapper() /*-{
                                         return @com.google.gwt.json.client.JSONExpression::unwrap(Lcom/google/gwt/json/client/JSONExpression;);
                                         }-*/;

  /**
   * Called from {@link #getUnwrapper()}.
   */
  private static String unwrap(JSONExpression value) {
    return value.value;
  }
}