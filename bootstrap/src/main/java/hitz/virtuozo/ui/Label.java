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

import hitz.virtuozo.infra.api.HasText;
import hitz.virtuozo.ui.CssClass;
import hitz.virtuozo.ui.Elements;
import hitz.virtuozo.ui.StyleChooser;
import hitz.virtuozo.ui.Widget;
import hitz.virtuozo.ui.api.UIWidget;

public class Label extends Widget<Label> implements HasText<Label>{
  public Label() {
    super(Elements.span());
    this.css().set("label", "label-default");
  }
  
  public Label appendTo(UIWidget widget){
    widget.asWidget().addChild(this);
    return this;
  }
  
  @Override
  public String text() {
    return this.element().getInnerText();
  }
  
  @Override
  public Label text(String text) {
    this.element().setInnerText(text);
    return this;
  }
  
  public static class Color extends CssClass {
    private Color(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    public static final Color DEFAULT = new Color("label-default");
    public static final Color PRIMARY = new Color("label-primary");
    public static final Color SUCCESS = new Color("label-success");
    public static final Color INFO = new Color("label-info");
    public static final Color WARNING = new Color("label-warning");
    public static final Color DANGER = new Color("label-danger");

    private static final StyleChooser STYLES = new StyleChooser(DEFAULT, PRIMARY, SUCCESS, INFO, WARNING, DANGER);
  }
}
