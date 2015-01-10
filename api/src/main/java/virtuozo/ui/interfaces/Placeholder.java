package virtuozo.ui.interfaces;

public interface Placeholder {
  void apply(UIInput<?, String> input, String placeholder);

  String valueOf(UIInput<?, String> input);
}
