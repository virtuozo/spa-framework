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

import java.util.List;

import virtuozo.ui.Component;
import virtuozo.ui.CssClass;
import virtuozo.ui.Elements;
import virtuozo.ui.StyleChooser;
import virtuozo.ui.ActivationHelper.Behavior;
import virtuozo.ui.ActivationHelper.ToggleBehavior;
import virtuozo.ui.api.HasActivation;
import virtuozo.ui.api.UIComponent;

import com.google.gwt.dom.client.Element;

public class ButtonGroup extends Component<ButtonGroup> {
  private ActivationHelper activationHelper = new ActivationHelper();
  
  private Type type;
  
  public ButtonGroup() {
    this(Type.DEFAULT);
  }
  
  public ButtonGroup(Type type) {
    super(Elements.div());
    this.activationHelper.behaviour(new Behavior() {
      @Override
      public void doActivation(Element element, List<HasActivation<?>> activationList) {
      }
    });
    this.type = type;
    this.css().set(type.css());
  }
  
  public ButtonGroup checkbox(){
    this.activationHelper.behaviour(new CheckboxBehavior());
    return this;
  }
  
  public ButtonGroup radio(){
    this.activationHelper.behaviour(new ToggleBehavior());
    return this;
  }
  
  public Button addButton(){
    Button button = new Button();
    this.add(button);
    return button;
  }
  
  public ButtonGroup add(Button add) {
    this.activationHelper.add(add);
    return this.type.add(this, add);
  }
  
  public ButtonGroup addButtonGroup(){
    ButtonGroup group = new ButtonGroup();
    this.add(group);
    return group;
  }
  
  public ButtonGroup add(ButtonGroup add){
    return super.addChild(add);
  }
  
  public DropButton addDropButton(){
    DropButton button = new DropButton();
    this.add(button);
    return button;
  }
  
  public ButtonGroup add(DropButton add){
    return this.type.add(this, add);
  }
  
  public SplitButton addSplitButton(){
    SplitButton button = new SplitButton();
    this.add(button);
    return button;
  }
  
  public ButtonGroup add(SplitButton add){
    return this.type.add(this, add);
  }
  
  public static enum Type{
    BLOCK{
      String css() {
        return "btn-group btn-group-justified";
      }
      
      @Override
      ButtonGroup add(ButtonGroup group, UIComponent button) {
        group.add(new ButtonGroup().addChild(button));
        return group;
      }
    }, DEFAULT{
      String css() {
        return "btn-group";
      }
      
      @Override
      ButtonGroup add(ButtonGroup group, UIComponent button) {
        group.addChild(button);
        return group;
      }
    };
    
    abstract String css();
    
    abstract ButtonGroup add(ButtonGroup group, UIComponent button);
  }
  
  public static class Orientation extends CssClass{
    private Orientation(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }
    
    public static final Orientation HORIZONTAL = new Orientation("btn-group");
    public static final Orientation VERTICAL = new Orientation("btn-group-vertical");
    private static final StyleChooser STYLES = new StyleChooser(HORIZONTAL, VERTICAL);
  }
  
  public static class Size extends CssClass{
    private Size(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }
    
    public static final Size LARGE = new Size("btn-group-lg");
    public static final Size MEDIUM = new Size("btn-group-md");
    public static final Size SMALL = new Size("btn-group-sm");
    public static final Size X_SMALL = new Size("btn-group-xs");
    private static final StyleChooser STYLES = new StyleChooser(LARGE, MEDIUM, SMALL, X_SMALL);
  }
  
  class CheckboxBehavior implements Behavior{
    @Override
    public void doActivation(Element element, List<HasActivation<?>> activationList) {
      for (HasActivation<?> widget : activationList) {
        if (widget.match(element)) {
          if(widget.active()){
            widget.deactivate();
            return;
          }
          widget.activate();
        }
      }
    }
  }
}