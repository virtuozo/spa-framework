package virtuozo.ui;

import virtuozo.ui.interfaces.Icon;
import virtuozo.ui.interfaces.UIComponent;

class Icons {
  static <C extends UIComponent> void attachTo(C component, Icon icon) {
    UIComponent uiIcon = icon.asComponent();
    
    if(!component.asComponent().hasChildren()){
      component.asComponent().addChild(uiIcon);
      return;
    }
    
    if(icon.is(component.asComponent().firstChild())){
      component.asComponent().firstChild().asComponent().detach();
    }
    
    component.asComponent().insertChild(uiIcon, component.asComponent().childAt(0));
    
  }
}
