package virtuozo.interfaces;

import virtuozo.ui.interfaces.HasComponents;

public interface View {
  void render(HasComponents<?, ?> container);
  
  void detach();
}
