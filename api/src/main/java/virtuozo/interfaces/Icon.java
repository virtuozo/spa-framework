package virtuozo.interfaces;


public interface Icon {
  UIComponent asComponent();
  
  void attachTo(UIComponent component);
  
  void update(UIComponent component);
  
  String name();
  
  boolean is(UIComponent component);
}