package hitz.virtuozo.ui;

import hitz.virtuozo.ui.api.Clause;
import hitz.virtuozo.ui.api.UIWidget;

public enum Clauses implements Clause{
  ATTACHED{
    @Override
    public boolean matches(UIWidget widget) {
      return widget.asWidget().attached();
    }
  },
  DETACHED{
    @Override
    public boolean matches(UIWidget widget) {
      return !widget.asWidget().attached();
    }
  },
  HIDDEN{
    @Override
    public boolean matches(UIWidget widget) {
      return !widget.asWidget().visible();
    }
  },
  VISIBLE{
    @Override
    public boolean matches(UIWidget widget) {
      return widget.asWidget().visible();
    }
  }
}