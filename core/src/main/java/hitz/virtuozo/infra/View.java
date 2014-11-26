package hitz.virtuozo.infra;

import hitz.virtuozo.ui.api.HasComponents;

public interface View {
  void render(HasComponents<?> container);
}
