package virtuozo.ui.interfaces;


public interface Icon {
  UIComponent asComponent();
  
  void attachTo(UIComponent component);
  
  String name();
  
  boolean is(UIComponent component);
}
