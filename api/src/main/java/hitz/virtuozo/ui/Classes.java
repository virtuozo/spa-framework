package hitz.virtuozo.ui;

import hitz.virtuozo.ui.api.UIClass;
import hitz.virtuozo.ui.api.UIClasses;
import hitz.virtuozo.ui.api.UIComponent;

import com.google.gwt.user.client.ui.WidgetHolder;

final class Classes implements UIClasses {
  private final WidgetHolder widget;

  Classes(WidgetHolder widget) {
    this.widget = widget;
  }

  public Classes append(UIClass... classes) {
    if(classes == null){
      return this;
    }
    
    for (UIClass clazz : classes) {
      clazz.parse((UIComponent) this.widget.getReference());
      this.widget.addStyleName(clazz.name());
    }

    return this;
  }

  public UIClasses append(String... classes) {
    if(classes == null){
      return this;
    }
    
    for (String clazz : classes) {
      this.widget.addStyleName(clazz);
    }

    return this;
  }

  public UIClasses set(String... classes) {
    if(classes == null){
      return this;
    }
    
    this.widget.setStyleName("");

    for (String clazz : classes) {
      this.widget.addStyleName(clazz);
    }

    return this;
  }

  public UIClasses set(UIClass... classes) {
    if(classes == null){
      return this;
    }
    
    this.widget.setStyleName("");

    for (UIClass clazz : classes) {
      clazz.parse((UIComponent) this.widget.getReference());
      this.widget.addStyleName(clazz.name());
    }

    return this;
  }

  public UIClasses remove(String... classes) {
    if(classes == null){
      return this;
    }
    
    for (String clazz : classes) {
      this.widget.removeStyleName(clazz);
    }

    return this;
  }

  public UIClasses remove(UIClass... classes) {
    if(classes == null){
      return this;
    }
    
    for (UIClass clazz : classes) {
      this.widget.removeStyleName(clazz.name());
    }

    return this;
  }
  
  public UIClasses toggle(UIClass clazz) {
    return this.toggle(clazz.name());
  }

  public UIClasses toggle(String clazz) {
    if (this.contains(clazz)) {
      this.remove(clazz);
      return this;
    }

    return this.append(clazz);
  }
  
  public boolean contains(UIClass clazz) {
    return this.contains(clazz.name());
  }

  public boolean contains(String clazz) {
    return this.widget.getStyleName().contains(clazz);
  }
}