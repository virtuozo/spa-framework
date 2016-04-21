package virtuozo.infra;

import virtuozo.interfaces.UIComponent;


public enum Clauses implements Clause{
  ATTACHED{
    @Override
    public boolean matches(UIComponent widget) {
      return widget.asComponent().attached();
    }
  },
  DETACHED{
    @Override
    public boolean matches(UIComponent widget) {
      return !widget.asComponent().attached();
    }
  },
  HIDDEN{
    @Override
    public boolean matches(UIComponent widget) {
      return !widget.asComponent().visible();
    }
  },
  VISIBLE{
    @Override
    public boolean matches(UIComponent widget) {
      return widget.asComponent().visible();
    }
  };
}