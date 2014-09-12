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

import hitz.virtuozo.ui.api.UIWidget;

public enum ViewPort {
  X_SMALL{
    @Override
    public void hidden(UIWidget widget) {
      widget.asWidget().css().append("hidden-xs").remove("visible-xs");
    }
    
    @Override
    public void visible(UIWidget widget) {
      widget.asWidget().css().append("visible-xs").remove("hidden-xs");
    }
  }
  , SMALL{
    @Override
    public void hidden(UIWidget widget) {
      widget.asWidget().css().append("hidden-sm").remove("visible-sm");
    }
    
    @Override
    public void visible(UIWidget widget) {
      widget.asWidget().css().append("visible-sm").remove("hidden-sm");
    }
  }
  , MEDIUM{
    @Override
    public void hidden(UIWidget widget) {
      widget.asWidget().css().append("hidden-md").remove("visible-md");
    }
    
    @Override
    public void visible(UIWidget widget) {
      widget.asWidget().css().append("visible-md").remove("hidden-md");
    }
  }
  , LARGE{
    @Override
    public void hidden(UIWidget widget) {
      widget.asWidget().css().append("hidden-lg").remove("visible-lg");
    }
    
    @Override
    public void visible(UIWidget widget) {
      widget.asWidget().css().append("visible-lg").remove("hidden-lg");
    }
  };
  
  public abstract void visible(UIWidget widget);
  
  public abstract void hidden(UIWidget widget);
}
