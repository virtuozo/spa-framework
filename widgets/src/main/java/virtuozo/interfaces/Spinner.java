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

package virtuozo.interfaces;

import virtuozo.infra.Elements;
import virtuozo.infra.handlers.HasChangeHandlers;
import virtuozo.interfaces.Button;
import virtuozo.interfaces.ButtonGroup;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.InputText;
import virtuozo.interfaces.Tag;
import virtuozo.interfaces.UIClass;
import virtuozo.interfaces.UIClasses;
import virtuozo.interfaces.UIInput;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;

public class Spinner extends Component<Spinner> implements UIInput<Spinner, Integer>, HasChangeHandlers<Spinner> {

  private int step = 1;

  private int defaultValue = 0;

  private int minValue = Integer.MIN_VALUE;

  private int maxValue = Integer.MAX_VALUE;

  private InputText input = InputText.create().css("spinner-input", "form-control");
  
  private ButtonGroup buttons = ButtonGroup.vertical().css("spinner-buttons");
  
  private Assets assets = GWT.create(Assets.class);
  
  private Tag<DivElement> container = Tag.asDiv().css("spinner");
  
  public static Spinner create() {
    return new Spinner();
  }
  
  private Spinner() {
    super(Elements.div());
    this.init();
  }

  private void init() {
    super.css("number-spinner").addChild(this.container);
    this.container.css("spinner").addChild(this.input).addChild(this.buttons);
    this.buttons.addButton().css("spinner-up").css(RichButton.Size.X_SMALL).icon(this.assets.upIcon()).onClick(new ClickHandler() {
      
      @Override
      public void onClick(ClickEvent event) {
        Spinner.this.increment();
      }
    });
    
    this.buttons.addButton().css("spinner-down").css(RichButton.Size.X_SMALL).icon(this.assets.downIcon()).onClick(new ClickHandler() {
      
      @Override
      public void onClick(ClickEvent event) {
        Spinner.this.decrement();
      }
    });
    this.input.value("0");
    this.input.onBlur(new BlurHandler() {

      @Override
      public void onBlur(BlurEvent event) {
        if (Spinner.this.input.value().isEmpty()) {
          Spinner.this.value(Spinner.this.defaultValue());
          return;
        }

        Spinner.this.value(Spinner.this.value());
      }
    });
    
    this.input.onKeyDown(new KeyDownHandler() {
      
      @Override
      public void onKeyDown(KeyDownEvent event) {
        if(event.isUpArrow()){
          Spinner.this.increment();
          return;
        }
        
        if(event.isDownArrow()){
          Spinner.this.decrement();
          return;
        }
      }
    });
    
    NumberInputPrevent.create().attachTo(this.input);
  }
  
  @Override
  public UIClasses css() {
    return this.input.css();
  }
  
  @Override
  public Spinner css(String... classes) {
    this.input.css(classes);
    return this;
  }
  
  @Override
  public Spinner css(UIClass... classes) {
    this.input.css(classes);
    return this;
  }

  public int defaultValue() {
    return defaultValue;
  }

  public Spinner defaultValue(int value) {
    this.defaultValue = value;
    return this.value(value);
  }

  public Spinner onChange(ChangeHandler handler) {
    this.input.onChange(handler);
    return this;
  }

  public Spinner range(int minValue, int maxValue) {
    this.minValue(minValue);
    this.maxValue(maxValue);

    return this.value(this.value());
  }
  
  public Spinner minValue(int minValue){
    this.minValue = minValue;
    return this.value(this.value());
  }
  
  public Spinner maxValue(int maxValue){
    this.maxValue = maxValue;
    return this.value(this.value());
  }

  public Spinner step(int step) {
    this.step = step;
    return this;
  }

  public Spinner increment() {
    return this.value(this.value() + this.step);
  }

  public Spinner decrement() {
    return this.value(this.value() - this.step);
  }

  public Spinner maxLength(int maxLength) {
    this.input.maxLength(maxLength);
    return this;
  }

  public Spinner value(Integer value) {
    value = Math.max(value, this.minValue);
    value = Math.min(value, this.maxValue);

    this.input.value(String.valueOf(value));
    return this;
  }

  public Spinner clear() {
    this.input.value("");
    return this;
  }

  public Integer value() {
    try{
      return Integer.valueOf(this.input.value());
    } catch(Exception e){
      this.value(0);
      return Integer.valueOf(this.input.value());
    }
  }

  public Spinner placeholder(String placeholder) {
    this.input.placeholder(placeholder);
    return this;
  }

  @Override
  public Spinner disable() {
    this.input.disable();
    return this;
  }

  @Override
  public boolean disabled() {
    return this.input.disabled();
  }

  @Override
  public Spinner enable() {
    this.input.enable();
    return this;
  }
  
  @Override
  public Spinner tabIndex(int index) {
    this.input.tabIndex(index);
    return this;
  }
}