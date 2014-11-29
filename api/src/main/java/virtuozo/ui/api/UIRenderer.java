package virtuozo.ui.api;

public interface UIRenderer<E> {
  UIComponent render(E value);
}