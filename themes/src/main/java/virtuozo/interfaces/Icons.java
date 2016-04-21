package virtuozo.interfaces;

import virtuozo.infra.Clause;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.Icon;
import virtuozo.interfaces.UIComponent;

class Icons {
  static <C extends UIComponent> void attachTo(C component, final Icon icon) {
    Component<?> parent = component.asComponent();
    
    if(!parent.hasChildren()){
      Component<?> uiIcon = icon.asComponent().asComponent();
      parent.addChild(uiIcon);
      return;
    }
    
    Component<?> uiIcon = parent.find(new Clause() {
      @Override
      public boolean matches(UIComponent component) {
        return icon.is(component);
      }
    });
    
    if(uiIcon == null){
      uiIcon = icon.asComponent().asComponent();
      parent.addChild(uiIcon);
    }
    
    icon.update(uiIcon);
  }
}
