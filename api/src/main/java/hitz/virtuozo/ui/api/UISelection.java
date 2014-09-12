package hitz.virtuozo.ui.api;

import hitz.virtuozo.infra.api.HasChangeHandlers;

public interface UISelection<W extends UIWidget, V> extends UIInput<W, V>, HasChangeHandlers<W> {

  W checked(Boolean checked);

  Boolean checked();
}
