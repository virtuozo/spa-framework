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

import hitz.virtuozo.ui.Table.Row;
import hitz.virtuozo.ui.api.Assets;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public final class HorizontalSpinner extends Spinner<HorizontalSpinner> {

  private Assets assets = GWT.create(Assets.class);
  
  private Table table = new Table();

  private Iconic down = new Iconic();//.icon(Glyphicon.CHEVRON_LEFT);

  private Iconic up = new Iconic();//.icon(Glyphicon.CHEVRON_RIGHT);

  public HorizontalSpinner() {
    this.incorporate(this.table);
    this.down.icon(this.assets.downIcon());
    this.up.icon(this.assets.upIcon());
    this.init();
  }

  private void init() {
    Row row = this.table.body().addRow();

    row.addCell().add(this.down);
    row.addCell().add(this.input());
    row.addCell().add(this.up);

    this.up.onClick(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        HorizontalSpinner.this.increment();
      }
    });

    this.down.onClick(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        HorizontalSpinner.this.decrement();
      }
    });
  }
}