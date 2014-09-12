package hitz.virtuozo.ui.api;

public interface UIRenderer<E> {
  UIWidget render(E value);
}