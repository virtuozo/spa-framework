package virtuozo.infra;

import virtuozo.ui.api.HasComponents;

public interface View {
  void render(HasComponents<?, ?> container);
}
