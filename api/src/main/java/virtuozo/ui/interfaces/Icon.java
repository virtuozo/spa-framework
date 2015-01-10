package virtuozo.ui.interfaces;


public interface Icon {
  UIComponent asComponent();
  
  <C extends UIComponent> void attachTo(C component);
  
  String name();
}
