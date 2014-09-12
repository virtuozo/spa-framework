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

public class MessageFormat {

  public static <T> String format(final String pattern, final T... args) {
    if (null == args || 0 == args.length) {
      return pattern;
    }

    JsArrayString array = JavaScriptObject.createArray().cast();
    for (Object arg : args) {
      array.push(String.valueOf(arg));
    }

    return nativeFormat(pattern, array);
  }

  private static native String nativeFormat(final String format, final JsArrayString args)/*-{
    return format.replace(/{(\d+)}/g, function(match, number) {
      return typeof args[number] != 'undefined' ? args[number] : match;
    });
  }-*/;
}