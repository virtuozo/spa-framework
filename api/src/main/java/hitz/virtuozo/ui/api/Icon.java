package hitz.virtuozo.ui.api;

public interface Icon {
  UIWidget asWidget();
  
  <W extends UIWidget> void appendTo(W widget);
  
  String name();
}
