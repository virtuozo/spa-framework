package virtuozo.infra;

import virtuozo.interfaces.UIComponent;


public interface Clause {
  boolean matches(UIComponent component);
}