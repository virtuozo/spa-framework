package hitz.virtuozo.ui.api;

public interface Icon {
  UIComponent asWidget();
  
  <C extends UIComponent> void appendTo(C component);
  
  String name();
}
