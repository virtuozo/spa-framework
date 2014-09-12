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

import hitz.virtuozo.ui.Container;
import hitz.virtuozo.ui.HTML;
import hitz.virtuozo.ui.ViewPort;
import hitz.virtuozo.ui.Container.Row;
import hitz.virtuozo.ui.Container.Row.Column;

import com.google.gwt.dom.client.StyleInjector;

public class SidebarLayout {
  private Container container = new Container(Container.Type.FLUID);

  private Column top;

  private Column left;
  
  private Column main;

  public SidebarLayout() {
    HTML.body().addChild(this.container);
    this.init();
  }
  
  public Column top(){
    return this.top;
  }
  
  public Column left(){
    return this.left;
  }
  
  public Column main(){
    return this.main;
  }

  private void init() {
    this.top = this.container.addRow().addColumn().span(12, ViewPort.LARGE).id("page-layout-top");
    
    Row row = this.container.addRow();
    this.left = row.addColumn().span(4, ViewPort.SMALL).id("page-layout-left");
    this.main = row.addColumn().span(8, ViewPort.SMALL).offset(4, ViewPort.SMALL).id("page-layout-main");
    
    StyleInjector.inject("@media (min-width: 768px) { #page-layout-left { bottom: 0; display: block; left: 0; overflow-x: hidden; overflow-y: auto; padding: 20px; position: fixed; top: 51px;}}");
  }
}
