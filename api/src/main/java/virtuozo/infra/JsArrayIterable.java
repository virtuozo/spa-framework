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

import java.util.Iterator;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayBoolean;
import com.google.gwt.core.client.JsArrayInteger;
import com.google.gwt.core.client.JsArrayNumber;
import com.google.gwt.core.client.JsArrayString;

public class JsArrayIterable {

  public static <J extends JavaScriptObject> Iterable<J> of(JsArray<J> values) {
    return new JavaScriptObjectIterable<J>(values);
  }

  public static Iterable<Boolean> of(JsArrayBoolean values) {
    return new BooleanIterable(values);
  }

  public static Iterable<Integer> of(JsArrayInteger values) {
    return new IntegerIterable(values);
  }

  public static Iterable<Number> of(JsArrayNumber values) {
    return new NumberIterable(values);
  }

  public static Iterable<String> of(JsArrayString values) {
    return new StringIterable(values);
  }

  static class BooleanIterable extends AbstractIterable<Boolean> {

    private JsArrayBoolean values;

    BooleanIterable(JsArrayBoolean values) {
      super(values.length());
      this.values = values;
    }

    @Override
    public Boolean next() {
      return this.values.get(this.doNext());
    }
  }

  static class IntegerIterable extends AbstractIterable<Integer> {

    private JsArrayInteger values;

    IntegerIterable(JsArrayInteger values) {
      super(values.length());
      this.values = values;
    }

    @Override
    public Integer next() {
      return this.values.get(this.doNext());
    }
  }

  static class NumberIterable extends AbstractIterable<Number> {

    private JsArrayNumber values;

    NumberIterable(JsArrayNumber values) {
      super(values.length());
      this.values = values;
    }

    @Override
    public Number next() {
      return this.values.get(this.doNext());
    }
  }

  static class StringIterable extends AbstractIterable<String> {

    private JsArrayString values;

    StringIterable(JsArrayString values) {
      super(values.length());
      this.values = values;
    }

    @Override
    public String next() {
      return this.values.get(this.doNext());
    }
  }

  static class JavaScriptObjectIterable<J extends JavaScriptObject> extends AbstractIterable<J> {

    private JsArray<J> values;

    JavaScriptObjectIterable(JsArray<J> values) {
      super(values.length());
      this.values = values;
    }

    @Override
    public J next() {
      return this.values.get(this.doNext());
    }
  }

  static abstract class AbstractIterable<T> implements Iterable<T>, Iterator<T> {

    private int index;

    private int length;

    AbstractIterable(int length) {
      this.length = length;
    }

    int doNext() {
      return this.index++;
    }

    int index() {
      return this.index;
    }

    @Override
    public Iterator<T> iterator() {
      this.index = 0;
      return this;
    }

    @Override
    public boolean hasNext() {
      boolean hasNext = this.index < this.length;
      if (!hasNext) {
        this.index = 0;
      }

      return hasNext;
    }

    @Override
    public void remove() {
      throw new UnsupportedOperationException("Remove for JSArrays is not supported");
    }
  }
}
