package virtuozo.ui.interfaces;

import virtuozo.ui.events.DetachChildrenEvent.DetachChildrenHandler;

public interface HasComponents<H extends HasComponents<H, C>, C extends UIComponent> extends UIComponent {
  H onDetachChildren(DetachChildrenHandler handler);
  
  H detachChildren();

  H add(C add);

  H adopt(C child);

  H insert(C add, C before);

  H firstChild(C add);
  
  Iterable<C> children();

  C firstChild();
  
  C lastChild();
  
  C childAt(int index);
  
  C find(Clause clause);
  
  Iterable<C> findAll(Clause clause);

  int indexOf(C child);

  int childrenCount();

  boolean hasChildren();

  H remove(C child);
}
