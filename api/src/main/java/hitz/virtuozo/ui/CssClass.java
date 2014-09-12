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

import hitz.virtuozo.ui.api.UIClass;
import hitz.virtuozo.ui.api.UIWidget;


public abstract class CssClass implements UIClass {
  private String name;
  
  protected CssClass(String name) {
    this.name = name;
  }
  
  @Override
  public void parse(UIWidget widget) {
    this.chooser().choose(widget, this);
  }

  @Override
  public String name() {
    return this.name;
  }
  
  @Override
  public boolean equals(Object obj) {
    if(obj instanceof UIClass){
      return this.name.equals(((UIClass) obj).name());
    }
    
    return super.equals(obj);
  }
  
  protected abstract StyleChooser chooser();
}