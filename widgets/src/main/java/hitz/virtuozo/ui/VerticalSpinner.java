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

package hitz.virtuozo.ui;

import hitz.virtuozo.ui.api.Assets;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public final class VerticalSpinner extends Spinner<VerticalSpinner> {

  private SpinnerButtons buttons = new SpinnerButtons();

  public VerticalSpinner() {
    super(Elements.div());
    this.init();
  }

  private void init() {
    this.css("spinner").addChild(this.input()).addChild(this.buttons);

    this.buttons.onUp(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        VerticalSpinner.this.increment();
      }
    });

    this.buttons.onDown(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        VerticalSpinner.this.decrement();
      }
    });
  }

  class SpinnerButtons extends Widget<SpinnerButtons> {
    private Assets assets = GWT.create(Assets.class);

    private Button up = new Button().css("spinner-up");

    private Button down = new Button().css("spinner-down");

    public SpinnerButtons() {
      super(Elements.div());
      this.up.icon(this.assets.upIcon());
      this.down.icon(this.assets.downIcon());
      this.css("spinner-buttons  btn-group btn-group-vertical").addChild(this.up).addChild(this.down);
    }

    public SpinnerButtons onUp(ClickHandler handler) {
      this.up.on(handler);
      return this;
    }

    public SpinnerButtons onDown(ClickHandler handler) {
      this.down.on(handler);
      return this;
    }
  }
}