package virtuozo.interfaces;

public interface UIRenderer<E> {
  UIComponent render(E value);
}