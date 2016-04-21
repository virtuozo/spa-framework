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
import virtuozo.infra.StyleChooser;
import virtuozo.interfaces.Composite;
import virtuozo.interfaces.CssClass;
import virtuozo.interfaces.Tag;

import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class Alert extends Composite<Alert> {
  private Tag<ButtonElement> close = Tag.as(Elements.button());

  public static Alert danger(){
    return new Alert().css(Type.DANGER);
  }
  
  public static Alert info(){
    return new Alert().css(Type.INFO);
  }
  
  public static Alert success(){
    return new Alert().css(Type.SUCCESS);
  }
  
  public static Alert warning(){
    return new Alert().css(Type.WARNING);
  }
  
  private Alert() {
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
  
  static class Type extends CssClass {
    private Type(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    public static final Type SUCCESS = new Type("alert-success");
    public static final Type INFO = new Type("alert-info");
    public static final Type WARNING = new Type("alert-warning");
    public static final Type DANGER = new Type("alert-danger");
    private static final StyleChooser STYLES = new StyleChooser(SUCCESS, INFO, WARNING, DANGER);
  }
}