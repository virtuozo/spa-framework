package virtuozo.interfaces;

public interface Placeholder {
  void apply(UIInput<?, String> input, String placeholder);

  String valueOf(UIInput<?, String> input);
}
