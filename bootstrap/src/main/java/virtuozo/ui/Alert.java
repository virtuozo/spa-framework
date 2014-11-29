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

import virtuozo.ui.Composite;
import virtuozo.ui.CssClass;
import virtuozo.ui.Elements;
import virtuozo.ui.StyleChooser;
import virtuozo.ui.Tag;

import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class Alert extends Composite<Alert> {
  private Tag<ButtonElement> close = Tag.as(Elements.button());

  public Alert() {
    super(Elements.div());
    this.css().set("alert");
  }

  public Alert closeable() {
    this.css("alert-dismissable");
    this.close.css("close").html("&times;").onClick(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        Alert.this.hide();
      }
    });
    
    if(this.hasChildren()){
      return this.insert(this.close, this.childAt(0));
    }
    
    return this.add(this.close);
  }
  
  public static class Color extends CssClass {
    private Color(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    public static final Color SUCCESS = new Color("alert-success");
    public static final Color INFO = new Color("alert-info");
    public static final Color WARNING = new Color("alert-warning");
    public static final Color DANGER = new Color("alert-danger");
    private static final StyleChooser STYLES = new StyleChooser(SUCCESS, INFO, WARNING, DANGER);
  }
}