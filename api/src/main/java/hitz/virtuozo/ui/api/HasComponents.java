package hitz.virtuozo.ui.api;

import hitz.virtuozo.ui.api.DetachChildrenEvent.DetachChildrenHandler;

public interface HasComponents<C extends HasComponents<C>> extends UIComponent {
  C onDetachChildren(DetachChildrenHandler handler);
  
  C detachChildren();

  C add(UIComponent add);

  C adopt(UIComponent child);

  C insert(UIComponent add, UIComponent before);

  Iterable<UIComponent> children();

  <UI extends UIComponent> UI childAt(int index);
  
  <UI extends UIComponent> UI find(Clause clause);
  
  Iterable<UIComponent> findAll(Clause clause);

  int indexOf(UIComponent child);

  int childrenCount();

  boolean hasChildren();

  C remove(UIComponent child);
}
