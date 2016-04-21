package virtuozo.infra;

import virtuozo.interfaces.HasComponents;


public interface View {
  void bind();
  
  void render(HasComponents<?, ?> container);
  
  void unbind();
}
