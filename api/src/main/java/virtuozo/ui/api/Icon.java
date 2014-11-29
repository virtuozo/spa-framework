package virtuozo.ui.api;

public interface Icon {
  UIComponent asComponent();
  
  <C extends UIComponent> void appendTo(C component);
  
  String name();
}
