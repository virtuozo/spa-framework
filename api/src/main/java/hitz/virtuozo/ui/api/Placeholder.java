package hitz.virtuozo.ui.api;

public interface Placeholder {
  void apply(UIInput<?, String> input, String placeholder);

  String valueOf(UIInput<?, String> input);
}
