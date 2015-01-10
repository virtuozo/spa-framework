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

package virtuozo.ui;

import virtuozo.ui.InputGroup.Size;
import virtuozo.ui.interfaces.HasChangeHandlers;
import virtuozo.ui.interfaces.UIInput;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeHandler;

@SuppressWarnings("unchecked")
abstract class Spinner<S extends Spinner<S>> extends Component<S> implements UIInput<S, Integer>, HasChangeHandlers<S> {

  private InputGroup input = InputNumber.create().css(Size.SMALL).css("spinner-input").maxLength(3);
  
  private int step = 1;
  
  private int defaultValue = 0;

  private int minValue = Integer.MIN_VALUE;

  private int maxValue = Integer.MAX_VALUE;
  
  protected Spinner() {
    this.incorporate(this.input);
    this.init();
  }
  
  private void init(){
    this.input().onBlur(new BlurHandler() {

      @Override
      public void onBlur(BlurEvent event) {
        if (Spinner.this.input().value().isEmpty()) {
          Spinner.this.value(Spinner.this.defaultValue());
          return;
        }

        Spinner.this.value(Spinner.this.value());
      }
    });
  }

  protected InputGroup input() {
    return input;
  }
  
  public int defaultValue() {
    return defaultValue;
  }
  
  public S defaultValue(int value) {
    this.defaultValue = value;
    return this.value(value);
  }
  
  public S onChange(ChangeHandler handler) {
    this.input.on(handler);
    return (S) this;
  }

  public S range(int minValue, int maxValue) {
    this.minValue = minValue;
    this.maxValue = maxValue;

    return (S) this;
  }

  public S step(int step) {
    this.step = step;
    return (S) this;
  }

  public S increment() {
    int value = this.value() + this.step;

    value = Math.min(value, this.maxValue);

    return this.value(value);
  }

  public S decrement() {
    int value = this.value() - this.step;

    value = Math.max(value, this.minValue);

    return this.value(value);
  }

  public S maxLength(int maxLength) {
    this.input.maxLength(maxLength);
    return (S) this;
  }

  public S value(Integer value) {
    value = Math.max(value, this.minValue);
    value = Math.min(value, this.maxValue);

    this.input.value(String.valueOf(value));
    return (S) this;
  }

  public S clear() {
    this.input.value("");
    return (S) this;
  }

  public Integer value() {
    return Integer.valueOf(this.input.value());
  }

  public S placeholder(String placeholder) {
    this.input.placeholder(placeholder);
    return (S) this;
  }
  
  @Override
  public S disable() {
    this.input.disable();
    return (S) this;
  }
  
  @Override
  public boolean disabled() {
    return this.input.disabled();
  }
  
  @Override
  public S enable() {
    this.input.enable();
    return (S) this;
  }
}